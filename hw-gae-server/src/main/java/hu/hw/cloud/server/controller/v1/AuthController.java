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
import hu.hw.cloud.server.entity.MyMapper;
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
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	private final AppUserService userService;

	private final MyMapper modelMapper;

	@Autowired
	AuthController(AppUserService userService, MyMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@RequestMapping(method = GET, value = IS_LOGGED_IN)
	@PermitAll
	ResponseEntity<Boolean> isCurrentUserLoggedIn() {
		logger.info("isCurrentUserLoggedIn()");
		return new ResponseEntity<Boolean>(userService.isCurrentUserLoggedIn(), OK);
	}

	@RequestMapping(method = GET, value = CURRENTUSER)
	ResponseEntity<AppUserDto> getCurrentUser() {
		logger.info("getCurrentUser()");
		AppUser appUser = userService.getCurrentUser();
		logger.info("getCurrentUser()->appUser=");
		AppUserDto appUserDto = modelMapper.map(appUser, AppUserDto.class);
		logger.info("getCurrentUser()->appUserDto=" + appUserDto);
		return new ResponseEntity<AppUserDto>(appUserDto, HttpStatus.OK);
	}

}
