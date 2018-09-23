/**
 * 
 */
package hu.hw.cloud.shared.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.dto.profile.ProfileDtor;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.PROFILE;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

/**
 * @author robi
 *
 */
@Path(ROOT + PROFILE)
@Produces(MediaType.APPLICATION_JSON)
public interface ProfileResource {

	@GET
	List<ProfileDtor> getAll();
}
