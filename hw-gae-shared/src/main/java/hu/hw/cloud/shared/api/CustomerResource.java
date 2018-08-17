/**
 * 
 */
package hu.hw.cloud.shared.api;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.CUSTOMER;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.dto.profile.CustomerDto;

/**
 * @author robi
 *
 */
@Path(ROOT + CUSTOMER)
@Produces(MediaType.APPLICATION_JSON)
public interface CustomerResource {

	@GET
	List<CustomerDto> list();

	@GET
	@Path(PATH_WEBSAFEKEY)
	CustomerDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	CustomerDto saveOrCreate(CustomerDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
