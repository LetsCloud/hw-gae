/**
 * 
 */
package hu.hw.cloud.client.fro.browser.usergroup;

import java.util.List;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import hu.hw.cloud.client.core.event.RefreshTableEvent;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.meditor.usergroup.UserGroupEditorFactory;
import hu.hw.cloud.client.fro.meditor.usergroup.UserGroupEditorPresenter;
import hu.hw.cloud.shared.UserGroupResource;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupBrowserPresenter extends AbstractBrowserPresenter<UserGroupDto, UserGroupBrowserPresenter.MyView>
		implements UserGroupBrowserUiHandlers, RefreshTableEvent.RefreshTableHandler {

	public interface MyView extends View, HasUiHandlers<UserGroupBrowserUiHandlers> {
		void setData(List<UserGroupDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<UserGroupResource> resourceDelegate;

	private final UserGroupEditorPresenter editor;

	@Inject
	UserGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<UserGroupResource> resourceDelegate, CurrentUser currentUser,
			UserGroupEditorFactory editorFactory) {
		super(eventBus, view, placeManager);

		this.resourceDelegate = resourceDelegate;
		this.editor = editorFactory.createUserGroupEditor();

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_EDITOR, editor);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<UserGroupDto>>() {
			@Override
			public void onSuccess(List<UserGroupDto> result) {
				getView().setData(result);
			}
		}).list();
	}

	@Override
	public void addNew() {
		editor.create();
	}

	@Override
	public void edit(UserGroupDto dto) {
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
		// TODO Auto-generated method stub
		return null;
	}
}