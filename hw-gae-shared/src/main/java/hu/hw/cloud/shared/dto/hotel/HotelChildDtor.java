/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class HotelChildDtor extends BaseDto {

	private HotelDtor hotel;

	public HotelDtor getHotel() {
		return hotel;
	}

	public void setHotel(HotelDtor hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		String ret = "HotelChildDtor:{hotel=" + hotel + ", " + super.toString() + "}";
		return ret;
	}
}
