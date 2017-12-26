/**
 * 
 */
package hu.hw.cloud.server.service;

import java.util.List;

import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.shared.dto.cube.D3m6Dto;
import hu.hw.cloud.shared.dto.cube.DataWidgetValueM1Dto;
import hu.hw.cloud.shared.dto.cube.Perf1Dto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * @author CR
 *
 */
public interface CubeService {

	void insCapAct(HwFroCubDto dto) throws Throwable;

	void insPerfAct(HwFroCubDto dto) throws Throwable;

	void insertCapacityForecast(HwFroFocDto dto) throws Throwable;

	void insertMeasuresForecast(HwFroFocDto dto) throws Throwable;

	void deleteForecast(String hotelKey) throws Throwable;

	void generateTestData(String hotelKey) throws Throwable;

	List<D3m6Dto> getD3M6Data(CubeQueryParam param);

	List<DataWidgetValueM1Dto> getDataWidgetValues(CubeQueryParam param);

	List<DataWidgetValueM1Dto> getM1ValuesFromBigQuery(CubeQueryParam param);

	List<Perf1Dto> queryPerf1();
}
