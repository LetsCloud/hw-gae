/**
 * 
 */
package hu.hw.cloud.client.fro.config.hotel;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.browser.hotel.HotelBrowserModule;
import hu.hw.cloud.client.fro.browser.marketgroup.MarketGroupBrowserModule;
import hu.hw.cloud.client.fro.browser.room.RoomBrowserModule;
import hu.hw.cloud.client.fro.browser.roomtype.RoomTypeBrowserModule;

/**
 * @author robi
 *
 */
public class HotelConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new HotelBrowserModule());
		install(new RoomTypeBrowserModule());
		install(new RoomBrowserModule());
		install(new MarketGroupBrowserModule());

		bindPresenter(HotelConfigPresenter.class, HotelConfigPresenter.MyView.class, HotelConfigView.class,
				HotelConfigPresenter.MyProxy.class);
	}
}
