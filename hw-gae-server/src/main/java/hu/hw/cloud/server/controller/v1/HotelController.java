/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.HOTEL;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.server.service.HotelService;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT + HOTEL, produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelController.class);

	private final HotelService hotelService;
	private final AppUserService userService;

	@Autowired
	HotelController(HotelService hotelService, AppUserService userService) {
		this.hotelService = hotelService;
		this.userService = userService;
	}

	/**
	 * Visszaadja a bejelentkezett felhasználó előfizetéséhez tartozó összes
	 * szállodát
	 * 
	 * @return
	 */
	@RequestMapping(method = GET)
	public @ResponseBody ResponseEntity<List<HotelDto>> list() {
		LOGGER.info("list()");
		List<HotelDto> dtos = new ArrayList<HotelDto>();

		AppUser appUser = userService.getCurrentUser();
		LOGGER.info("list()->appUser=" + appUser);
		if (appUser == null)
			return new ResponseEntity<List<HotelDto>>(dtos, OK);

		String accountWebSafeKey = appUser.getAccount().getWebSafeKey();
		LOGGER.info("list()->accountWebSafeKey=" + accountWebSafeKey);
		if (accountWebSafeKey == null)
			return new ResponseEntity<List<HotelDto>>(dtos, OK);

		LOGGER.info("list()-2");

		for (Hotel entity : hotelService.getAll(accountWebSafeKey)) {
			LOGGER.info("list()->entity.getCode()=" + entity.getCode());
			dtos.add(Hotel.createDto(entity));
		}

		return new ResponseEntity<List<HotelDto>>(dtos, OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<HotelDto> create(@RequestBody HotelDto dto) {

		try {
			LOGGER.info("create->try->dto=" + dto);
			Hotel entity = hotelService.create(dto);
			dto = Hotel.createDto(entity);
			return new ResponseEntity<HotelDto>(dto, OK);
		} catch (Throwable e) {
			LOGGER.info("create->catch (Throwable e)");
			e.printStackTrace();
			return new ResponseEntity<HotelDto>(NOT_FOUND);
		}
	}

	@RequestMapping(value = "{" + WEBSAFEKEY + "}", method = GET)
	public ResponseEntity<HotelDto> read(@PathVariable String webSafeKey) throws Throwable {
		Hotel entity = hotelService.read(webSafeKey);
		HotelDto dto = Hotel.createDto(entity);
		return new ResponseEntity<HotelDto>(dto, OK);
	}

	@RequestMapping(method = PUT)
	public ResponseEntity<HotelDto> update(@RequestBody HotelDto dto) {
		try {
			LOGGER.info("update->try->userDto=" + dto);
			Hotel entity = hotelService.update(dto);
			dto = Hotel.createDto(entity);
			return new ResponseEntity<HotelDto>(dto, OK);
		} catch (Throwable e) {
			LOGGER.info("create->catch (Throwable e)");
			e.printStackTrace();
			return new ResponseEntity<HotelDto>(NOT_FOUND);
		}
	}

	@RequestMapping(value = "{" + WEBSAFEKEY + "}", method = DELETE)
	public ResponseEntity<Void> delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		try {
			hotelService.delete(webSafeKey);
			return new ResponseEntity<Void>(OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}
}
