/**
 * 
 */
package hu.hw.cloud.shared.api;

import static hu.hw.cloud.shared.api.ApiParameters.PASSWORD;
import static hu.hw.cloud.shared.api.ApiParameters.USERNAME;
import static hu.hw.cloud.shared.api.ApiParameters.REMEMBER_ME;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.LOGIN;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.LOGOUT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.CURRENTUSER;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.IS_LOGGED_IN;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
@Path(ROOT)
public interface AuthResource {

	@POST
	@Path(LOGIN)
	@Consumes(APPLICATION_FORM_URLENCODED)
	RestAction<Void> login(@FormParam(USERNAME) String username, @FormParam(PASSWORD) String password,
			@FormParam(REMEMBER_ME) Boolean remeberMe);

	@GET
	@Path(LOGOUT)
	RestAction<Void> logout();

	@GET
	@Path(LOGIN + IS_LOGGED_IN)
	RestAction<Boolean> isCurrentUserLoggedIn();

	@GET
	@Path(LOGIN + CURRENTUSER)
	RestAction<AppUserDto> getCurrentUser();

}
