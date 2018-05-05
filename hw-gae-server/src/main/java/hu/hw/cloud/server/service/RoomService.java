/**
 * 
 */
package hu.hw.cloud.server.service;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public interface RoomService extends HotelChildService<Room, RoomDto> {

	/**
	 * 
	 * @param hotel
	 * @return
	 */
	List<Room> getActiveRoomsByHotel(String hotelKey);

	/**
	 * Visszaadja a megadott szálloda adott adott nappon rendelkezésre álló szobáit
	 * 
	 * @param hotel
	 * @return
	 */
	List<Room> getAvailableRoomsByHotelOnDate(String hotelKey, Date date);

	List<Room> getAvailableRoomsByHotelOnDateWithReservations(String hotelKey, Date date);

	Room changeStatus(String hotelKey, RoomStatus roomStatus) throws Throwable;
}
