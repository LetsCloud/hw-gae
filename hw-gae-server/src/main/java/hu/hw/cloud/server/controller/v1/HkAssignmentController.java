/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.User.ROOT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT, produces = MediaType.APPLICATION_JSON_VALUE)
public class HkAssignmentController {
	private final Logger logger = LoggerFactory.getLogger(HkAssignmentController.class);

}
