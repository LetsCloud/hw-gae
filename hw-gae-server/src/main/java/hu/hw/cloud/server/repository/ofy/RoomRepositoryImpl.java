/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.repository.RoomRepository;

/**
 * @author CR
 *
 */
public class RoomRepositoryImpl extends CrudRepositoryImpl<Room> implements RoomRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomRepositoryImpl.class.getName());

	public RoomRepositoryImpl() {
		super(Room.class);
		LOGGER.info("RoomRepositoryImpl");
	}

	@Override
	protected Object getParent(Room entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<Room> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	public List<Room> getByDateAndHotel(String hotelWebSafeKey, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAllByHotel(String hotelKey) {
		List<Room> result = this.getChildren(Hotel.createRef(hotelKey));
		
//		for (Room room : result) {
//			LOGGER.info("getAllByHotel->room=" + room.getRoomAvailabilities());
//		}
		return result;
	}
}
