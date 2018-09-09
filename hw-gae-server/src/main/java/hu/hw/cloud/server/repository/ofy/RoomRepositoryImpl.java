/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.repository.RoomRepository;

/**
 * @author CR
 *
 */
public class RoomRepositoryImpl extends HotelChildRepositoryImpl<Room> implements RoomRepository {
	private static final Logger logger = LoggerFactory.getLogger(RoomRepositoryImpl.class.getName());

	private static final String ROOM_CODE = "code";

	public RoomRepositoryImpl() {
		super(Room.class);
		logger.info("RoomRepositoryImpl");
	}

	@Override
	public List<Room> getByDateAndHotel(String hotelWebSafeKey, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAllByHotel(String hotelKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void loadUniqueIndexMap(Room entiy) {
		if (entiy.getCode() != null)
			entiy.addUniqueIndex(ROOM_CODE, entiy.getCode());
	}
}
