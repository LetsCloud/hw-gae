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

import hu.hw.cloud.shared.dto.profile.ContactDto;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.CONTACT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Path(ROOT + CONTACT)
@Produces(MediaType.APPLICATION_JSON)
public interface ContactResource {

	@GET
	List<ContactDto> list();

	@GET
	@Path(PATH_WEBSAFEKEY)
	ContactDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	ContactDto saveOrCreate(ContactDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);
}
