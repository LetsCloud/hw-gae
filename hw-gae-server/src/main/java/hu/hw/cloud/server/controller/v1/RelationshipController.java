/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.RELATIONSHIP;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.modelmapper.ModelMapper;
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

import hu.hw.cloud.server.entity.profile.Relationship;
import hu.hw.cloud.server.service.RelationshipService;
import hu.hw.cloud.shared.dto.profile.RelationshipDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + RELATIONSHIP, produces = MediaType.APPLICATION_JSON_VALUE)
public class RelationshipController extends CrudController<Relationship, RelationshipDto> {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroupController.class);

	private final ModelMapper modelMapper;

	@Autowired
	RelationshipController(RelationshipService service, ModelMapper modelMapper) {
		super(Relationship.class, service, modelMapper);
		logger.info("ProfileGroupController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected RelationshipDto createDto(Relationship entity) {
		RelationshipDto dto = modelMapper.map(entity, RelationshipDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<RelationshipDto>> getAll(@QueryParam(ONLY_ACTIVE) Boolean onlyActive) {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<RelationshipDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<RelationshipDto> saveOrCreate(@RequestBody RelationshipDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
