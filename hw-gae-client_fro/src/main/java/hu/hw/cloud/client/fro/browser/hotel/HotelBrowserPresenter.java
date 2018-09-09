/**
 * 
 */
package hu.hw.cloud.client.fro.browser.hotel;

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

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.shared.api.HotelResource;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelBrowserPresenter extends AbstractBrowserPresenter<HotelDto, HotelBrowserPresenter.MyView>
		implements HotelBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(HotelBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<HotelBrowserUiHandlers> {
		void setData(List<HotelDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<HotelResource> resourceDelegate;

	@Inject
	HotelBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<HotelResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view, placeManager);
		logger.info("HotelBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<HotelDto>>() {
			@Override
			public void onSuccess(List<HotelDto> result) {
				getView().setData(result);
			}
		}).list();
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.HOTEL_EDITOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.HOTEL_EDITOR;
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
}