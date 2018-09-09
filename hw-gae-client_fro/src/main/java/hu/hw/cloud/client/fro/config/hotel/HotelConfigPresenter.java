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

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.fro.browser.hotel.HotelBrowserFactory;
import hu.hw.cloud.client.fro.browser.marketgroup.MarketGroupBrowserFactory;
import hu.hw.cloud.client.fro.browser.room.RoomBrowserFactory;
import hu.hw.cloud.client.fro.browser.roomtype.RoomTypeBrowserFactory;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
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
	@NameToken(CoreNameTokens.HOTEL_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<HotelConfigPresenter> {
	}

	@Inject
	HotelConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy, HotelBrowserFactory hotelBrowserFactory,
			RoomTypeBrowserFactory roomTypeBrowserFactory, RoomBrowserFactory roomBrowserFactory,
			MarketGroupBrowserFactory marketGroupBrowserFactory, CoreMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("HotelConfigPresenter()");

		setCaption(i18n.hotelConfigTitle());
		setDescription(i18n.hotelConfigDescription());

		addContent(i18n.hotelBrowserTitle(), hotelBrowserFactory.createHotelTablePresenter());
		addContent(i18n.roomTypeBrowserTitle(), roomTypeBrowserFactory.createRoomTypeTablePresenter());
		addContent(i18n.roomBrowserTitle(), roomBrowserFactory.createRoomTablePresenter());
		addContent(i18n.marketGroupBrowserTitle(), marketGroupBrowserFactory.createMarketGroupBrowser());

		getView().setUiHandlers(this);
	}
}
