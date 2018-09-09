/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;

import hu.hw.cloud.server.entity.reservation.Reservation;
import hu.hw.cloud.server.repository.ReservationRepository;
import hu.hw.cloud.server.service.ReservationService;

/**
 * @author CR
 *
 */
public class ReservationServiceImpl extends CrudServiceImpl<Reservation, ReservationRepository>
		implements ReservationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class.getName());
	
//	@Autowired
	public ReservationServiceImpl(ReservationRepository reservationRepository) {
		super(reservationRepository);
		LOGGER.info("ReservationServiceImpl");
	}

	@Override
	public List<Reservation> getAll(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsByRoom(String roomKey, Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationsByArrivalDate(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}
}
