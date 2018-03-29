/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

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

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.server.service.HotelService;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

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
	@RequestMapping(value = "", method = GET)
	public List<HotelDto> list() {
		LOGGER.info("list()");
		List<HotelDto> dtos = new ArrayList<HotelDto>();

		AppUser appUser = userService.getCurrentUser();
		if (appUser == null)
			return dtos;

		if (appUser.getAccount().getWebSafeKey() == null)
			return dtos;

		for (Hotel entity : hotelService.getAll(appUser.getAccount().getWebSafeKey())) {
			dtos.add(Hotel.createDto(entity));
		}

		return dtos;
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

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<HotelDto> read(@PathVariable String id) throws Throwable {
		/*
		 * User user = userService.read(id); UserDto userDto =
		 * User.createDto(user); return new ResponseEntity<UserDto>(userDto,
		 * OK);
		 */
		return new ResponseEntity<HotelDto>(new HotelDto(), OK);
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

	@RequestMapping(value = "/{id}", method = DELETE)
	public void delete(@PathVariable String id) {
		// TODO Auto-generated method stub

	}
}
