/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hu.hw.cloud.server.entity.profile.ProfileGroup;
import hu.hw.cloud.server.service.ProfileGroupService;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;
import hu.hw.cloud.shared.exception.RestApiException;

import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.PROFILE_GROUP;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + PROFILE_GROUP, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileGroupController extends CrudController<ProfileGroup, ProfileGroupDto> {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroupController.class);

	@Autowired
	ProfileGroupController(ProfileGroupService service) {
		super(service);
		logger.info("ProfileGroupController()");
	}

	@Override
	protected ProfileGroupDto createDto(ProfileGroup entity) {
		return ProfileGroup.createDto(entity);
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<ProfileGroupDto>> getAll(@QueryParam(ONLY_ACTIVE) Boolean onlyActive) {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<ProfileGroupDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<ProfileGroupDto> saveOrCreate(@RequestBody ProfileGroupDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
