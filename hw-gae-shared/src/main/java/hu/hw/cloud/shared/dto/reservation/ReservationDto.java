/**
 * 
 */
package hu.hw.cloud.shared.dto.reservation;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.cnst.ReservationStatus;
import hu.hw.cloud.shared.dto.common.CurrencyDto;
import hu.hw.cloud.shared.dto.hotel.HotelChildDto;
import hu.hw.cloud.shared.dto.hotel.MarketCodeDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ReservationDto extends HotelChildDto {

	/**
	 * Foglalás státusza.
	 */
	private ReservationStatus status;

	private MarketCodeDto market;
	
	/**
	 * Valutanem.
	 */
	private CurrencyDto currencyDto;

	/**
	 * Szobafoglalások listája.
	 */
	private List<RoomStayDto> roomStayDtos = new ArrayList<RoomStayDto>();

	/**
	 * Terhelés előjegyzések listája.
	 */
	private List<FixedChargeDto> fixedChargeDtos = new ArrayList<FixedChargeDto>();

	/**
	 * Profil kapcsolatok.
	 */
	private List<ProfileLinkDto> profileLinkDtos = new ArrayList<ProfileLinkDto>();

	/**
	 * Csoport foglalás esetén a kapcsolódó szobafoglalások.
	 */
	private List<ReservationDto> reservationDtos = new ArrayList<ReservationDto>();

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public CurrencyDto getCurrencyDto() {
		return currencyDto;
	}

	public void setCurrencyDto(CurrencyDto currencyDto) {
		this.currencyDto = currencyDto;
	}

	public List<RoomStayDto> getRoomStayDtos() {
		return roomStayDtos;
	}

	public void setRoomStayDtos(List<RoomStayDto> roomStayDtos) {
		this.roomStayDtos = roomStayDtos;
	}

	public List<FixedChargeDto> getFixedChargeDtos() {
		return fixedChargeDtos;
	}

	public void setFixedChargeDtos(List<FixedChargeDto> fixedChargeDtos) {
		this.fixedChargeDtos = fixedChargeDtos;
	}

	public List<ReservationDto> getReservationDtos() {
		return reservationDtos;
	}

	public void setReservationRefs(List<ReservationDto> reservationDtos) {
		this.reservationDtos = reservationDtos;
	}

	public List<ProfileLinkDto> getProfileLinkDtos() {
		return profileLinkDtos;
	}

	public void setProfileLinkDtos(List<ProfileLinkDto> profileLinkDtos) {
		this.profileLinkDtos = profileLinkDtos;
	}

}
