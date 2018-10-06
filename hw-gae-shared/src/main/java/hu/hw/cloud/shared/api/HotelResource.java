/**
 * 
 */
package hu.hw.cloud.shared.api;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.dto.hotel.HotelDtor;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.REDUCED;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.HOTEL;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author CR
 *
 */
@Path(ROOT + HOTEL)
@Produces(MediaType.APPLICATION_JSON)
public interface HotelResource {

	@GET
	List<HotelDto> list();

	@GET
	@Path(REDUCED)
	List<HotelDtor> listReduced();

	@GET
	@Path(PATH_WEBSAFEKEY)
	HotelDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	HotelDto saveOrCreate(HotelDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
