/**
 * 
 */
package hu.hw.cloud.client.fro.reservation;

import hu.hw.cloud.client.fro.reservation.extra.ExtraResPresenter;
import hu.hw.cloud.client.fro.reservation.guest.GuestResPresenter;
import hu.hw.cloud.client.fro.reservation.header.MainResPresenter;
import hu.hw.cloud.client.fro.reservation.room.RoomResPresenter;

/**
 * @author robi
 *
 */
public interface ResWidgetPresenterFactory {
	MainResPresenter createMainResPresenter();

	RoomResPresenter createRoomResPresenter();

	ExtraResPresenter createExtraResPresenter();

	GuestResPresenter createGuestResPresenter();
}
