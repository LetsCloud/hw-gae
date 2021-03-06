/**
 * 
 */
package hu.hw.cloud.shared.api;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOMTYPE;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiParameters.HOTEL_KEY;
import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.SEL_INV_TYPE;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.cnst.InventoryType;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
@Path(ROOT + ROOMTYPE)
@Produces(MediaType.APPLICATION_JSON)
public interface RoomTypeResource {

	@GET
	List<RoomTypeDto> getAll(@QueryParam(HOTEL_KEY) String hotelKey, @QueryParam(ONLY_ACTIVE) Boolean onlyActive,
			@QueryParam(SEL_INV_TYPE) InventoryType type);

	@GET
	@Path(PATH_WEBSAFEKEY)
	RoomTypeDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	RoomTypeDto saveOrCreate(RoomTypeDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
