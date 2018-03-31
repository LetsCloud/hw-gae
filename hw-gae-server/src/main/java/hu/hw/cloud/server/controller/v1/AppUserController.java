package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.USER;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.INVITE;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.security.OnRegistrationCompleteEvent;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.shared.dto.common.AppUserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = ROOT + USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class AppUserController extends BaseController {
	private static final Logger logger = Logger.getLogger(AppUserController.class.getName());

	private final AppUserService userService;

	private final ApplicationEventPublisher eventPublisher;

	@Autowired
	AppUserController(AppUserService userService, ApplicationEventPublisher eventPublisher) {
		this.userService = userService;
		this.eventPublisher = eventPublisher;
	}

	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<AppUserDto>> list() {
		List<AppUserDto> userDtos = new ArrayList<AppUserDto>();

		AppUser appUser = userService.getCurrentUser();
		if (appUser == null)
			return new ResponseEntity<List<AppUserDto>>(userDtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<AppUserDto>>(userDtos, OK);

		for (AppUser user : userService.getAll(accountWebSafeKey)) {
			userDtos.add(AppUser.createDto(user));
		}
		return new ResponseEntity<List<AppUserDto>>(userDtos, OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<AppUserDto> create(@RequestBody AppUserDto userDto) {
		try {
			logger.info("create->try->userDto=" + userDto);
			AppUser user = userService.create(userDto);
			userDto = AppUser.createDto(user);
			logger.info("create->try2->userDto=" + userDto);
			return new ResponseEntity<AppUserDto>(userDto, OK);
		} catch (Throwable e) {
			logger.info("create->catch (Throwable e)->" +e.toString());
			e.printStackTrace();
			return new ResponseEntity<AppUserDto>(NOT_FOUND);
		}
	}

	@RequestMapping(value = INVITE, method = POST)
	public ResponseEntity<AppUserDto> invite(@RequestBody AppUserDto userDto, WebRequest request) {
		try {
			String appUrl = request.getContextPath();
			eventPublisher
					.publishEvent(new OnRegistrationCompleteEvent(new AppUser(userDto), request.getLocale(), appUrl));
			return new ResponseEntity<AppUserDto>(userDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<AppUserDto>(NOT_FOUND);
		}
	}

	@RequestMapping(value = "{"+WEBSAFEKEY+"}", method = GET)
	public ResponseEntity<AppUserDto> read(@PathVariable String webSafeKey) throws Throwable {
		AppUser user = userService.read(webSafeKey);
		AppUserDto userDto = AppUser.createDto(user);
		return new ResponseEntity<AppUserDto>(userDto, OK);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity<AppUserDto> update(@RequestBody AppUserDto userDto) {
		try {
			AppUser user = userService.update(userDto);
			userDto = AppUser.createDto(user);
			return new ResponseEntity<AppUserDto>(userDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<AppUserDto>(NOT_FOUND);
		}
	}

	@RequestMapping(method = DELETE, value = WEBSAFEKEY)
	public void delete(@PathVariable String webSafeKey) {
		// TODO Auto-generated method stub

	}

	@RequestMapping(value = "/activate/{token}", method = GET)
	@ResponseBody
	public Boolean activate(@PathVariable String token) {
		try {
			userService.activate(token);
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

}
