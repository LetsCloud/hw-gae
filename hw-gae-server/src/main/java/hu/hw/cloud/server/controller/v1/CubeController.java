/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.ROOT;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.DATACUBE;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.D3M6_QUERY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.M1_QUERY;
import static hu.hw.cloud.shared.api.ApiPaths.SpaV1.PERF1_QUERY;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.service.CubeService;
import hu.hw.cloud.shared.dto.cube.D3m6Dto;
import hu.hw.cloud.shared.dto.cube.DataWidgetValueM1Dto;
import hu.hw.cloud.shared.dto.cube.Perf1Dto;
import hu.hw.cloud.shared.dto.cube.Perf1QueryParamDto;
import hu.hw.cloud.shared.dto.cube.query.CubeQueryParamDto;

/**
 * @author CR
 *
 */
@Controller
@RequestMapping(value = ROOT + DATACUBE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CubeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeController.class);

	@Autowired
	private CubeService cubeService;

	@RequestMapping(value = D3M6_QUERY, method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<D3m6Dto>> d3m6CubeQuery(@RequestBody CubeQueryParamDto cubeQueryParamDto) {
		LOGGER.info("d3m6CubeQuery->hotelKey=" + cubeQueryParamDto.getHotelDto().getWebSafeKey());
		LOGGER.info("d3m6CubeQuery->fromDate=" + cubeQueryParamDto.getFromDate());
		LOGGER.info("d3m6CubeQuery->toDate=" + cubeQueryParamDto.getToDate());

		List<D3m6Dto> snr = cubeService.getD3M6Data(new CubeQueryParam(cubeQueryParamDto));

		return new ResponseEntity<List<D3m6Dto>>(snr, HttpStatus.OK);
	}

	@RequestMapping(value = M1_QUERY, method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<DataWidgetValueM1Dto>> dataWidgetValueM1Query(
			@RequestBody CubeQueryParamDto cubeQueryParamDto) {
		// LOGGER.info("dataWidgetValueM1Query->hotelKey=" +
		// cubeQueryParamDto.getHotelDto().getWebSafeKey());
		// LOGGER.info("dataWidgetValueM1Query->fromDate=" +
		// cubeQueryParamDto.getFromDate());
		// LOGGER.info("dataWidgetValueM1Query->toDate=" +
		// cubeQueryParamDto.getToDate());

		// List<DataWidgetValueM1Dto> snr = cubeService.getDataWidgetValues(new
		// CubeQueryParam(cubeQueryParamDto));

		List<DataWidgetValueM1Dto> snr = cubeService.getM1ValuesFromBigQuery(new CubeQueryParam(cubeQueryParamDto));

		for (DataWidgetValueM1Dto m : snr) {
			LOGGER.info("dataWidgetValueM1Query->" + m.toString());
		}
		return new ResponseEntity<List<DataWidgetValueM1Dto>>(snr, HttpStatus.OK);
	}

	@RequestMapping(value = PERF1_QUERY, method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Perf1Dto>> perf1Query(@RequestBody Perf1QueryParamDto paramDto) {
		LOGGER.info("perf1Query()");
		LOGGER.info("perf1Query->fromDate=" + paramDto.getFromDate());
		LOGGER.info("perf1Query->toDate=" + paramDto.getToDate());

		List<Perf1Dto> snr = cubeService.queryPerf1();

		return new ResponseEntity<List<Perf1Dto>>(snr, HttpStatus.OK);
	}
}
