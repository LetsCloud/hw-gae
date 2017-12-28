/**
 * 
 */
package hu.hw.cloud.shared;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.RECONNECT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.NOTIFICATION;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gwtplatform.dispatch.rest.shared.RestAction;

import hu.hw.cloud.shared.rpc.Message;
import hu.hw.cloud.shared.rpc.NotificationDTO;

/**
 * @author CR
 *
 */
@Path(ROOT + NOTIFICATION)
@Produces(MediaType.APPLICATION_JSON)
public interface NotificationService {

	@GET
	@Path("/{input}")
	RestAction<Message> getMessage(@PathParam("input") String input);

	@POST
	@Path("/{endpoint}/{auth}/{key}")
	RestAction<Void> subscribeUser(@PathParam("endpoint") String endpoint, @PathParam("auth") String auth,
			@PathParam("key") String key);

	@POST
	@Path(RECONNECT)
	RestAction<Void> reconnect();

	@PUT
	RestAction<Void> notifyAllUser(NotificationDTO notification);

}
