package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.USER;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.INVITE;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.security.OnRegistrationCompleteEvent;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.exception.RestApiException;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = ROOT + USER, produces = MediaType.APPLICATION_JSON_VALUE)
public class AppUserController extends CrudController<AppUser, AppUserDto> {
	private static final Logger logger = Logger.getLogger(AppUserController.class.getName());

	private final AppUserService service;

	private final ApplicationEventPublisher eventPublisher;

	private final ModelMapper modelMapper;

	@Autowired
	AppUserController(AppUserService service, ApplicationEventPublisher eventPublisher, ModelMapper modelMapper) {
		super(AppUser.class, service, modelMapper);
		logger.info("AppUserController()");
		this.service = service;
		this.eventPublisher = eventPublisher;
		this.modelMapper = modelMapper;
	}

	@Override
	protected AppUserDto createDto(AppUser entity) {
		AppUserDto dto = modelMapper.map(entity, AppUserDto.class);
		return dto;
	}

	@Override
	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<AppUserDto>> getAll() {
		return super.getAll();
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<AppUserDto> get(@PathVariable String webSafeKey) throws RestApiException {
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<AppUserDto> saveOrCreate(@RequestBody AppUserDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@RequestMapping(value = INVITE, method = POST)
	public ResponseEntity<AppUserDto> invite(@RequestBody AppUserDto userDto, WebRequest request) {
		try {
			String appUrl = request.getContextPath();
			AppUser user = modelMapper.map(userDto, AppUser.class);
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
			return new ResponseEntity<AppUserDto>(userDto, OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<AppUserDto>(NOT_FOUND);
		}
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

	@RequestMapping(value = "/activate/{token}", method = GET)
	@ResponseBody
	public Boolean activate(@PathVariable String token) {
		try {
			service.activate(token);
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
	}

}
