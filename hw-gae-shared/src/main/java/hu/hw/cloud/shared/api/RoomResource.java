/**
 * 
 */
package hu.hw.cloud.shared.api;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.List;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiParameters.HOTEL_KEY;
import static hu.hw.cloud.shared.api.ApiParameters.ROOM_KEY;
import static hu.hw.cloud.shared.api.ApiParameters.ROOM_STATUS;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOM;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.AVAILABLE_ON_DATE;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.STATUS_CHANGE;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.filter.RoomStatusFilterDto;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
@Path(ROOT + ROOM)
@Produces(MediaType.APPLICATION_JSON)
public interface RoomResource {

	@GET
	List<RoomDto> getByHotel(@QueryParam(HOTEL_KEY) String hotelKey);

	@GET
	@Path(PATH_WEBSAFEKEY)
	RoomDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	RoomDto saveOrCreate(RoomDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);

	@GET
	@Path(STATUS_CHANGE)
	RoomDto changeRoomStatus(@QueryParam(ROOM_KEY) String roomKey, @QueryParam(ROOM_STATUS) RoomStatus roomStatus);

	@POST
	@Path(AVAILABLE_ON_DATE)
	List<RoomDto> getAvailableRoomsOnDate(RoomStatusFilterDto filterDto);
}
