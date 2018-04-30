/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hu.hw.cloud.server.api.v1.BaseController;
import hu.hw.cloud.server.entity.BaseEntity;
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

	private final CrudService<T, D> service;

	public CrudController(CrudService<T, D> service) {
		logger.info("CrudController()");
		this.service = service;
	}

	public CrudService<T, D> getService() {
		return service;
	}

	abstract protected D createDto(T entity);

	public ResponseEntity<List<D>> getAll() {
		List<D> dtos = new ArrayList<D>();

		logger.info("CrudController().getAll()");
		AppUser appUser = userService.getCurrentUser();
		if (appUser == null)
			return new ResponseEntity<List<D>>(dtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		logger.info("CrudController().getAll()-2");
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<D>>(dtos, OK);

		logger.info("CrudController().getAll()-3");
		for (T entity : service.getChildren(accountWebSafeKey)) {
			dtos.add(createDto(entity));
		}

		return new ResponseEntity<List<D>>(dtos, OK);
	}

	public ResponseEntity<D> get(String webSafeKey) throws RestApiException {
		try {
			T entity = service.read(webSafeKey);
			D dto = createDto(entity);
			return new ResponseEntity<D>(dto, HttpStatus.OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	public ResponseEntity<D> saveOrCreate(D dto) throws RestApiException {
		try {
			T entity;
			if (dto.getId() == null) {
				entity = service.create(dto);
			} else {
				entity = service.update(dto);
			}
			dto = createDto(entity);
			return new ResponseEntity<D>(dto, HttpStatus.OK);
		} catch (Throwable e) {
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
		logger.info("CrudController().getChildren()->parentWebSafeKey=" + parentWebSafeKey);
		List<D> dtos = new ArrayList<D>();
		if (parentWebSafeKey == null)
			return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);

		logger.info("CrudController().getChildren()-2");
		for (T entity : service.getChildren(parentWebSafeKey)) {
			logger.info("CrudController().getChildren().entity.getWebSafeKey()=" + entity.getWebSafeKey());
			dtos.add(createDto(entity));
		}
		logger.info("CrudController().getChildren()-3");
		return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);
	}

	public ResponseEntity<List<D>> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters) {
		List<D> dtos = new ArrayList<D>();
		if (parentWebSafeKey == null)
			return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);

		for (T entity : service.getChildrenByFilters(parentWebSafeKey, filters)) {
			logger.info("CrudController().getChildrenByFilters()->entity.getId()=" + entity.getId());
			dtos.add(createDto(entity));
		}
		return new ResponseEntity<List<D>>(dtos, HttpStatus.OK);
	}

}
