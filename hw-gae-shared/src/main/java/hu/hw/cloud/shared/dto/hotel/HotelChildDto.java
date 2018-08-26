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

	private HotelDto hotel;

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		String ret = "HotelChildDto:{" + super.toString() + ", hotel=" + hotel + "}";
		return ret;
	}
}
