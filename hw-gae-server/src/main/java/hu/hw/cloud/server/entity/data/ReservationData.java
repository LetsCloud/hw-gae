/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import java.util.Date;

import hu.hw.cloud.server.entity.reservation.GuestStay;
import hu.hw.cloud.server.entity.reservation.Reservation;
import hu.hw.cloud.server.entity.reservation.RoomStay;
import hu.hw.cloud.server.repository.ReservationRepository;
import hu.hw.cloud.server.service.DataBuilderService;
import hu.hw.cloud.server.service.impl.DataBuilderServiceImpl;
import hu.hw.cloud.server.utils.DateUtils;
import hu.hw.cloud.shared.cnst.ReservationStatus;

/**
 * @author CR
 *
 */
public class ReservationData extends BaseData<Reservation> {

	private Reservation r10;
	private Reservation r11;
	private Reservation r12;
	private Reservation r13;

	public ReservationData(DataBuilderService dbs, ReservationRepository repo) {
		super(dbs, repo);
	}

	public void build() {
		getR10();
		getR11();
	}

	public Reservation getR10() {
		if (r10 != null)
			return r10;

		Date arrival = DataBuilderServiceImpl.BUSINESS_DATE;
		Date departure = DateUtils.addDays(arrival, 5);

		// @formatter:off
		GuestStay gs0 = GuestStay.builder()
				.arrival(arrival)
				.departure(departure)
				.guest(dbs.getGuest102())
				.build();

		GuestStay gs1 = GuestStay.builder()
				.arrival(arrival)
				.departure(departure)
				.guest(dbs.getGuest101())
				.build();

		RoomStay rs1 = RoomStay.builder()
				.arrival(arrival)
				.departure(departure)
				.addGuestStay(gs0)
				.addGuestStay(gs1)
				.quantity(1)
				.room(dbs.getRoom10A())
				.build();
		
		r10 = Reservation.builder()
				.hotel(dbs.getHotelA())
				.status(ReservationStatus.DEFINITIVE)
				.addRoomStay(rs1)
				.build();
		// @formatter:on

		r10 = save(r10);
		return r10;
	}

	public Reservation getR11() {
		if (r11 != null)
			return r11;

		Date arrival = DataBuilderServiceImpl.BUSINESS_DATE;
		Date departure = DateUtils.addDays(arrival, 5);

		// @formatter:off
		GuestStay gs1 = GuestStay.builder()
				.arrival(arrival)
				.departure(departure)
				.guest(dbs.getGuest101())
				.build();
		
		RoomStay rs1 = RoomStay.builder()
				.arrival(arrival)
				.departure(departure)
				.addGuestStay(gs1)
				.quantity(1)
				.room(dbs.getRoom11A())
				.build();
		
		r11 = Reservation.builder()
				.hotel(dbs.getHotelA())
				.status(ReservationStatus.CHECKED_IN)
				.addRoomStay(rs1)
				.build();
		// @formatter:on

		r11 = save(r11);
		return r11;
	}

	public Reservation getR12() {
		if (r12 != null)
			return r12;

		Date arrival = DateUtils.addDays(DataBuilderServiceImpl.BUSINESS_DATE, -7);
		Date departure = DateUtils.addDays(DataBuilderServiceImpl.BUSINESS_DATE, -1);

		// @formatter:off
		GuestStay gs1 = GuestStay.builder()
				.arrival(arrival)
				.departure(departure)
				.guest(dbs.getGuest121())
				.build();

		GuestStay gs2 = GuestStay.builder()
				.arrival(arrival)
				.departure(departure)
				.guest(dbs.getGuest122())
				.build();
		
		RoomStay rs1 = RoomStay.builder()
				.arrival(arrival)
				.departure(departure)
				.addGuestStay(gs1)
				.addGuestStay(gs2)
				.quantity(1)
				.room(dbs.getRoom10A())
				.build();
		
		r12 = Reservation.builder()
				.hotel(dbs.getHotelA())
				.status(ReservationStatus.CHECKED_OUT)
				.addRoomStay(rs1)
				.build();
		// @formatter:on

		r12 = save(r12);
		return r12;
	}

	public Reservation getR13() {
		if (r13 != null)
			return r13;

		Date arrival = DateUtils.addDays(DataBuilderServiceImpl.BUSINESS_DATE, -3);
		Date departure = DateUtils.addDays(DataBuilderServiceImpl.BUSINESS_DATE, 2);

		// @formatter:off
		GuestStay gs1 = GuestStay.builder()
				.arrival(arrival)
				.departure(departure)
				.guest(dbs.getGuest131())
				.build();

		GuestStay gs2 = GuestStay.builder()
				.arrival(arrival)
				.departure(departure)
				.guest(dbs.getGuest132())
				.build();
		
		RoomStay rs1 = RoomStay.builder()
				.arrival(arrival)
				.departure(departure)
				.addGuestStay(gs1)
				.addGuestStay(gs2)
				.quantity(1)
				.room(dbs.getRoom12A())
				.build();
		
		r13 = Reservation.builder()
				.hotel(dbs.getHotelA())
				.status(ReservationStatus.CHECKED_IN)
				.addRoomStay(rs1)
				.build();
		// @formatter:on

		r13 = save(r13);
		return r13;
	}
}
