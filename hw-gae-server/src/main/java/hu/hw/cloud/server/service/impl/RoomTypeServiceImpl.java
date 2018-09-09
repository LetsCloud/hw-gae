/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.RoomRepository;
import hu.hw.cloud.server.repository.RoomTypeRepository;
import hu.hw.cloud.server.service.RoomTypeService;

/**
 * @author CR
 *
 */
public class RoomTypeServiceImpl extends HotelChildServiceImpl<RoomType, RoomTypeRepository>
		implements RoomTypeService {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeServiceImpl.class.getName());

	private final RoomRepository roomRepository;

	public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository, AccountRepository accountRepository,
			HotelRepository hotelRepository, RoomRepository roomRepository) {
		super(roomTypeRepository, accountRepository, hotelRepository);
		logger.info("RoomTypeServiceImpl");
		this.roomRepository = roomRepository;
	}

	@Override
	public List<RoomType> getChildren(String parentWebSafeKey) {
		List<Room> rooms = roomRepository.getChildren(parentWebSafeKey);
		List<RoomType> roomTypes = repository.getChildren(parentWebSafeKey);
		for (RoomType roomType : roomTypes)
			roomType.setNumberOfRooms((int) rooms.stream().filter(room -> room.getRoomType() == roomType).count());

		return roomTypes;
	}

	@Override
	public List<RoomType> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters) {
		List<Room> rooms = roomRepository.getChildren(parentWebSafeKey);
		List<RoomType> roomTypes = super.getChildrenByFilters(parentWebSafeKey, filters);
		for (RoomType roomType : roomTypes)
			roomType.setNumberOfRooms((int) rooms.stream().filter(room -> room.getRoomType() == roomType).count());

		return roomTypes;
	}
}
