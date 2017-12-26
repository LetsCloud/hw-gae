/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.List;

import javax.ws.rs.QueryParam;

import static hu.hw.cloud.shared.api.ApiParameters.HOTEL_KEY;
import static hu.hw.cloud.shared.api.ApiParameters.ROOM_KEY;
import static hu.hw.cloud.shared.api.ApiParameters.ROOM_STATUS;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOM;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.AVAILABLE_ON_DATE;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.STATUS_CHANGE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.service.RoomService;
import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.filter.RoomStatusFilterDto;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT + ROOM, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {
//	private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

	private final RoomService roomService;

	@Autowired
	RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@RequestMapping(method = GET)
	public List<RoomDto> getRoomsByHotel(@QueryParam(HOTEL_KEY) String hotelKey) {
		List<Room> allRooms = roomService.getAllRoomsByHotel(hotelKey);
		List<RoomDto> roomDtos = Room.createDtos(allRooms);
		return roomDtos;
	}

	@RequestMapping(method = GET, value = STATUS_CHANGE)
	public ResponseEntity<RoomDto> changeStatus(@QueryParam(ROOM_KEY) String roomKey,
			@QueryParam(ROOM_STATUS) RoomStatus roomStatus) {
		Room room;
		try {
			room = roomService.changeStatus(roomKey, roomStatus);
			RoomDto roomDto = Room.createDto(room);
			// LOGGER.info("changeStatus()->roomDto=" + roomDto);
			return new ResponseEntity<RoomDto>(roomDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<RoomDto>(NOT_FOUND);
		}
	}

	// public List<RoomDto> getAvailableRoomsByHotel(@QueryParam(HOTEL_KEY)
	// String hotelKey,
	// @QueryParam(DATE) @DateTimeFormat(iso = ISO.DATE) Date date, @RequestBody
	// RoomStatusFilterDto filterDto) {

	@RequestMapping(method = POST, value = AVAILABLE_ON_DATE)
	public ResponseEntity<List<RoomDto>> getAvailableRoomsByHotel(@RequestBody RoomStatusFilterDto filterDto) {

		List<Room> allRooms = roomService.getAvailableRoomsByHotelOnDateWithReservations(filterDto.getHotelKey(),
				filterDto.getDate());
		allRooms = Room.filterByRoomStatus(allRooms, filterDto);
		List<RoomDto> roomDtos = Room.createDtos(allRooms);
		return new ResponseEntity<List<RoomDto>>(roomDtos, OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<RoomDto> create(@RequestBody RoomDto roomDto) {
		try {
			Room room = roomService.create(roomDto);
			roomDto = Room.createDto(room);
			return new ResponseEntity<RoomDto>(roomDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<RoomDto>(NOT_FOUND);
		}
	}

	@RequestMapping(method = GET, value = PATH_WEBSAFEKEY)
	public ResponseEntity<RoomDto> read(@PathVariable String webSafeKey) throws Throwable {
		Room room = roomService.read(webSafeKey);
		RoomDto roomDto = Room.createDto(room);
		return new ResponseEntity<RoomDto>(roomDto, OK);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity<RoomDto> update(@RequestBody RoomDto roomDto) {
		try {
			Room room = roomService.update(roomDto);
			roomDto = Room.createDto(room);
			return new ResponseEntity<RoomDto>(roomDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<RoomDto>(NOT_FOUND);
		}
	}

	@RequestMapping(method = DELETE, value = PATH_WEBSAFEKEY)
	public void delete(@PathVariable String webSafeKey) {
		try {
			roomService.delete(webSafeKey);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
