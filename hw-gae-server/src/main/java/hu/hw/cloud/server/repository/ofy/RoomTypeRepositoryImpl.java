/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.RoomTypeRepository;

/**
 * @author CR
 *
 */
public class RoomTypeRepositoryImpl extends HotelChildRepositoryImpl<RoomType> implements RoomTypeRepository {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeRepositoryImpl.class.getName());

	@Autowired
	HotelRepository hotelRepository;

	public RoomTypeRepositoryImpl() {
		super(RoomType.class);
		logger.info("RoomTypeRepositoryImpl");
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<RoomType> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}
}
