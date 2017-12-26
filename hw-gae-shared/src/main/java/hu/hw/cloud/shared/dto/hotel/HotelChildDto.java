/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HotelChildDto extends BaseDto {

	private String hotelWebSafeKey;

	private HotelDto hotelDto;

	public String getHotelWebSafeKey() {
		return hotelWebSafeKey;
	}

	public void setHotelWebSafeKey(String hotelWebSafeKey) {
		this.hotelWebSafeKey = hotelWebSafeKey;
	}

	public HotelDto getHotelDto() {
		return hotelDto;
	}

	public void setHotelDto(HotelDto hotelDto) {
		this.hotelDto = hotelDto;
	}
}
