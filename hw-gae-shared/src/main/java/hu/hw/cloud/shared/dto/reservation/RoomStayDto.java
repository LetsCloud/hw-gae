/**
 * 
 */
package hu.hw.cloud.shared.dto.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.hotel.RoomDto;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomStayDto implements Dto {

	/**
	 * Érkezés napja.
	 */
	private Date arrival;

	/**
	 * Elutazott.
	 */
	private Date departure;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean movedInto;

	/**
	 * Átköltözött másik szobába.
	 */
	private Boolean movedOut;

	/**
	 * Szobatípus hivatkozás.
	 */
	private RoomTypeDto roomTypeDto;

	/**
	 * Szobatípus hivatkozás.
	 */
	private Integer quantity;

	/**
	 * Szoba hivatkozás.
	 */
	private RoomDto roomDto;

	/**
	 * Szobafoglaláshoz rendelt árkódok.
	 */
	private List<RateDto> rateDtos;

	/**
	 * Szobafoglalás vendégtartózkodásai.
	 */
	private List<GuestStayDto> guestStayDtos;

	private List<String> amenities = new ArrayList<String>();

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Boolean getMovedInto() {
		return movedInto;
	}

	public void setMovedInto(Boolean movedInto) {
		this.movedInto = movedInto;
	}

	public Boolean getMovedOut() {
		return movedOut;
	}

	public void setMovedOut(Boolean movedOut) {
		this.movedOut = movedOut;
	}

	public RoomTypeDto getRoomTypeDto() {
		return roomTypeDto;
	}

	public void setRoomTypeDto(RoomTypeDto roomTypeDto) {
		this.roomTypeDto = roomTypeDto;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public RoomDto getRoomDto() {
		return roomDto;
	}

	public void setRoomDto(RoomDto roomDto) {
		this.roomDto = roomDto;
	}

	public List<RateDto> getRateDtos() {
		return rateDtos;
	}

	public void setRateDtos(List<RateDto> rateDtos) {
		this.rateDtos = rateDtos;
	}

	public List<GuestStayDto> getGuestStayDtos() {
		return guestStayDtos;
	}

	public void setGuestStayDtos(List<GuestStayDto> guestStayDtos) {
		this.guestStayDtos = guestStayDtos;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

}
