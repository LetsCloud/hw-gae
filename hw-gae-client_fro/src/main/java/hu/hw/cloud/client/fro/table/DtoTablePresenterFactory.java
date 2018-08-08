/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import hu.hw.cloud.client.fro.table.appuser.AppUserTablePresenter;
import hu.hw.cloud.client.fro.table.hotel.HotelTablePresenter;
import hu.hw.cloud.client.fro.table.marketgroup.MarketGroupTablePresenter;
import hu.hw.cloud.client.fro.table.room.RoomTablePresenter;
import hu.hw.cloud.client.fro.table.roomtype.RoomTypeTablePresenter;
import hu.hw.cloud.client.fro.table.usergroup.UserGroupTablePresenter;

/**
 * @author robi
 *
 */
public interface DtoTablePresenterFactory {

	UserGroupTablePresenter createUserGroupTablePresenter();

	AppUserTablePresenter createAppUserTablePresenter();

	HotelTablePresenter createHotelTablePresenter();

	RoomTypeTablePresenter createRoomTypeTablePresenter();

	RoomTablePresenter createRoomTablePresenter();

	MarketGroupTablePresenter createMarketGroupTablePresenter();

}
