package hu.hw.cloud.client.core.security;

import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

public class CurrentUser {
	
	private AppUserDto appUserDto;
	
	private HotelDto currentHotelDto;
	
    private boolean loggedIn;

    public AppUserDto getAppUserDto() {
		return appUserDto;
	}

	public void setAppUserDto(AppUserDto userDto) {
		this.appUserDto = userDto;
		this.currentHotelDto = userDto.getDefaultHotel();
	}

	public HotelDto getCurrentHotelDto() {
		return currentHotelDto;
	}

	public void setCurrentHotelDto(HotelDto currentHotel) {
		this.currentHotelDto = currentHotel;
	}

	public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
