/**
 * 
 */
package hu.hw.cloud.shared.api;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ACCOUNT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import hu.hw.cloud.shared.dto.RegisterDto;

/**
 * @author CR
 *
 */
@Path(ROOT)
public interface AccountResource {

	@POST
	@Path(ACCOUNT)
	@Consumes(APPLICATION_JSON)
	RestAction<RegisterDto> register(RegisterDto register);

}
