/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.FCM;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.SUBSCRIBE;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.MESSAGE;
import static hu.hw.cloud.shared.api.ApiParameters.IID_TOKEN;
import static hu.hw.cloud.shared.api.ApiParameters.USER_AGENT;
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
import org.springframework.web.context.request.WebRequest;

import com.google.gson.JsonObject;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.entity.chat.FcmToken;
import hu.hw.cloud.server.entity.common.AppUser;
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

	@RequestMapping(value = SUBSCRIBE, params = { IID_TOKEN, USER_AGENT }, method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void subscribe(@RequestParam String iidToken, @RequestParam String userAgent) {
		log.info("subscribe(IID_TOKEN=" + iidToken + ", USER_AGENT=" + userAgent + ")");
		try {
			userService.fcmSubscribe(iidToken, userAgent);
		} catch (Throwable e) {
			log.info("subscribe->catch Exeption->" + e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping(value = SUBSCRIBE, method = DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void unsubscribeAll() {
		log.info("unsubscribeAll()");
		subscriptions.clear();
	}

	@RequestMapping(value = MESSAGE, method = POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void notifyAllUser(@RequestBody NotificationDto notification, WebRequest request) {
		log.info("notifyAllUser()-START");

		List<String> tokens = getTokens(userService.getAll(getAccountId(request)));
		FcmService fcmService = new FcmService();
		try {
			for (String token : tokens) {
				fcmService.sendMessage2(generatePayload(notification, token));
			}
			log.info("notifyAllUser()-END");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> getTokens(List<AppUser> appUsers) {
		List<String> tokens = new ArrayList<String>();

		for (AppUser au : appUsers) {
			for (FcmToken t : au.getFcmTokens()) {
				if (!tokens.contains(t.getToken()))
					tokens.add(t.getToken());
			}
		}
		return tokens;
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
