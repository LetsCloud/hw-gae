/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube.m1;

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
public class CubeActlQueryM1 extends AbstractCubeFullQuery<CubeActlCap, CubeActlPfm> {

	class CubeActlCapQueryM1 extends AbstractCubeCoreQueryM1<CubeActlCap> {
		public CubeActlCapQueryM1(CubeQueryParam params, CubeRepo<CubeActlCap> cubeRepo,
				Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> calcDataMap) {
			super(params, DataSource.FRO_ACTL_CAPY, cubeRepo, cubeResultDtoMap, calcDataMap);
		}
	}

	class CubeActlPfmQueryM1 extends AbstractCubeCoreQueryM1<CubeActlPfm> {
		public CubeActlPfmQueryM1(CubeQueryParam params, CubeRepo<CubeActlPfm> cubeRepo,
				Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> calcDataMap) {
			super(params, DataSource.FRO_ACTL_PERF, cubeRepo, cubeResultDtoMap, calcDataMap);
		}
	}

	public CubeActlQueryM1(CubeQueryParam param, CubeRepo<CubeActlCap> capRepo, CubeRepo<CubeActlPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap) {
		super(param, capRepo, pfmRepo, cubeResultDtoMap);
	}

	@Override
	public CubeCoreQuery getCapQuery(CubeQueryParam param, CubeRepo<CubeActlCap> capRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeActlCapQueryM1(param, capRepo, cubeResultDtoMap, occData);
	}

	@Override
	public CubeCoreQuery getPfmQuery(CubeQueryParam param, CubeRepo<CubeActlPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeActlPfmQueryM1(param, pfmRepo, cubeResultDtoMap, occData);
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
