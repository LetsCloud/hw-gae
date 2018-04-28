/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import com.googlecode.objectify.Work;

import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.hotel.RoomAvailability;
import hu.hw.cloud.server.entity.reservation.Reservation;
import hu.hw.cloud.server.entity.reservation.RoomStay;
import hu.hw.cloud.server.repository.ReservationRepository;
import hu.hw.cloud.server.repository.RoomRepository;
import hu.hw.cloud.server.service.RoomService;
import hu.hw.cloud.shared.cnst.FoRoomStatus;
import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
//@Service
public class RoomServiceImpl extends CrudServiceImpl<Room, RoomDto, RoomRepository> implements RoomService {
//	private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class.getName());

	private ReservationRepository reservationRepository;

	//@Autowired
	public RoomServiceImpl(RoomRepository repository, ReservationRepository reservationRepository) {
		super(repository);
//		LOGGER.info("RoomServiceImpl");
		this.reservationRepository = reservationRepository;
	}

	@Override
	public List<Room> getAll(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Room createEntity(RoomDto dto) {
		return new Room(dto);
	}

	@Override
	protected Room updateEntity(Room entity, RoomDto dto) {
		entity.update(dto);
		return null;
	}

	@Override
	protected Room updateEntity(Room oldEntity, Room newEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAllRoomsByHotel(String hotelKey) {
		List<Room> allRooms = repository.getAllByHotel(hotelKey);
		return allRooms;
	}

	@Override
	public List<Room> getAvailableRoomsByHotelOnDate(String hotelKey, Date date) {
		// LOGGER.info("getAvailableRoomsByHotel()->date:" + date.toString());
		// A szálloda összes szobája
		List<Room> rooms = getAllRoomsByHotel(hotelKey);
		// Rendelkezésre nem álló szobák
		List<Room> unavailableRooms = new ArrayList<Room>();
		// Végigpásztázzuk az összes szobát, hogy megállapítsuk a rendelkezésre
		// állásukat
		for (Room room : rooms) {
			// LOGGER.info("getAvailableRoomsByHotel()->allRoom:" +
			// room.getCode());
			List<RoomAvailability> roomAvailabilities = room.getRoomAvailabilities();
			// LOGGER.info("getAvailableRoomsByHotel()->roomAvailabilities.size()="
			// + roomAvailabilities.size());
			// for (RoomAvailability ra : roomAvailabilities) {
			// LOGGER.info("getAvailableRoomsByHotel()->date(unOrdered)=" +
			// ra.getDate());
			// }
			// A rendelkezésre állások listáját dátum szerint rendezzük
			Collections.sort(roomAvailabilities, RoomAvailability.ORDER_BY_DATE);
			// for (RoomAvailability ra : roomAvailabilities) {
			// LOGGER.info("getAvailableRoomsByHotel()->date(ordered)=" +
			// ra.getDate());
			// }
			// Megizsgáljuk, hogy az adott szoba a megadott napon rendelkezésre
			// áll e
			if (!isAvailableRoom(roomAvailabilities, date))
				unavailableRooms.add(room);
		}
		// Töröljük a rendelkezésre nem álló szobákat
		if (!unavailableRooms.isEmpty())
			rooms.removeAll(unavailableRooms);

		// for (Room room : rooms) {
		// LOGGER.info("getAvailableRoomsByHotel()->room=" + room.getCode());
		// }

		return rooms;
	}

	/**
	 * Megállapítja, hogy rendelkezésre áll e a szoba
	 * 
	 * @param roomOpenings
	 * @param date
	 * @return
	 */
	private Boolean isAvailableRoom(List<RoomAvailability> roomAvailabilies, Date date) {
		// LOGGER.info("isAvailableRoom()->Date=" + date.toString());
		// Alapból nem áll rendelkezésre a szoba
		Boolean available = false;
		for (RoomAvailability roomAvailability : roomAvailabilies) {
			// LOGGER.info("isAvailableRoom()->roomAvailability.getDate()=" +
			// roomAvailability.getDate().toString());
			if (roomAvailability.getDate().before(date)) {
				// Megjegyezzük a rendelkezésre állási állapotot
				available = roomAvailability.isAvailable();
				// LOGGER.info("isAvailableRoom()->before->available=" +
				// available);
			} else {
				// LOGGER.info("isAvailableRoom()->after or equals->available="
				// + available);
				if (roomAvailability.getDate().equals(date)) {
					available = roomAvailability.isAvailable();
					// LOGGER.info("isAvailableRoom()->equals->available=" +
					// available);
				}
				// Ha túl vagyunk a vizsgált napon, akkor visszaadjuk az eddig
				// jegyzett rendelkezésre állási állapotot
				return available;
			}
		}
		return available;
	}

	@Override
	public Room changeStatus(final String roomKey, final RoomStatus roomStatus) throws Throwable {
		try {
			// Objectify tranzakció indul
			Room th = ofy().transact(new Work<Room>() {
				public Room run() {
					Room entity = repository.findByWebSafeKey(roomKey);
					try {
						// LOGGER.info("changeStatus()->roomStatus=" +
						// roomStatus);
						entity.setRoomStatus(roomStatus);
						entity = repository.save(entity);
						return entity;
					} catch (Throwable e) {
						e.printStackTrace(System.out);
						throw new RuntimeException(e);
					}
				}
			});
			return th;
		} catch (RuntimeException re) {
			// A csomagolt kivételt elcsípjük és továbbküldjük
			throw re.getCause();
		}
	}

	@Override
	public List<Room> getAvailableRoomsByHotelOnDateWithReservations(String hotelKey, Date date) {
		List<Reservation> reservations = reservationRepository.getInHouseReservations(hotelKey, date);
		List<Room> rooms = getAvailableRoomsByHotelOnDate(hotelKey, date);

		for (Room room : rooms) {
			room.setOccupied(false);
			room.setFoRoomStatus(FoRoomStatus.NOT_RESERVED);

			List<Reservation> filteredReservations = Reservation.filterByRoom(reservations, room);
			if ((filteredReservations != null) && (filteredReservations.size() > 0)) {
				for (Reservation r : filteredReservations) {
					if (r.getRoomStays() != null) {
						for (RoomStay rs : r.getRoomStays()) {
							if (rs.getRoom().equals(room)) {
								switch (r.getStatus()) {
								case PROSPECT:
									// Még nem érkezett meg
									room.setFoRoomStatus(FoRoomStatus.ARRIVALS);
									break;
								case TENTATIVE:
									// Még nem érkezett meg
									room.setFoRoomStatus(FoRoomStatus.ARRIVALS);
									break;
								case DEFINITIVE:
									// Még nem érkezett meg
									room.setFoRoomStatus(FoRoomStatus.ARRIVALS);
									break;
								case CHECKED_IN:
									// Már megérkezett
									room.setOccupied(true);
									if (rs.getArrival().equals(rs.getDeparture())) {
										room.setFoRoomStatus(FoRoomStatus.DAY_USE);
									} else if (rs.getArrival().equals(date)) {
										room.setFoRoomStatus(FoRoomStatus.ARRIVED);
									} else if (rs.getDeparture().equals(date)) {
										room.setFoRoomStatus(FoRoomStatus.DUE_OUT);
									} else {
										room.setFoRoomStatus(FoRoomStatus.STAYOVER);
									}
									break;
								case CHECKED_OUT:
									// Elutazott
									room.setFoRoomStatus(FoRoomStatus.DEPARTED);
									break;
								case OUTSTANDING:
									// Elutazott
									room.setFoRoomStatus(FoRoomStatus.DEPARTED);
									break;
								case CANCELLED:
									// Elutazott
									if (room.getFoRoomStatus() == null)
										room.setFoRoomStatus(FoRoomStatus.NOT_RESERVED);
									break;
								default:
									if (room.getFoRoomStatus() == null)
										room.setFoRoomStatus(FoRoomStatus.NOT_RESERVED);
									break;
								}
							}
						}
					}
				}
			}
		}
		return rooms;
	}
}
