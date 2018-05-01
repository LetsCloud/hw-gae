/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

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

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.service.RoomTypeService;
import hu.hw.cloud.shared.cnst.InventoryType;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;
import hu.hw.cloud.shared.exception.RestApiException;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOMTYPE;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiParameters.HOTEL_KEY;
import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.SEL_INV_TYPE;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + ROOMTYPE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomTypeController extends HotelChildController<RoomType, RoomTypeDto> {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeController.class);

	@Autowired
	RoomTypeController(RoomTypeService service) {
		super(service);
		logger.info("RoomTypeController()");
	}

	@Override
	protected RoomTypeDto createDto(RoomType entity) {
		return RoomType.createDto(entity);
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<RoomTypeDto>> getByHotelWithFilters(@QueryParam(HOTEL_KEY) String hotelKey,
			@QueryParam(ONLY_ACTIVE) Boolean onlyActive, @QueryParam(SEL_INV_TYPE) InventoryType inventoryType) {
		Map<String, Object> filters = new HashMap<String, Object>();
		if (onlyActive)
			filters.put("active", onlyActive);

		if (inventoryType != null)
			filters.put(SEL_INV_TYPE, inventoryType);

		return getChildrenByFilters(hotelKey, filters);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<RoomTypeDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("RoomTypeController().get()");
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<RoomTypeDto> saveOrCreate(@RequestBody RoomTypeDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
