/**
 * 
 */
package hu.hw.cloud.server.api.v1;

import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.API;
import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.HOTEL;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.service.HotelService;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = API, produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelApiController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelApiController.class);

	@Autowired
	private HotelService hotelService;

	@Autowired
	private HotelRepository hotelRepository;

	/**
	 * Új hotel rögzítése
	 * 
	 * @param hotelDto
	 * @return
	 */
	@RequestMapping(value = HOTEL+"2", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HotelDto createHotel2(@RequestBody HotelDto hotelDto) {
		LOGGER.info("createHotel2()");
		return hotelDto;
	}

	@RequestMapping(value = HOTEL, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto, WebRequest request)
			throws RestApiException {
		LOGGER.info("createHotel()");

		// Ha nem egyezik a HotelDto előfizetői azonosító és az autentikációban
		// található előfizetői azonosítóval, akkor gáz van.
		accountIdValidation(request, hotelDto.getAccountWebSafeKey());

		try {
			Hotel hotel = hotelService.create(hotelDto);
			hotelDto = Hotel.createDto(hotel);
			return new ResponseEntity<HotelDto>(hotelDto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	@RequestMapping(value = HOTEL, method = RequestMethod.GET)
	ResponseEntity<List<HotelDto>> getHotels(WebRequest request) throws RestApiException {
		LOGGER.info("getHotels()");
		try {
			List<HotelDto> dtos = new ArrayList<HotelDto>();
			List<Hotel> hotels = hotelService.getAll(getAccountId(request));
			for (Hotel hotel : hotels) {
				dtos.add(Hotel.createDto(hotel));
			}
			return new ResponseEntity<List<HotelDto>>(dtos, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	/**
	 * 
	 * @param hotelId
	 * @param request
	 * @return
	 * @throws RestApiException
	 */
	@RequestMapping(value = HOTEL + "/{hotelId}", method = RequestMethod.GET)
	ResponseEntity<HotelDto> readHotel(@PathVariable String hotelId, WebRequest request) throws RestApiException {
		String accountId = hotelRepository.getAccountId(hotelId);
		accountIdValidation(request, accountId);

		try {
			Hotel hotel = hotelService.read(hotelId);
			HotelDto hotelDto = Hotel.createDto(hotel);
			return new ResponseEntity<HotelDto>(hotelDto, OK);
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
	@RequestMapping(value = HOTEL, method = RequestMethod.PUT)
	public ResponseEntity<HotelDto> updateHotel(@RequestBody HotelDto hotelDto, WebRequest request) throws Throwable {

		String accountId = hotelRepository.getAccountId(hotelDto.getWebSafeKey());
		// Ha nem egyezik a HotelDto előfizetői azonosító és az autentikációban
		// található előfizetői azonosítóval, akkor gáz van.
		accountIdValidation(request, accountId);

		try {
			Hotel hotel = hotelService.update(hotelDto);
			hotelDto = Hotel.createDto(hotel);
			return new ResponseEntity<HotelDto>(hotelDto, OK);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	/**
	 * 
	 * @param hotelId
	 * @param request
	 * @throws Throwable
	 */
	@RequestMapping(value = HOTEL + "/{hotelId}", method = RequestMethod.DELETE)
	void deleteHotel(@PathVariable String hotelId, WebRequest request) throws Throwable {
		String accountId = hotelRepository.getAccountId(hotelId);
		accountIdValidation(request, accountId);

		hotelService.delete(hotelId);
	}

}
