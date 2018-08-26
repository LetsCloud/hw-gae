/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.cnst.FoRoomStatus;
import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.filter.RoomStatusFilterDto;

/**
 * Szoba entitás
 * 
 * @author CR
 *
 */
@Entity
public class Room extends HotelChild {
//	private static final Logger logger = LoggerFactory.getLogger(Room.class.getName());

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
//		logger.info("Room()");
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
		if (roomType == null)
			return null;
		return roomType.get();
	}

	public void setRoomType(RoomType roomType) {
		if (roomType.getId() != null)
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
