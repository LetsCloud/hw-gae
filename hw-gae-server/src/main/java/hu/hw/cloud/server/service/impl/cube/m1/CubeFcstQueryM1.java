/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube.m1;

import java.util.Map;

import hu.hw.cloud.server.entity.cube.CubeFcstCap;
import hu.hw.cloud.server.entity.cube.CubeFcstPfm;
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
public class CubeFcstQueryM1 extends AbstractCubeFullQuery<CubeFcstCap, CubeFcstPfm> {

	class CubeFcstCapQueryM1 extends AbstractCubeCoreQueryM1<CubeFcstCap> {
		public CubeFcstCapQueryM1(CubeQueryParam params, CubeRepo<CubeFcstCap> cubeRepo,
				Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
			super(params, DataSource.FRO_FCST_CAPY, cubeRepo, cubeResultDtoMap, occData);
		}
	}

	class CubeFcstPfmQueryM1 extends AbstractCubeCoreQueryM1<CubeFcstPfm> {
		public CubeFcstPfmQueryM1(CubeQueryParam params, CubeRepo<CubeFcstPfm> cubeRepo,
				Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
			super(params, DataSource.FRO_FCST_PERF, cubeRepo, cubeResultDtoMap, occData);
		}
	}

	public CubeFcstQueryM1(CubeQueryParam param, CubeRepo<CubeFcstCap> capRepo, CubeRepo<CubeFcstPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap) {
		super(param, capRepo, pfmRepo, cubeResultDtoMap);
	}

	@Override
	public CubeCoreQuery getCapQuery(CubeQueryParam param, CubeRepo<CubeFcstCap> capRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeFcstCapQueryM1(param, capRepo, cubeResultDtoMap, occData);
	}

	@Override
	public CubeCoreQuery getPfmQuery(CubeQueryParam param, CubeRepo<CubeFcstPfm> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		return new CubeFcstPfmQueryM1(param, pfmRepo, cubeResultDtoMap, occData);
	}

	@Override
	protected Boolean isCapRequired() {
		return (param.getMeasures().containsKey(DataSource.FRO_FCST_CAPY))
				|| (param.getMeasures().containsKey(DataSource.FRO_FCST_CALC));
	}

	@Override
	protected Boolean isPfmRequired() {
		return (param.getMeasures().containsKey(DataSource.FRO_FCST_PERF))
				|| (param.getMeasures().containsKey(DataSource.FRO_FCST_CALC));
	}

}
