/**
 * 
 */
package hu.hw.cloud.client.fro.browser.marketgroup;

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
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.filter.FilterPresenterFactory;
import hu.hw.cloud.client.fro.filter.hotelchild.HotelChildFilterPresenter;
import hu.hw.cloud.client.fro.meditor.marketgroup.MarketGroupEditorFactory;
import hu.hw.cloud.client.fro.meditor.marketgroup.MarketGroupEditorPresenter;
import hu.hw.cloud.shared.api.MarketGroupResource;
import hu.hw.cloud.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class MarketGroupBrowserPresenter extends AbstractBrowserPresenter<MarketGroupDto, MarketGroupBrowserPresenter.MyView>
		implements MarketGroupBrowserUiHandlers, RefreshTableEvent.RefreshTableHandler {
	private static Logger logger = Logger.getLogger(MarketGroupBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<MarketGroupBrowserUiHandlers> {
		void setData(List<MarketGroupDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<MarketGroupResource> resourceDelegate;

	private final HotelChildFilterPresenter filter;

	private final MarketGroupEditorPresenter editor;

	private final CurrentUser currentUser;

	@Inject
	MarketGroupBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<MarketGroupResource> resourceDelegate, CurrentUser currentUser,
			FilterPresenterFactory filterFactory, MarketGroupEditorFactory editorFactory) {
		super(eventBus, view, placeManager);

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createHotelChildFilter();
		this.editor = editorFactory.createMarketGroupEditor();
		this.currentUser = currentUser;

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
		logger.info("MarketGroupTablePresenter().loadData()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<MarketGroupDto>>() {
			@Override
			public void onSuccess(List<MarketGroupDto> result) {
				getView().setData(result);
			}
		}).getAll(currentUser.getCurrentHotel().getWebSafeKey(), false);
	}

	@Override
	public void addNew() {
		editor.create();
	}

	@Override
	public void edit(MarketGroupDto dto) {
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