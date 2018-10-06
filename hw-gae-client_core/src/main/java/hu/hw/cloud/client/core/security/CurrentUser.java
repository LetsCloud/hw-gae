package hu.hw.cloud.client.core.security;

import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.dto.hotel.HotelDtor;

public class CurrentUser {

	private AppUserDto appUserDto;

	private HotelDtor currentHotel;

	private boolean loggedIn;

	public AppUserDto getAppUserDto() {
		return appUserDto;
	}

	public void setAppUserDto(AppUserDto userDto) {
		this.appUserDto = userDto;
		this.currentHotel = userDto.getDefaultHotel();
	}

	public HotelDtor getCurrentHotel() {
		return currentHotel;
	}

	public void setCurrentHotel(HotelDtor currentHotel) {
		this.currentHotel = currentHotel;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public String toString() {
		String ret = "CurrentUser:{appUserDto=" + appUserDto + ", currentHotelDto=" + currentHotel + ", loggedIn=" + loggedIn +", " + super.toString() + "}";
		return ret;
	}

}
