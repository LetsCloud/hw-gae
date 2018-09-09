/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;

import hu.hw.cloud.server.entity.hk.HkAssignment;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.reservation.Reservation;
import hu.hw.cloud.server.entity.reservation.RoomStay;
import hu.hw.cloud.server.repository.HkAssignmentRepo;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.ReservationRepository;
import hu.hw.cloud.server.repository.RoomRepository;
import hu.hw.cloud.server.service.HkAssignmentService;
import hu.hw.cloud.shared.cnst.ReservationStatus;
import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.hk.HkAssignmentDto;
import hu.hw.cloud.shared.dto.hk.AssignmentSummaryDto;

/**
 * @author CR
 *
 */
public class HkAssignmentServiceImpl extends CrudServiceImpl<HkAssignment, HkAssignmentRepo>
		implements HkAssignmentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HkAssignmentServiceImpl.class.getName());

	private final HkAssignmentRepo hkAssignmentRepo;
	private final HotelRepository hotelRepository;
	private final RoomRepository roomRepository;
	private final ReservationRepository reservationRepository;

	// @Autowired
	public HkAssignmentServiceImpl(HkAssignmentRepo hkAssignmentRepo, HotelRepository hotelRepository,
			RoomRepository roomRepository, ReservationRepository reservationRepository) {
		super(hkAssignmentRepo);
		LOGGER.info("HkAssignmentServiceImpl");
		this.hkAssignmentRepo = hkAssignmentRepo;
		this.hotelRepository = hotelRepository;
		this.roomRepository = roomRepository;
		this.reservationRepository = reservationRepository;
	}

	@Override
	public List<HkAssignment> getAll(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HkAssignment> getByDateAndHotel(String hotelWebSafeKey, final Date date) {
		List<HkAssignmentDto> assignmentDtos = new ArrayList<HkAssignmentDto>();

		// Begyűjtjük a szálloda adott napi HK beosztásait
		List<HkAssignment> assignments = hkAssignmentRepo.getByDateAndHotel(hotelWebSafeKey, date);
		for (HkAssignment a0 : assignments) {
			assignmentDtos.add(HkAssignment.createDto(a0));
		}

		// Begyűjtjük a szálloda adott napon aktív szobáit
		List<Room> rooms = roomRepository.getByDateAndHotel(hotelWebSafeKey, date);
		// Begyűjtjük a szálloda adott napi lakó foglalásait
		List<Reservation> reservations = reservationRepository.getInHouseReservations(hotelWebSafeKey, date);

		// Végig pásztázzuk a szobákat, ha nincs HK beosztása, akkor létrehozunk
		// egy üreset
		for (final Room room : rooms) {
			// Ha piszkos a szoba, akkor takarítani kell
			if (room.getRoomStatus().equals(RoomStatus.DIRTY)) {
				// Van már beosztása?
				HkAssignmentDto assignmentDto = IterableUtils.find(assignmentDtos,
						new Predicate<HkAssignmentDto>() {
					@Override
							public boolean evaluate(final HkAssignmentDto a2) {
								return a2.getRoomDto().getCode() == room.getCode();
							}
						});
				// Nincs még beosztása, akkor felvesszünk egy üres beosztást
				if (assignmentDto == null) {
					assignmentDto = new HkAssignmentDto();
					assignmentDto.setBusinessDate(date);
// cr					assignmentDto.setHotelDto(Hotel.createDto(room.getHotel()));
					// cr					assignmentDto.setRoomDto(Room.createDto(room));
				}
				// A beosztáshoz hozzáadjuk az érkező és a lakó foglalásokat
				for (Reservation reservation : reservations) {
					List<RoomStay> roomStays = reservation.getRoomStays();
					RoomStay roomStay = IterableUtils.find(roomStays, new Predicate<RoomStay>() {
						public boolean evaluate(RoomStay object) {
							return object.getRoom().equals(room)
									&& (object.getArrival().equals(date)
											|| object.getArrival().after(date))
									&& (object.getDeparture().equals(date)
											|| object.getDeparture().before(date));
						}
					});
					// Érkezik
					if (reservation.getStatus().equals(ReservationStatus.DEFINITIVE)) {
						// cr assignmentDto.addReservationDto(reservation.createDto());
					}
					// Lakó foglalás
					if (reservation.getStatus().equals(ReservationStatus.CHECKED_IN)) {
					}
					//
					if (reservation.getStatus().equals(ReservationStatus.OOO)) {
					}

				}

			}
		}
		return null;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();

		List<Hotel> hotels = hotelRepository.getAll(accountId);
		for (Hotel hotel : hotels) {
			parents.add(hotel);
		}
		return (List<Object>) parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();

		List<Hotel> hotels = hotelRepository.getAll(accountWebSafeKey);
		for (Hotel hotel : hotels) {
			parents.add(hotel);
		}
		return (List<Object>) parents;
	}

	@Override
	public List<AssignmentSummaryDto> getAssignemntSummary(String hotelWebSafeKey, Date date) {
		Hotel hotel = hotelRepository.findByWebSafeKey(hotelWebSafeKey);

		List<Room> rooms = roomRepository.getAll(hotel);

		List<AssignmentSummaryDto> result = new ArrayList<AssignmentSummaryDto>();

		List<HkAssignment> assignments = hkAssignmentRepo.getAll(hotel);
		for (HkAssignment assignment : assignments) {
			if (rooms.contains(assignment.getRoom()))
				rooms.remove(assignment.getRoom());
/*
						AppUserDto attendantDto = AppUser.createDto(assignment.getAttendant());
						AssignmentSummaryDto asd = Utils.findByAttendant(result, attendantDto);

			if (asd != null) {
				asd.addRoom(assignment.getRoom().getRoomStatus());
			} else {
				result.add(new AssignmentSummaryDto(attendantDto, assignment.getRoom().getRoomStatus(), 1));
			}
*/
		}

		if (rooms.size() > 0)
			result.add(unassignedRoomsSummary(rooms));

		return result;
	}

	private AssignmentSummaryDto unassignedRoomsSummary(List<Room> rooms) {
		AssignmentSummaryDto result = new AssignmentSummaryDto(null, new HashMap<RoomStatus, Integer>());
		for (Room room : rooms)
			result.addRoom(room.getRoomStatus());
		return result;
	}
}
