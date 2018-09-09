/**
 * 
 */
package hu.hw.cloud.server.entity.reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.entity.hotel.HotelChild;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.shared.cnst.ReservationStatus;

/**
 * @author CR
 *
 */
@Entity
public class Reservation extends HotelChild {
//	private static final Logger LOGGER = LoggerFactory.getLogger(Reservation.class.getName());

	public static final String FLD_STATUS = "status";

	public static final String FLD_ROOMSTAYS = "roomStays";

	/**
	 * Foglalás státusza.
	 */
	@Index
	private ReservationStatus status;

	/**
	 * Valutanem.
	 */
	private Ref<Currency> currencyRef;

	/**
	 * Szobafoglalások listája.
	 */
	private List<RoomStay> roomStays = new ArrayList<RoomStay>();

	/**
	 * Terhelés előjegyzések listája.
	 */
	private List<FixedCharge> fixedCharges = new ArrayList<FixedCharge>();

	/**
	 * Profil kapcsolatok.
	 */
	private List<ProfileLink> profileLinks = new ArrayList<ProfileLink>();

	/**
	 * Csoport foglalás esetén a kapcsolódó szobafoglalások.
	 */
	private List<Ref<Reservation>> reservationRefs = new ArrayList<Ref<Reservation>>();

	public Reservation() {
	}

	/**
	 * 
	 */
	public Currency getCurrency() {
		return currencyRef.get();
	}

	/**
	 * 
	 * @param currency
	 */
	public void setCurrency(Currency currency) {
		this.currencyRef = Ref.create(currency);
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public Ref<Currency> getCurrencyRef() {
		return currencyRef;
	}

	public void setCurrencyRef(Ref<Currency> currencyRef) {
		this.currencyRef = currencyRef;
	}

	public List<RoomStay> getRoomStays() {
		return roomStays;
	}

	public void setRoomStays(List<RoomStay> roomStays) {
		this.roomStays = roomStays;
	}

	public List<FixedCharge> getFixedCharges() {
		return fixedCharges;
	}

	public void setFixedCharges(List<FixedCharge> fixedCharges) {
		this.fixedCharges = fixedCharges;
	}

	public List<ProfileLink> getProfileLinks() {
		return profileLinks;
	}

	public void setProfileLinks(List<ProfileLink> profileLinks) {
		this.profileLinks = profileLinks;
	}

	public List<Ref<Reservation>> getReservationRefs() {
		return reservationRefs;
	}

	public void setReservationRefs(List<Ref<Reservation>> reservationRefs) {
		this.reservationRefs = reservationRefs;
	}

	public static Builder builder() {
		return new Reservation.Builder();
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder extends HotelChild.Builder<Builder> {

		private ReservationStatus status;

		private Currency currency;

		private List<RoomStay> roomStays = new ArrayList<RoomStay>();

		private List<FixedCharge> fixedCharges = new ArrayList<FixedCharge>();

		private List<ProfileLink> profileLinks = new ArrayList<ProfileLink>();

		private List<Reservation> reservations = new ArrayList<Reservation>();

		public Builder() {
		}

		public Reservation build() {
			return new Reservation(this);
		}

		public Builder status(ReservationStatus status) {
			this.status = status;
			return this;
		}

		public Builder description(Currency currency) {
			this.currency = currency;
			return this;
		}

		public Builder roomStays(List<RoomStay> roomStays) {
			this.roomStays = roomStays;
			return this;
		}

		public Builder addRoomStay(RoomStay roomStay) {
			this.roomStays.add(roomStay);
			return this;
		}

		public Builder fixedCharges(List<FixedCharge> fixedCharges) {
			this.fixedCharges = fixedCharges;
			return this;
		}

		public Builder addFixedCharge(FixedCharge fixedCharge) {
			this.fixedCharges.add(fixedCharge);
			return this;
		}

		public Builder profileLinks(List<ProfileLink> profileLinks) {
			this.profileLinks = profileLinks;
			return this;
		}

		public Builder addProfileLink(ProfileLink profileLink) {
			this.profileLinks.add(profileLink);
			return this;
		}

		public Builder reservations(List<Reservation> reservations) {
			this.reservations = reservations;
			return this;
		}

		public Builder addReservation(Reservation reservation) {
			this.reservations.add(reservation);
			return this;
		}
	}

	protected Reservation(Builder builder) {
		super(builder);
		if (builder.currency != null)
			this.setCurrency(builder.currency);
		this.setFixedCharges(builder.fixedCharges);
		this.setProfileLinks(builder.profileLinks);
		// this.setReservationRefs(builder.reservationRefs);
		this.setRoomStays(builder.roomStays);
		this.setStatus(builder.status);
	}

	public static List<Reservation> filterByRoom(List<Reservation> reservations, final Room room) {

		Predicate<Reservation> condition = new Predicate<Reservation>() {

			@Override
			public boolean apply(Reservation reservation) {
				for (RoomStay roomStay : reservation.getRoomStays()) {
					if (roomStay.getRoom().equals(room)) {
						return true;
					}
				}
				return false;
			}
		};

		Collection<Reservation> result = Collections2.filter(reservations, condition);

		if (result.isEmpty()) {
			return null;
		}

		return new ArrayList<Reservation>(result);
	}

}
