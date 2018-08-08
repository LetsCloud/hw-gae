/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiParameters.HOTEL_KEY;
import static hu.hw.cloud.shared.api.ApiParameters.ONLY_ACTIVE;
import static hu.hw.cloud.shared.api.ApiParameters.SEL_INV_TYPE;
import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.PATH_WEBSAFEKEY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.MARKET_GROUP;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

import hu.hw.cloud.server.entity.hotel.MarketGroup;
import hu.hw.cloud.server.service.MarketGroupService;
import hu.hw.cloud.shared.cnst.InventoryType;
import hu.hw.cloud.shared.dto.hotel.MarketGroupDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author robi
 *
 */
@RestController
@RequestMapping(value = ROOT + MARKET_GROUP, produces = MediaType.APPLICATION_JSON_VALUE)
public class MarketGroupController extends HotelChildController<MarketGroup, MarketGroupDto> {
	private static final Logger logger = LoggerFactory.getLogger(RoomTypeController.class);

	@Autowired
	MarketGroupController(MarketGroupService service) {
		super(service);
		logger.info("RoomTypeController()");
	}

	@Override
	protected MarketGroupDto createDto(MarketGroup entity) {
		return MarketGroup.createDto(entity);
	}

	@RequestMapping(method = GET)
	public ResponseEntity<List<MarketGroupDto>> getByHotelWithFilters(@QueryParam(HOTEL_KEY) String hotelKey,
			@QueryParam(ONLY_ACTIVE) Boolean onlyActive, @QueryParam(SEL_INV_TYPE) InventoryType inventoryType) {
		logger.info("RoomTypeController().getByHotelWithFilters()");
		Map<String, Object> filters = new HashMap<String, Object>();
		if (onlyActive)
			filters.put("active", onlyActive);

		return getChildrenByFilters(hotelKey, filters);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = GET)
	public ResponseEntity<MarketGroupDto> get(@PathVariable String webSafeKey) throws RestApiException {
		logger.info("RoomTypeController().get()");
		return super.get(webSafeKey);
	}

	@Override
	@RequestMapping(method = POST)
	public ResponseEntity<MarketGroupDto> saveOrCreate(@RequestBody MarketGroupDto dto) throws RestApiException {
		return super.saveOrCreate(dto);
	}

	@Override
	@RequestMapping(value = PATH_WEBSAFEKEY, method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(WEBSAFEKEY) String webSafeKey) throws RestApiException {
		super.delete(webSafeKey);
	}

}
