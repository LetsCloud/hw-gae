/**
 * 
 */
package hu.hw.cloud.shared.api;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.USER_GROUP;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import hu.hw.cloud.shared.dto.ErrorResponseDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
@Path(ROOT + USER_GROUP)
@Produces(MediaType.APPLICATION_JSON)
public interface UserGroupResource {

	@GET
	List<UserGroupDto> list();

	@POST
	UserGroupDto create(UserGroupDto dto);

	@GET
	@Path(PATH_WEBSAFEKEY)
	UserGroupDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@PUT
	UserGroupDto update(UserGroupDto dto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);

	@HEAD
	ErrorResponseDto error();

}
