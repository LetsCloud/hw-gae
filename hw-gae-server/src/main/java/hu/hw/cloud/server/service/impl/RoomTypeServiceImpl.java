/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.RoomTypeRepository;
import hu.hw.cloud.server.service.RoomTypeService;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author CR
 *
 */
// @Service
public class RoomTypeServiceImpl extends HotelChildServiceImpl<RoomType, RoomTypeDto, RoomTypeRepository>
		implements RoomTypeService {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeServiceImpl.class.getName());

	// @Autowired
	public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository, AccountRepository accountRepository,
			HotelRepository hotelRepository) {
		super(roomTypeRepository, accountRepository, hotelRepository);
		logger.info("RoomTypeServiceImpl");
	}

	@Override
	protected RoomType createEntity(RoomTypeDto dto) {
		return new RoomType(dto);
	}

	@Override
	protected RoomType updateEntity(RoomType entity, RoomTypeDto dto) {
		entity.update(dto);
		return entity;
	}

	@Override
	protected RoomType updateEntity(RoomType oldEntity, RoomType newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomType> getAll(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}
}
