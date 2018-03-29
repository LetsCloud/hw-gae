/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.service.DataBuilderService;

/**
 * @author CR
 *
 */
public class HotelData extends BaseData<Hotel> {

	public HotelData(DataBuilderService dbs, HotelRepository repo) {
		super(dbs, repo);
	}

	private Hotel hotelA;

	// private Hotel hotelB;

	public void build() {
		getHotelA();
	}

	public Hotel getHotelA() {
		if (hotelA != null)
			return hotelA;

		hotelA = new Hotel();
		hotelA.setAccount(dbs.getAccount());
		hotelA.setBusinessDate(dbs.getBusinessDate());
		hotelA.setCode("HA");
		hotelA.setName("Hotel A");

		hotelA = save(hotelA);
		return hotelA;
	}
}
