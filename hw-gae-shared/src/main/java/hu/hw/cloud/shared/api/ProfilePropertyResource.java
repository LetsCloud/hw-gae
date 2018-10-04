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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.dto.profile.ProfilePropertyDto;

import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.PROFILE_PROPERTY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Path(ROOT + PROFILE_PROPERTY)
@Produces(MediaType.APPLICATION_JSON)
public interface ProfilePropertyResource {

	@GET
	List<ProfilePropertyDto> getAll(@QueryParam(ONLY_ACTIVE) Boolean onlyActive);

	@GET
	@Path(PATH_WEBSAFEKEY)
	ProfilePropertyDto get(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	ProfilePropertyDto saveOrCreate(ProfilePropertyDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);

}
