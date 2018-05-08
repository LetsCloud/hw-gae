/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hu.hw.cloud.shared.cnst.FoRoomStatus;
import hu.hw.cloud.shared.cnst.RoomStatus;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomDto extends HotelChildDto {

	/**
	 * Egyedi szobaszám
	 */
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
	private Boolean occupied;

	/**
	 * A szoba front office státusza.
	 */
	private FoRoomStatus foRoomStatus;

	/**
	 * Szobatípus hivatkozás
	 */
	private RoomTypeDto roomTypeDto;

	/**
	 * Szoba nyitások és zárások
	 */
	private List<RoomAvailabilityDto> roomAvailabilityDtos = new ArrayList<RoomAvailabilityDto>();

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

	public RoomTypeDto getRoomTypeDto() {
		return roomTypeDto;
	}

	public void setRoomTypeDto(RoomTypeDto roomTypeDto) {
		this.roomTypeDto = roomTypeDto;
	}

	public List<RoomAvailabilityDto> getRoomAvailabilityDtos() {
		return roomAvailabilityDtos;
	}

	public void setRoomAvailabilityDtos(List<RoomAvailabilityDto> roomAvailabilityDtos) {
		this.roomAvailabilityDtos = roomAvailabilityDtos;
	}

	public void addRoomAvailabilityDto(RoomAvailabilityDto roomAvailabilityDto) {
		this.roomAvailabilityDtos.add(roomAvailabilityDto);
	}

	@Override
	public String toString() {
		return "RoomDto [" + super.toString() + ", code=" + this.code + ", floor=" + this.floor + ", description="
				+ this.description + ", roomType=" + this.roomTypeDto + ", roomStatus=" + this.roomStatus
				+ ", occupied=" + this.occupied + ", foRoomStatus=" + this.foRoomStatus + ", roomAvailabilies="
				+ this.roomAvailabilityDtos + "]";
	}

	@JsonIgnore
	public static Comparator<RoomDto> ORDER_BY_CODE = new Comparator<RoomDto>() {
		public int compare(RoomDto one, RoomDto other) {
			return one.getCode().compareTo(other.getCode());
		}
	};
}
