/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.RoomTypeRepository;
import hu.hw.cloud.server.service.RoomTypeService;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author CR
 *
 */
//@Service
public class RoomTypeServiceImpl extends CrudServiceImpl<RoomType, RoomTypeDto, RoomTypeRepository>
		implements RoomTypeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomTypeServiceImpl.class.getName());

	//@Autowired
	public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
		super(roomTypeRepository);
		LOGGER.info("RoomTypeServiceImpl");
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
	protected List<Object> getParents(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoomType> getAll(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RoomType updateEntity(RoomType oldEntity, RoomType newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
