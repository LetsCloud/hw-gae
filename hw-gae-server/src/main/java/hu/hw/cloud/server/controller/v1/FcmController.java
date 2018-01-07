/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.FCM;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.SUBSCRIBE;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.MESSAGE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.JsonObject;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.server.service.impl.fcm.FcmService;
import hu.hw.cloud.server.service.impl.fcm.Subscription;
import hu.hw.cloud.shared.dto.NotificationDto;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT + FCM, produces = MediaType.APPLICATION_JSON_VALUE)
public class FcmController extends BaseController {
	private static final Logger log = Logger.getLogger(FcmController.class.getName());	

	static List<Subscription> subscriptions = new ArrayList<Subscription>();

	private final AppUserService userService;

	@Autowired
	FcmController(AppUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = SUBSCRIBE, params = { "iidToken" }, method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void subscribe(@RequestParam String iidToken) {
		log.info("subscribe(" + iidToken + ")");
		subscriptions.add(new Subscription(iidToken));
	}

	@RequestMapping(value = SUBSCRIBE, method = DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void unsubscribeAll() {
		log.info("unsubscribeAll()");
		subscriptions.clear();
	}

	@RequestMapping(value = MESSAGE, method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void notifyAllUser(@RequestBody NotificationDto notification) {
		log.info("notifyAllUser()-START");
		FcmService fcmService = new FcmService();
		try {
			for (Subscription subscription : subscriptions) {
				fcmService.sendMessage2(generatePayload(notification, subscription.getIidToken()));
			}
			log.info("notifyAllUser()-END");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param notification
	 * @param to
	 * @return
	 */
	protected String generatePayload(NotificationDto notification, String to) {
		JsonObject message = new JsonObject();
		message.addProperty("title", notification.getTitle());
		message.addProperty("body", notification.getBody());
		message.addProperty("icon", notification.getIcon());
		message.addProperty("click_action", notification.getAction());

		JsonObject object = new JsonObject();
		object.add("notification", message);
		object.addProperty("to", to);

		return object.toString();
	}
}
