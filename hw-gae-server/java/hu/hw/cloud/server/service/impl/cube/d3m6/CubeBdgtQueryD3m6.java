/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube.d3m6;

import java.util.Map;

import hu.hw.cloud.server.entity.cube.CubeBdgtCap;
import hu.hw.cloud.server.entity.cube.CubeBdgtPfm;
import hu.hw.cloud.server.entity.cube.CubeOcc;
import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.repository.CubeRepo;
import hu.hw.cloud.server.service.impl.cube.AbstractCubeFullQuery;
import hu.hw.cloud.server.service.impl.cube.CubeCoreQuery;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;

/**
 * @author CR
 *
 */
public class CubeBdgtQueryD3m6 extends AbstractCubeFullQuery<CubeBdgtCap, CubeBdgtPfm> {
	
	class CubeBdgtCapQueryD3m6 extends AbstractCubeCoreQueryD3m6<CubeBdgtCap> {
		public CubeBdgtCapQueryD3m6(CubeQueryParam params, CubeRepo<CubeBdgtCap> cubeBdgtCapRepo,
				Map<String, CubeResultDto> modelD3M6DtoMap, Map<String, CubeOcc> occData) {
			super(params, DataSource.FRO_BDGT_CAPY, cubeBdgtCapRepo, modelD3M6DtoMap, occData);
		}
	}

	class CubeBdgtPfmQueryD3m6 extends AbstractCubeCoreQueryD3m6<CubeBdgtPfm> {
		public CubeBdgtPfmQueryD3m6(CubeQueryParam params, CubeRepo<CubeBdgtPfm> cubeBdgtPfmRepo,
				Map<String, CubeResultDto> modelD3M6DtoMap, Map<String, CubeOcc> occData) {
			super(params, DataSource.FRO_BDGT_PERF, cubeBdgtPfmRepo, modelD3M6DtoMap, occData);
		}
	}

	public CubeBdgtQueryD3m6(CubeQueryParam param, CubeRepo<CubeBdgtCap> capRepo, CubeRepo<CubeBdgtPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap) {
		super(param, capRepo, pfmRepo, cubeResultDtoMap);
	}

	@Override
	public CubeCoreQuery getCapQuery(CubeQueryParam param, CubeRepo<CubeBdgtCap> capRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeBdgtCapQueryD3m6(param, capRepo, cubeResultDtoMap, occData);
	}

	@Override
	public CubeCoreQuery getPfmQuery(CubeQueryParam param, CubeRepo<CubeBdgtPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeBdgtPfmQueryD3m6(param, pfmRepo, cubeResultDtoMap, occData);
	}

	@Override
	protected Boolean isCapRequired() {
		return (param.getMeasures().containsKey(DataSource.FRO_BDGT_CAPY))
				|| (param.getMeasures().containsKey(DataSource.FRO_BDGT_CALC));
	}

	@Override
	protected Boolean isPfmRequired() {
		return (param.getMeasures().containsKey(DataSource.FRO_BDGT_PERF))
				|| (param.getMeasures().containsKey(DataSource.FRO_BDGT_CALC));
	}

}
