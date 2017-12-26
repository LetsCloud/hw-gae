/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube.d3m6;

import java.util.Map;

import hu.hw.cloud.server.entity.cube.CubeActlCap;
import hu.hw.cloud.server.entity.cube.CubeActlPfm;
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
public class CubeActlQueryD3m6 extends AbstractCubeFullQuery<CubeActlCap, CubeActlPfm> {

	class CubeActlCapQueryD3m6 extends AbstractCubeCoreQueryD3m6<CubeActlCap> {
		public CubeActlCapQueryD3m6(CubeQueryParam params, CubeRepo<CubeActlCap> repo,
				Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
			super(params, DataSource.FRO_ACTL_CAPY, repo, cubeResultDtoMap, occData);
		}
	}

	class CubeActlPfmQueryD3m6 extends AbstractCubeCoreQueryD3m6<CubeActlPfm> {
		public CubeActlPfmQueryD3m6(CubeQueryParam params, CubeRepo<CubeActlPfm> repo,
				Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
			super(params, DataSource.FRO_ACTL_PERF, repo, cubeResultDtoMap, occData);
		}
	}

	public CubeActlQueryD3m6(CubeQueryParam param, CubeRepo<CubeActlCap> capRepo, CubeRepo<CubeActlPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap) {
		super(param, capRepo, pfmRepo, cubeResultDtoMap);
	}

	@Override
	public CubeCoreQuery getCapQuery(CubeQueryParam param, CubeRepo<CubeActlCap> capRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeActlCapQueryD3m6(param, capRepo, cubeResultDtoMap, occData);
	}

	@Override
	public CubeCoreQuery getPfmQuery(CubeQueryParam param, CubeRepo<CubeActlPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeActlPfmQueryD3m6(param, pfmRepo, cubeResultDtoMap, occData);
	}

	@Override
	protected Boolean isCapRequired() {
		return (param.getMeasures().containsKey(DataSource.FRO_ACTL_CAPY))
				|| (param.getMeasures().containsKey(DataSource.FRO_ACTL_CALC));
	}

	@Override
	protected Boolean isPfmRequired() {
		return (param.getMeasures().containsKey(DataSource.FRO_ACTL_PERF))
				|| (param.getMeasures().containsKey(DataSource.FRO_ACTL_CALC));
	}

}
