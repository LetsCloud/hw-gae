/**
 * 
 */
package hu.hw.cloud.client.fro.browser.relationship;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import hu.hw.cloud.client.core.event.RefreshTableEvent;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.filter.FilterPresenterFactory;
import hu.hw.cloud.client.fro.filter.accountchild.AccountChildFilterPresenter;
import hu.hw.cloud.client.fro.meditor.relationship.RelationshipEditorFactory;
import hu.hw.cloud.client.fro.meditor.relationship.RelationshipEditorPresenter;
import hu.hw.cloud.shared.api.RelationshipResource;
import hu.hw.cloud.shared.dto.profile.RelationshipDto;

/**
 * @author robi
 *
 */
public class RelationshipBrowserPresenter
		extends AbstractBrowserPresenter<RelationshipDto, RelationshipBrowserPresenter.MyView>
		implements RelationshipBrowserUiHandlers, RefreshTableEvent.RefreshTableHandler {
	private static Logger logger = Logger.getLogger(RelationshipBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RelationshipBrowserUiHandlers> {
		void setData(List<RelationshipDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<RelationshipResource> resourceDelegate;

	private final AccountChildFilterPresenter filter;

	private final RelationshipEditorPresenter editor;

	@Inject
	RelationshipBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RelationshipResource> resourceDelegate, FilterPresenterFactory filterFactory,
			RelationshipEditorFactory editorFactory) {
		super(eventBus, view, placeManager);
		logger.info("RelationshipBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createAccountChildFilter();
		this.editor = editorFactory.createRelationshipEditor();

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
		setInSlot(SLOT_EDITOR, editor);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RelationshipDto>>() {
			@Override
			public void onSuccess(List<RelationshipDto> result) {
				getView().setData(result);
			}
		}).getAll(false);
	}

	@Override
	public void addNew() {
		editor.create();
	}

	@Override
	public void edit(RelationshipDto dto) {
		editor.edit(dto);
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	protected String getCreatorNameToken() {
// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getEditorNameToken() {
//TODO Auto-generated method stub
		return null;
	}
}