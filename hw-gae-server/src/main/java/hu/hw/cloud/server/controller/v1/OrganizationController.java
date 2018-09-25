/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import java.util.ArrayList;
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

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.entity.profile.Organization;
import hu.hw.cloud.server.service.OrganizationService;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;
import hu.hw.cloud.shared.dto.profile.OrganizationDtor;
import hu.hw.cloud.shared.exception.RestApiException;

import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ORGANIZATION;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + ORGANIZATION, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController extends CrudController<Organization, OrganizationDto> {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	private final ModelMapper modelMapper;

	@Autowired
	OrganizationController(OrganizationService service, ModelMapper modelMapper) {
		super(Organization.class, service, modelMapper);
		logger.info("OrganizationController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected OrganizationDto createDto(Organization entity) {
		OrganizationDto dto = modelMapper.map(entity, OrganizationDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<OrganizationDtor>> getAll(@QueryParam(ONLY_ACTIVE) Boolean onlyActive) {
		List<OrganizationDtor> dtos = new ArrayList<OrganizationDtor>();

		AppUser appUser = userService.getCurrentUser();
		if (appUser == null)
			return new ResponseEntity<List<OrganizationDtor>>(dtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<OrganizationDtor>>(dtos, OK);

		for (Organization entity : service.getChildren(accountWebSafeKey))
			dtos.add(modelMapper.map(entity, OrganizationDtor.class));

		return new ResponseEntity<List<OrganizationDtor>>(dtos, OK);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<OrganizationDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("get()->webSafeKey=" + webSafeKey);
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<OrganizationDto> saveOrCreate(@RequestBody OrganizationDto dto) throws RestApiException {
		logger.info("OrganizationController().saveOrCreate(" + dto + ")");
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
