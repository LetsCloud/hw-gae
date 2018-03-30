/**
 * 
 */
package hu.hw.cloud.server.api.v1;

import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.API;
import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.ROOMTYPE;
import static org.springframework.http.HttpStatus.OK;
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
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.RoomTypeRepository;
import hu.hw.cloud.server.service.RoomTypeService;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = API, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeApiController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomTypeApiController.class);

	@Autowired
	private RoomTypeService roomTypeService;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private RoomTypeRepository roomTypeRepository;

	/**
	 * Új hotel rögzítése
	 * 
	 * @param hotelDto
	 * @return
	 * @throws RestApiException
	 */
	@RequestMapping(value = ROOMTYPE, method = POST)
	ResponseEntity<RoomTypeDto> createRoomType(@RequestBody RoomTypeDto dto, WebRequest request)
			throws RestApiException {
		LOGGER.info("createRoomType");

		String accountId = hotelRepository.getAccountId(dto.getHotelWebSafeKey());
		// Ha nem egyezik a HotelDto előfizetői azonosító és az autentikációban
		// található előfizetői azonosítóval, akkor gáz van.
		accountIdValidation(request, accountId);

		try {
			LOGGER.info("createRoomType->try");
			RoomType entity = roomTypeService.create(dto);
			dto = RoomType.createDto(entity);
			return new ResponseEntity<RoomTypeDto>(dto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	/**
	 * 
	 * @param roomTypeId
	 * @param request
	 * @return
	 * @throws RestApiException
	 */
	@RequestMapping(value = ROOMTYPE + "/{roomTypeId}", method = GET)
	ResponseEntity<RoomTypeDto> readHotel(@PathVariable String roomTypeId, WebRequest request) throws RestApiException {
		String accountId = roomTypeRepository.getAccountId(roomTypeId);
		accountIdValidation(request, accountId);

		try {
			RoomType entity = roomTypeService.read(roomTypeId);
			RoomTypeDto dto = RoomType.createDto(entity);
			return new ResponseEntity<RoomTypeDto>(dto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	/**
	 * 
	 * @param hotelDto
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = ROOMTYPE, method = PUT)
	public ResponseEntity<RoomTypeDto> updateHotel(@RequestBody RoomTypeDto dto, WebRequest request) throws Throwable {
		LOGGER.info("createRoomType->dto=" + dto);

		String accountId = roomTypeRepository.getAccountId(dto.getWebSafeKey());
		// Ha nem egyezik a HotelDto előfizetői azonosító és az autentikációban
		// található előfizetői azonosítóval, akkor gáz van.
		accountIdValidation(request, accountId);

		try {
			RoomType entity = roomTypeService.update(dto);
			dto = RoomType.createDto(entity);
			return new ResponseEntity<RoomTypeDto>(dto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	/**
	 * 
	 * @param hotelDto
	 * @throws Throwable 
	 */
	@RequestMapping(value = ROOMTYPE+ "/{roomTypeId}", method = DELETE)
	void deleteHotel(@PathVariable String roomTypeId, WebRequest request) throws Throwable {
		String accountId = roomTypeRepository.getAccountId(roomTypeId);
		accountIdValidation(request, accountId);

		roomTypeService.delete(roomTypeId);
	}
}
