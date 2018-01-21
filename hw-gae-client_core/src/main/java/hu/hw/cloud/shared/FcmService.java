/**
 * 
 */
package hu.hw.cloud.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import hu.hw.cloud.shared.dto.NotificationDto;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.FCM;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.SUBSCRIBE;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.MESSAGE;
import static hu.hw.cloud.shared.api.ApiParameters.IID_TOKEN;
import static hu.hw.cloud.shared.api.ApiParameters.USER_AGENT;

/**
 * @author CR
 *
 */
@Path(ROOT + FCM)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface FcmService {

	@POST
	@Path(SUBSCRIBE)
	RestAction<Void> subscribe(@QueryParam(IID_TOKEN) String iidToken, @QueryParam(USER_AGENT) String userAgent);

	@DELETE
	@Path(SUBSCRIBE)
	RestAction<Void> unsubscribeAll();

	@POST
	@Path(MESSAGE)
	RestAction<Void> notifyAllUser(NotificationDto notification);

}
