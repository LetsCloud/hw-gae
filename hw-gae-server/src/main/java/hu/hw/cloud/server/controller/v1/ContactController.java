/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.CONTACT;
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

import hu.hw.cloud.server.entity.profile.Contact;
import hu.hw.cloud.server.service.ContactService;
import hu.hw.cloud.shared.dto.profile.ContactDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + CONTACT, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController extends CrudController<Contact, ContactDto> {
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

	private final ModelMapper modelMapper;

	@Autowired
	ContactController(ContactService service, ModelMapper modelMapper) {
		super(Contact.class, service, modelMapper);
		logger.info("OrganizationController()");
		this.modelMapper = modelMapper;
	}

	@Override
	protected ContactDto createDto(Contact entity) {
		ContactDto dto = modelMapper.map(entity, ContactDto.class);
		return dto;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<ContactDto>> getAll(@QueryParam(ONLY_ACTIVE) Boolean onlyActive) {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<ContactDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("get()->webSafeKey=" + webSafeKey);
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<ContactDto> saveOrCreate(@RequestBody ContactDto dto) throws RestApiException {
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
