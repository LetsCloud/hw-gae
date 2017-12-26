/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube.d3m6;

import java.util.List;
import java.util.Map;

import hu.hw.cloud.server.entity.cube.CubeBase;
import hu.hw.cloud.server.entity.cube.CubeOcc;
import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.entity.cube.MeasureParam;
import hu.hw.cloud.server.repository.CubeRepo;
import hu.hw.cloud.server.service.impl.cube.AbstractCubeCoreQuery;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;
import hu.hw.cloud.shared.dto.cube.D3m6Dto;

/**
 * @author CR
 *
 */
public class AbstractCubeCoreQueryD3m6<T extends CubeBase> extends AbstractCubeCoreQuery<T> {

	public AbstractCubeCoreQueryD3m6(CubeQueryParam params, DataSource cubeDataSource, CubeRepo<T> cubeRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		super(params, cubeDataSource, cubeRepo, cubeResultDtoMap, occData);
	}

	@Override
	protected void sumCoreData(T data, List<String> dimValues, List<MeasureParam> measures, Boolean doSubstract) {
		CubeResultDto resultRow = cubeResultDtoMap.get(dimValues.toString());
		if (resultRow == null) {
			resultRow = new D3m6Dto(dimValues);
			cubeResultDtoMap.put(dimValues.toString(), resultRow);
		}
		data.addToCubeResultDto(resultRow, measures, doSubstract);
	}

	@Override
	protected void sumCalcData(T data, List<String> dimValues, List<MeasureParam> measureParams) {
		CubeOcc occEntity = calcDataMap.get(dimValues.toString());
		if (occEntity == null) {
			occEntity = new CubeOcc();
			calcDataMap.put(dimValues.toString(), occEntity);
		}
		data.addToOccData(occEntity);
	}

}
