package hu.hw.cloud.shared;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import static hu.hw.cloud.shared.api.ApiParameters.TOKEN;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.USER;

import java.util.List;

import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.INVITE;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import hu.hw.cloud.shared.dto.common.AppUserDto;

@Path(ROOT + USER)
@Produces(MediaType.APPLICATION_JSON)
public interface AppUserResource {

	@GET
	List<AppUserDto> list();

	@POST
	AppUserDto create(AppUserDto userDto);

	@GET
	@Path(PATH_WEBSAFEKEY)
	AppUserDto read(@PathParam(WEBSAFEKEY) String webSafeKey);

	@PUT
	AppUserDto update(AppUserDto userDto);

	@DELETE
	@Path(PATH_WEBSAFEKEY)
	void delete(@PathParam(WEBSAFEKEY) String webSafeKey);

	@POST
	@Path(INVITE)
	AppUserDto invite(AppUserDto userDto);

	@GET
	@Path("/activate/{token}")
	RestAction<Boolean> activate(@PathParam(TOKEN) String token);

}
