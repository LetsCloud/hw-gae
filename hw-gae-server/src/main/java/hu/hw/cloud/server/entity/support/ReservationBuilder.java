/**
 * 
 */
package hu.hw.cloud.server.entity.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.reservation.Reservation;
import hu.hw.cloud.server.entity.reservation.RoomStay;
import hu.hw.cloud.server.repository.ReservationRepository;
import hu.hw.cloud.shared.cnst.ReservationStatus;

/**
 * @author CR
 *
 */
public class ReservationBuilder extends EntityBuilder<Reservation> {

	@Autowired
	private ReservationRepository repo;
	
	@Override
	void initProduct() {
		this.product = new Reservation();
	}

	@Override
	Reservation assembleProduct() {
		return this.product;
	}

	@Override
	Reservation saveProduct(Reservation product) {
		try {
			this.product = repo.save(product);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.product;
	}

	public ReservationBuilder currency(Currency currency) {
		this.product.setCurrency(currency);
		return this;
	}

	public ReservationBuilder hotel(Hotel hotel) {
		this.product.setHotel(hotel);
		return this;
	}

	public ReservationBuilder roomStays(List<RoomStay> roomStays) {
		this.product.setRoomStays(roomStays);;
		return this;
	}

	public ReservationBuilder hotel(ReservationStatus status) {
		this.product.setStatus(status);;
		return this;
	}
}
