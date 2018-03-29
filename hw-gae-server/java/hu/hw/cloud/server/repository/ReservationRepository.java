/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.server.entity.reservation.Reservation;

/**
 * @author CR
 *
 */
public interface ReservationRepository extends CrudRepository<Reservation> {

	List<Reservation> getInHouseReservations(String hotelWebSafeKey, Date onDate);

	List<Reservation> getReservationsArrivalDate(String hotelWebSafeKey, Date onDate);

}
