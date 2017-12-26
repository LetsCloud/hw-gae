/**
 * 
 */
package hu.hw.cloud.server.api.v1;

import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.API;
import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.DATACUBE_ACTUAL;
import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.FORECAST_DATACUBE;
import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.DATACUBE;
import static hu.hw.cloud.shared.api.ApiPaths.ApiV1.M1_QUERY;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.service.CubeService;
import hu.hw.cloud.shared.dto.cube.query.CubeMeasureParamDto;
import hu.hw.cloud.shared.dto.cube.query.CubeQueryParamDto;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.cnst.cube.DataWidgetFieldType;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.D3m6Dto;
import hu.hw.cloud.shared.dto.cube.DataWidgetValueM1Dto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;
import hu.hw.cloud.shared.exception.RestApiException;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = API, produces = MediaType.APPLICATION_JSON_VALUE)
public class CubeApiController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeApiController.class);
	private static final String HOTEL_ID = "ag5Ib3N0V2FyZSBDbG91ZHImCxIHQWNjb3VudBiAgICAgICACgwLEgVIb3RlbBiAgICAgIDgCgw";

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private CubeService cubeService;

	@RequestMapping(value = DATACUBE, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<D3m6Dto>> d3m6CubeQuery(@RequestBody CubeQueryParamDto qp, WebRequest request)
			throws RestApiException {
		LOGGER.info("dataCubeQuery()->qp.getHotelDto()=" + qp.getHotelDto());

		List<D3m6Dto> snr = cubeService.getD3M6Data(new CubeQueryParam(qp));

		return new ResponseEntity<List<D3m6Dto>>(snr, HttpStatus.OK);
	}

	@RequestMapping(value = M1_QUERY, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DataWidgetValueM1Dto>> d1CubeQuery(@RequestBody CubeQueryParamDto qp, WebRequest request)
			throws RestApiException {
		LOGGER.info("dataCubeQuery()->qp.getHotelDto()=" + qp.getHotelDto());

		List<DataWidgetValueM1Dto> snr = cubeService.getDataWidgetValues(new CubeQueryParam(qp));

		return new ResponseEntity<List<DataWidgetValueM1Dto>>(snr, HttpStatus.OK);
	}

	@RequestMapping(value = FORECAST_DATACUBE, method = GET)
	public ResponseEntity<List<D3m6Dto>> getForecastDataCube(@RequestParam("hotelKey") String hotelKey,
			WebRequest request) throws RestApiException {
		LOGGER.info("getForecastDataCube()");
		// Long accountId = getAccountId(request);
		// LOGGER.info("getForecastDataCube()->accountId=" + accountId);
		// PerfTotal snr = cubeService.sumPerfAct(accountId, hotelId);
		// Map<String, PerfTotal> snr =
		// cubeService.sumPerfActByMarket(accountId, hotelId);
		// Map<String, PerfActGroupBy2Dto> snr =
		// cubeService.sumPerfActGroupBy2(accountId, hotelId,
		// CubePerfActDto.D_MARKET, CubePerfActDto.D_CHANNEL);

		List<Dimension> dimensions = new ArrayList<Dimension>();
		dimensions.add(Dimension.MARKET);
		dimensions.add(Dimension.CHANNEL);
		dimensions.add(Dimension.NONE);

		List<CubeMeasureParamDto> measures = new ArrayList<CubeMeasureParamDto>();
		measures.add(new CubeMeasureParamDto(DataWidgetFieldType.VALUE, DataSource.FRO_ACTL_PERF, Measure.RRES_ARR));
		measures.add(new CubeMeasureParamDto(DataWidgetFieldType.VALUE2, DataSource.FRO_ACTL_PERF, Measure.ROOM_NTS));
		measures.add(new CubeMeasureParamDto(DataWidgetFieldType.VALUE2, DataSource.FRO_ACTL_PERF, Measure.RRES_DEP));

		CubeQueryParamDto qp = new CubeQueryParamDto();
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			qp.setFromDate(format.parse("01.06.2015"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			qp.setToDate(format.parse("01.06.2015"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		qp.setDimensions(dimensions);
		qp.setMeasures(measures);

		List<D3m6Dto> snr = cubeService.getD3M6Data(new CubeQueryParam(qp));

		return new ResponseEntity<List<D3m6Dto>>(snr, HttpStatus.OK);
	}

	@RequestMapping(value = DATACUBE_ACTUAL, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> loadDataCubeActual(@RequestBody List<HwFroCubDto> dtos) throws RestApiException {
		LOGGER.info("loadDataCubeActual()");

		/*
		 * if ((dtos == null) || (dtos.size() == 0)) return;
		 * 
		 * String accountId =
		 * hotelRepository.getAccountId(dtos.get(0).getHotelId());
		 * accountIdValidation(request, accountId);
		 */
		try {
			LOGGER.info("loadDataCubeActual->try");
			for (HwFroCubDto dto : dtos) {
				dto.setHotelId(HOTEL_ID);
				if (dto.getRESNUM() == null) {
					cubeService.insCapAct(dto);
				} else {
					cubeService.insPerfAct(dto);
				}
			}
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	@RequestMapping(value = FORECAST_DATACUBE, method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> loadForecastDataCube(@RequestBody List<HwFroFocDto> dtos) throws RestApiException {
//		LOGGER.info("loadForecastDataCube()");

		/*
		 * if ((dtos == null) || (dtos.size() == 0)) return;
		 * 
		 * String accountId =
		 * hotelRepository.getAccountId(dtos.get(0).getHotelId());
		 * accountIdValidation(request, accountId);
		 */
		try {
//			LOGGER.info("loadForecastDataCube->try");
			for (HwFroFocDto dto : dtos) {
				dto.setHotelId(HOTEL_ID);
//				LOGGER.info("after-dto.setHotelId " + dto);
				if (dto.getRESSTA() != null) {
					// dto.setForecastDate(new Date());
					if (dto.getRESSTA().equals("O")) {
//						LOGGER.info("dto.getRESSTA().equals(O)");
						cubeService.insertCapacityForecast(dto);
					} else {
//						LOGGER.info("!dto.getRESSTA().equals(O)");
						cubeService.insertMeasuresForecast(dto);
					}
				}
			}
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	@RequestMapping(value = FORECAST_DATACUBE + "/{hotelKey}", method = PUT)
	void generate(@PathVariable String hotelKey, WebRequest request) throws RestApiException {
		LOGGER.info("generate");
		String accountId = hotelRepository.getAccountId(hotelKey);
		accountIdValidation(request, accountId);

		try {
			LOGGER.info("generate->try");
			cubeService.generateTestData(hotelKey);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

	@RequestMapping(value = FORECAST_DATACUBE + "/{hotelKey}", method = DELETE)
	void deleteForecastDataCube(@PathVariable String hotelKey, WebRequest request) throws RestApiException {
		LOGGER.info("deleteForecastDataCube");
		String accountId = hotelRepository.getAccountId(hotelKey);
		accountIdValidation(request, accountId);

		try {
			LOGGER.info("deleteForecastDataCube->try");
			cubeService.deleteForecast(hotelKey);
		} catch (Throwable e) {
			throw new RestApiException(e);
		}
	}

}
