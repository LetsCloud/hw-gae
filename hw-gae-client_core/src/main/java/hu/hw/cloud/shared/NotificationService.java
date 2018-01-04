/**
 * 
 */
package hu.hw.cloud.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import hu.hw.cloud.shared.rpc.Message;
import hu.hw.cloud.shared.rpc.NotificationDTO;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.NOTIFICATION;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.RECONNECT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.MESSAGE;
import static hu.hw.cloud.shared.api.ApiParameters.ENDPOINT;
import static hu.hw.cloud.shared.api.ApiParameters.AUTH;
import static hu.hw.cloud.shared.api.ApiParameters.KEY;

/**
 * @author CR
 *
 */
@Path("/spa/v1/notification")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface NotificationService {

	@GET
	@Path("")
	RestAction<Void> subscribeUser(@QueryParam(ENDPOINT) String endpoint, @QueryParam(AUTH) String auth,
			@QueryParam(KEY) String key);

	@GET
	@Path(MESSAGE + "/{input}")
	@Produces(MediaType.APPLICATION_JSON)
	RestAction<Message> getMessage(@PathParam("input") String input);

	@GET
	@Path(RECONNECT)
	RestAction<Void> reconnect();

	@PUT
	RestAction<Void> notifyAllUser(NotificationDTO notification);

}
