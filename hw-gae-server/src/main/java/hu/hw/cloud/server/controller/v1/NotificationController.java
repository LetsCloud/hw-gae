/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.NOTIFICATION;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.RECONNECT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.push.Subscription;
import hu.hw.cloud.server.push.WebPushAPI;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.shared.rpc.Message;
import hu.hw.cloud.shared.rpc.NotificationDTO;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT + NOTIFICATION, produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController extends BaseController {

	static List<Subscription> subscriptions = new ArrayList<>();

	private final AppUserService userService;

	@Autowired
	NotificationController(AppUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = GET, value = "/{input}")
	public ResponseEntity<Message> getMessage(@PathVariable String input) {
		return new ResponseEntity<Message>(new Message("Hello World"), OK);
	}

	@RequestMapping(method = GET, value = RECONNECT)
	public void reconnect() {
	}

	@RequestMapping(method = POST, value = "/{endpoint}/{auth}/{key}")
	public void subscribeUser(@PathVariable String endpoint, @PathVariable String auth, @PathVariable String key) {
		subscriptions.add(new Subscription(endpoint, auth, key));
	}

	@RequestMapping(method = PUT)
	public void notifyAllUser(@RequestBody NotificationDTO notification) {
		WebPushAPI webPushAPI = new WebPushAPI();
		try {
			for (Subscription subscription : subscriptions) {
				webPushAPI.sendPushMessage(subscription, generatePayload(notification));
			}
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (JoseException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected byte[] generatePayload(NotificationDTO notification) {
		JsonObject object = new JsonObject();
		object.addProperty("title", notification.getTitle());
		object.addProperty("description", notification.getDescription());
		object.addProperty("image", notification.getImage());
		return object.toString().getBytes();
	}
}
