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

	/**
	 * 
	 */
	private static final String ROOMTYPE_CODE = "code";

	public RoomTypeRepositoryImpl() {
		super(RoomType.class);
		logger.info("RoomTypeRepositoryImpl");
	}

	@Override
	protected void loadUniqueIndexMap(RoomType entiy) {

		if (entiy.getCode() != null)
			entiy.addUniqueIndex(ROOMTYPE_CODE, entiy.getCode());
	}
}
