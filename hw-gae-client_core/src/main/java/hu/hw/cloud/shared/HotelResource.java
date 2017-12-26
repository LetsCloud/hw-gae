/**
 * 
 */
package hu.hw.cloud.shared;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.HOTEL;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
@Path(ROOT + HOTEL)
@Produces(MediaType.APPLICATION_JSON)
public interface HotelResource {

	@GET
	@Path("")
	List<HotelDto> list();

	@POST
	HotelDto create(HotelDto dto);

	@GET
	@Path(PATH_WEBSAFEKEY)
	HotelDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@PUT
	HotelDto update(HotelDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
