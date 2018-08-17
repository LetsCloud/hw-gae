/**
 * 
 */
package hu.hw.cloud.client.fro.config.hotel;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.BrowserPresenterFactory;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
import hu.hw.cloud.client.fro.config.PresenterWidgetStore;
import hu.hw.cloud.client.fro.i18n.FroMessages;

/**
 * @author robi
 *
 */
public class HotelConfigPresenter
		extends AbstractConfigPresenter<HotelConfigPresenter.MyView, HotelConfigPresenter.MyProxy>
		implements HotelConfigUiHandlers {
	private static Logger logger = Logger.getLogger(HotelConfigPresenter.class.getName());

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.HOTEL_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<HotelConfigPresenter> {
	}

	@Inject
	HotelConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			BrowserPresenterFactory dtoTablePresenterFactory, FroMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("HotelConfigPresenter()");

		setCaption(i18n.mainMenuItemHotelConfig());

		addTable(1, new PresenterWidgetStore(i18n.hotelConfigHotels(), dtoTablePresenterFactory.createHotelTablePresenter()));
		addTable(2,
				new PresenterWidgetStore(i18n.hotelConfigRoomTypes(), dtoTablePresenterFactory.createRoomTypeTablePresenter()));
		addTable(3, new PresenterWidgetStore(i18n.hotelConfigRooms(), dtoTablePresenterFactory.createRoomTablePresenter()));
		addTable(4, new PresenterWidgetStore(i18n.hotelConfigMarketGroups(),
				dtoTablePresenterFactory.createMarketGroupTablePresenter()));

		getView().setUiHandlers(this);
	}
}
