/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.dto.hotel.RoomDto;
import hu.hw.cloud.shared.cnst.FoRoomStatus;
import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.filter.RoomStatusFilterDto;
import hu.hw.cloud.shared.dto.hotel.RoomAvailabilityDto;

/**
 * Szoba entitás
 * 
 * @author CR
 *
 */
@Entity
public class Room extends HotelChild {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(Room.class.getName());

	private static final String ROOM_CODE = "code";

	/**
	 * Szállodán belöl egyedi szobaszám
	 */
	@Index
	private String code;

	/**
	 * Emelet
	 */
	private String floor;

	/**
	 * Szoba leírása
	 */
	private String description;

	/**
	 * A szoba takarítási státusza.
	 */
	private RoomStatus roomStatus;

	/**
	 * Foglalt e a szoba
	 */
	@Ignore
	private Boolean occupied;

	/**
	 * A szoba front office státusza.
	 */
	@Ignore
	private FoRoomStatus foRoomStatus;

	/**
	 * Szobatípus hivatkozás
	 */
	private Ref<RoomType> roomType;

	/**
	 * Szoba nyitások és zárások
	 */
	private List<RoomAvailability> roomAvailabilities = new ArrayList<RoomAvailability>();

	public Room() {
		// LOGGER.info("Room()");
	}

	/**
	 * Entitás másolása
	 * 
	 * @param source
	 */
	public Room(Room source) {
		super(source);
		this.code = source.code;
		this.floor = source.floor;
		this.description = source.description;
		this.roomStatus = source.roomStatus;
		this.occupied = source.occupied;
		this.foRoomStatus = source.foRoomStatus;
		this.roomType = source.roomType;
		for (RoomAvailability ra : roomAvailabilities) {
			this.roomAvailabilities.add(new RoomAvailability(ra));
		}
	}

	/**
	 * Entitás létrehozása DTO-ból
	 * 
	 * @param dto
	 */
	public Room(RoomDto dto) {
		this.update(dto);
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public Room update(RoomDto dto) {
		super.update(dto);
		if (dto.getCode() != null) {
			this.setCode(dto.getCode());
			addUniqueIndex(ROOM_CODE, dto.getCode());
		}
		if (dto.getFloor() != null)
			this.setFloor(dto.getFloor());
		if (dto.getDescription() != null)
			this.setDescription(dto.getDescription());
		if (dto.getRoomStatus() != null)
			this.setRoomStatus(dto.getRoomStatus());
		if (dto.getOccupied() != null)
			this.setOccupied(dto.getOccupied());
		if (dto.getFoRoomStatus() != null)
			this.setFoRoomStatus(dto.getFoRoomStatus());
		if (dto.getRoomAvailabilityDtos() != null)
			this.setRoomAvailabilities(RoomAvailability.createList(dto.getRoomAvailabilityDtos()));
		return this;
	}

	public Room update(Room entity) {
		super.update(entity);
		if (entity.getCode() != null)
			this.setCode(entity.getCode());

		if (entity.getFloor() != null)
			this.setFloor(entity.getFloor());
		if (entity.getDescription() != null)
			this.setDescription(entity.getDescription());
		if (entity.getRoomStatus() != null)
			this.setRoomStatus(entity.getRoomStatus());
		if (entity.getOccupied() != null)
			this.setOccupied(entity.getOccupied());
		if (entity.getRoomAvailabilities() != null)
			this.setRoomAvailabilities(entity.getRoomAvailabilities());
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RoomType getRoomType() {
		return roomType.get();
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = Ref.create(roomType);
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public FoRoomStatus getFoRoomStatus() {
		return foRoomStatus;
	}

	public void setFoRoomStatus(FoRoomStatus foRoomStatus) {
		this.foRoomStatus = foRoomStatus;
	}

	public List<RoomAvailability> getRoomAvailabilities() {
		return roomAvailabilities;
	}

	public void setRoomAvailabilities(List<RoomAvailability> roomAvailabilities) {
		this.roomAvailabilities = roomAvailabilities;
	}

	@Override
	public String toString() {
		return "Room [" + super.toString() + ", code=" + this.code + ", floor=" + this.floor + ", description="
				+ this.description + ", roomType=" + this.roomType + ", roomStatus=" + this.roomStatus + ", occupied="
				+ this.occupied + ", foRoomStatus=" + this.foRoomStatus + ", roomAvailabilies="
				+ this.roomAvailabilities + "]";
	}

	/**
	 * DTO létrehozása entitásból
	 * 
	 * @param entity
	 * @return
	 */
	public static RoomDto createDto(Room entity) {
		RoomDto dto = new RoomDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * DTO módosítása entitás értékekkel
	 * 
	 * @param dto
	 * @return
	 */
	public RoomDto updateDto(RoomDto dto) {
		dto = (RoomDto) super.updateDto(dto);
		if (this.getCode() != null)
			dto.setCode(this.getCode());
		if (this.getFloor() != null)
			dto.setFloor(this.getFloor());
		if (this.getDescription() != null)
			dto.setDescription(this.getDescription());
		if (this.getRoomAvailabilities() != null) {
			List<RoomAvailabilityDto> roomOpeningsDtos = new ArrayList<RoomAvailabilityDto>();
			for (RoomAvailability roomOpening : this.getRoomAvailabilities()) {
				roomOpeningsDtos.add(RoomAvailability.createDto(roomOpening));
			}
			dto.setRoomAvailabilityDtos(roomOpeningsDtos);
		}
		if (this.getRoomStatus() != null)
			dto.setRoomStatus(this.getRoomStatus());
		if (this.getOccupied() != null)
			dto.setOccupied(this.getOccupied());
		if (this.getFoRoomStatus() != null)
			dto.setFoRoomStatus(this.getFoRoomStatus());
		if (this.getRoomType() != null)
			dto.setRoomTypeDto(RoomType.createDto(this.getRoomType()));
		return dto;
	}

	/**
	 * Entitás lsitából DTO listát hoz létre
	 * 
	 * @param entities
	 * @return
	 */
	public static List<RoomDto> createDtos(List<Room> entities) {
		List<RoomDto> dtos = new ArrayList<RoomDto>();
		for (Room entity : entities) {
			dtos.add(Room.createDto(entity));
		}
		return dtos;
	}

	/**
	 * 
	 * @param rooms
	 * @param filter
	 * @return
	 */
	public static List<Room> filterByRoomStatus(List<Room> rooms, final RoomStatusFilterDto filter) {

		Predicate<Room> condition = new Predicate<Room>() {

			@Override
			public boolean apply(Room object) {
				boolean result = true;
				// LOGGER.info("filterRooms.apply->fromRoom=" + filter.getFromRoom());
				result = (filter.getFromRoom().isEmpty() || (object.getCode().compareTo(filter.getFromRoom()) > -1)
						? result
						: false);
				result = (filter.getToRoom().isEmpty() || (object.getCode().compareTo(filter.getToRoom()) < 1) ? result
						: false);
				result = (filter.getToRoom().isEmpty() || (object.getCode().compareTo(filter.getToRoom()) < 1) ? result
						: false);
				return result;
			}
		};
		// LOGGER.info("filterRooms->fromRoom=" + filter.getFromRoom());

		Collection<Room> result = Collections2.filter(rooms, condition);

		// for (Room dto : result) {
		// LOGGER.info("filterRooms.filtered->" + dto.getCode());
		// }
		if (result.isEmpty())
			return null;

		// Collections.sort(result, RoomDto.ORDER_BY_CODE);

		return new ArrayList<Room>(result);
	}

}
