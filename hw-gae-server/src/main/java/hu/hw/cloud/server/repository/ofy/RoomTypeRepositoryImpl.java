/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.RoomTypeRepository;

/**
 * @author CR
 *
 */
public class RoomTypeRepositoryImpl extends HotelChildRepositoryImpl<RoomType> implements RoomTypeRepository {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeRepositoryImpl.class.getName());

	public RoomTypeRepositoryImpl() {
		super(RoomType.class);
		logger.info("RoomTypeRepositoryImpl");
	}
}
