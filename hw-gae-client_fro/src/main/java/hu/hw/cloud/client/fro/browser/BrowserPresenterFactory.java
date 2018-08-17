/**
 * 
 */
package hu.hw.cloud.client.fro.browser;

import hu.hw.cloud.client.fro.browser.customer.CustomerBrowserPresenter;
import hu.hw.cloud.client.fro.browser.hotel.HotelTablePresenter;
import hu.hw.cloud.client.fro.browser.marketgroup.MarketGroupTablePresenter;
import hu.hw.cloud.client.fro.browser.room.RoomTablePresenter;
import hu.hw.cloud.client.fro.browser.roomtype.RoomTypeTablePresenter;
import hu.hw.cloud.client.fro.browser.usergroup.UserGroupTablePresenter;
import hu.hw.cloud.client.fro.table.appuser.AppUserTablePresenter;

/**
 * @author robi
 *
 */
public interface BrowserPresenterFactory {

	UserGroupTablePresenter createUserGroupTablePresenter();

	AppUserTablePresenter createAppUserTablePresenter();

	CustomerBrowserPresenter createCustomerTablePresenter();

	HotelTablePresenter createHotelTablePresenter();

	RoomTypeTablePresenter createRoomTypeTablePresenter();

	RoomTablePresenter createRoomTablePresenter();

	MarketGroupTablePresenter createMarketGroupTablePresenter();

}
