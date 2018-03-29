/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.RoomTypeRepository;

/**
 * @author CR
 *
 */
//@Repository("roomTypeRepository")
public class RoomTypeRepositoryImpl extends CrudRepositoryImpl<RoomType> implements RoomTypeRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomTypeRepositoryImpl.class.getName());

	@Autowired
	HotelRepository hotelRepository;

	public RoomTypeRepositoryImpl() {
		super(RoomType.class);
		LOGGER.info("RoomTypeRepositoryImpl");
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<RoomType> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	protected Object getParent(RoomType entity) {
		return entity.getHotel();
	}
}
