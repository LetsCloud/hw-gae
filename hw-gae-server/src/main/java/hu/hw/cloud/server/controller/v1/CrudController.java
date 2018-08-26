/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.entity.MyMapper;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.server.service.CrudService;
import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
public abstract class CrudController<T extends BaseEntity, D extends BaseDto> extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CrudController.class);

	@Autowired
	private AppUserService userService;

	private final CrudService<T> service;

	private final ModelMapper modelMapper;

	private final Class<T> clazz;

	public CrudController(Class<T> clazz, CrudService<T> service, ModelMapper modelMapper) {
		logger.info("CrudController()");
		this.service = service;
		this.modelMapper = modelMapper;
		this.clazz = clazz;
	}

	public CrudService<T> getService() {
		return service;
	}

	abstract protected D createDto(T entity);

	public ResponseEntity<List<D>> getAll() {
		List<D> dtos = new ArrayList<D>();

		AppUser appUser = userService.getCurrentUser();
		if (appUser == null)
			return new ResponseEntity<List<D>>(dtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<D>>(dtos, OK);

		for (T entity : service.getChildren(accountWebSafeKey))
			dtos.add(createDto(entity));

		return new ResponseEntity<List<D>>(dtos, OK);
	}

	public ResponseEntity<D> get(String webSafeKey) throws RestApiException {
		logger.info("CrudController().get()-1");
		try {
			logger.info("CrudController().get()-2");
			T entity = service.read(webSafeKey);
			logger.info("CrudController().get()-3");
			D dto = createDto(entity);
			logger.info("CrudController().get()-4");
			return new ResponseEntity<D>(dto, HttpStatus.OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	public ResponseEntity<D> saveOrCreate(D dto) throws RestApiException {
		logger.info("saveOrCreate->source=" + dto);
		try {
			T entity;
			if (dto.getId() == null) {
				entity = service.create(modelMapper.map(dto, clazz));
			} else {
				entity = service.update(modelMapper.map(dto, clazz));
			}
			logger.info("saveOrCreate->saved=" + entity);
			dto = createDto(entity);
			logger.info("saveOrCreate->saved2=" + dto);
			return new ResponseEntity<D>(dto, HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RestApiException(e);
		}
	}

	public void delete(String webSafeKey) throws RestApiException {
		try {
			service.delete(webSafeKey);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	public ResponseEntity<List<D>> getChildren(String parentWebSafeKey) {
		List<D> dtos = new ArrayList<D>();
		if (parentWebSafeKey == null)
			return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);

		for (T entity : service.getChildren(parentWebSafeKey))
			dtos.add(createDto(entity));

		return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);
	}

	public ResponseEntity<List<D>> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters) {
		List<D> dtos = new ArrayList<D>();
		if (parentWebSafeKey == null)
			return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);

		for (T entity : service.getChildrenByFilters(parentWebSafeKey, filters))
			dtos.add(createDto(entity));

		return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);
	}

}
