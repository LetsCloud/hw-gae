/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.server.entity.hotel.Room;

/**
 * @author CR
 *
 */
public interface RoomRepository extends CrudRepository<Room> {
	
	List<Room> getByDateAndHotel(String hotelWebSafeKey, Date date);	

	/**
	 * Visszaadja a megadott szálloda szobáit
	 * 
	 * @param hotel
	 * @return
	 */
	List<Room> getAllByHotel(String hotelKey);

}
