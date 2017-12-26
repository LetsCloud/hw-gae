/**
 * 
 */
package hu.hw.cloud.server.api.v1;

import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.API;
import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.ROOM;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.annotation.security.PermitAll;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = API, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomApiController {

	@RequestMapping(value = ROOM, method = GET)
	@PermitAll
	ResponseEntity<Boolean> isCurrentUserLoggedIn() {
		return new ResponseEntity<>(true, OK);
	}

}
