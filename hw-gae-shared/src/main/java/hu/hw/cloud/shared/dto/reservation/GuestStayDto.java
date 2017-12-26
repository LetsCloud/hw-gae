/**
 * 
 */
package hu.hw.cloud.shared.dto.reservation;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.pf.GuestDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class GuestStayDto implements Dto {

	/**
	 * Vendég érkezése.
	 */
	private Date arrival;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean movedInto;

	/**
	 * Vendég távozása.
	 */
	private Date departure;

	/**
	 * Átköltözött másik szobába.
	 */
	private Boolean movedOut;

	/**
	 * Vendég profil kapcsolat.
	 */
	private GuestDto guestDto;

	/**
	 * A vendégtartózkodáshoz rendelet árkód.
	 */
	private List<RateDto> rateDtos;

	/**
	 * Átköltözött másik szobából.
	 */
	private Boolean chekedIn;

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Boolean getMovedInto() {
		return movedInto;
	}

	public void setMovedInto(Boolean movedInto) {
		this.movedInto = movedInto;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Boolean getMovedOut() {
		return movedOut;
	}

	public void setMovedOut(Boolean movedOut) {
		this.movedOut = movedOut;
	}

	public GuestDto getGuestDto() {
		return guestDto;
	}

	public void setGuestDto(GuestDto guestDto) {
		this.guestDto = guestDto;
	}

	public Boolean getChekedIn() {
		return chekedIn;
	}

	public void setChekedIn(Boolean chekedIn) {
		this.chekedIn = chekedIn;
	}

	public List<RateDto> getRateDtos() {
		return rateDtos;
	}

	public void setRateDtos(List<RateDto> rateDtos) {
		this.rateDtos = rateDtos;
	}

}
