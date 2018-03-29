/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.LOGIN;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.CURRENTUSER;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.IS_LOGGED_IN;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.annotation.security.PermitAll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT + LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	private final AppUserService userService;

	@Autowired
	AuthController(AppUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = GET, value = IS_LOGGED_IN)
	@PermitAll
	ResponseEntity<Boolean> isCurrentUserLoggedIn() {
		LOGGER.info("isCurrentUserLoggedIn()");
		return new ResponseEntity<Boolean>(userService.isCurrentUserLoggedIn(), OK);
	}

	@RequestMapping(method = GET, value = CURRENTUSER)
	ResponseEntity<AppUserDto> getCurrentUser() {
		AppUserDto appUserDto = AppUser.createDto(userService.getCurrentUser());
		LOGGER.info("getCurrentUser()->" + appUserDto);
		return new ResponseEntity<AppUserDto>(appUserDto, HttpStatus.OK);
	}

}
