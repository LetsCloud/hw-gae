/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.cube.CubeBase;
import hu.hw.cloud.server.entity.cube.CubeOcc;
import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.entity.cube.MeasureParam;
import hu.hw.cloud.server.repository.CubeRepo;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;

/**
 * @author CR
 *
 */
public abstract class AbstractCubeFullQuery<C extends CubeBase, P extends CubeBase> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCubeFullQuery.class.getName());

	protected final CubeQueryParam param;
	private final CubeRepo<C> capRepo;
	private final CubeRepo<P> pfmRepo;
	private Map<String, CubeResultDto> cubeResultDtoMap;
	private Map<String, CubeOcc> calcDataMap = new HashMap<String, CubeOcc>();

	public AbstractCubeFullQuery(CubeQueryParam param, CubeRepo<C> capRepo, CubeRepo<P> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap) {
		LOGGER.info("AbstractCubeFullQuery()");
		this.param = param;
		this.capRepo = capRepo;
		this.pfmRepo = pfmRepo;
		this.cubeResultDtoMap = cubeResultDtoMap;
	}

	protected abstract Boolean isCapRequired();

	protected abstract CubeCoreQuery getCapQuery(CubeQueryParam param, CubeRepo<C> capRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData);

	protected abstract Boolean isPfmRequired();

	protected abstract CubeCoreQuery getPfmQuery(CubeQueryParam param, CubeRepo<P> pfmRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData);

	/**
	 * 
	 * @return
	 */
	public Map<String, CubeResultDto> collectData() {

		if (isCapRequired()) {
			CubeCoreQuery capQuery = getCapQuery(param, capRepo, cubeResultDtoMap, calcDataMap);
			capQuery.collectData();
		}

		if (isPfmRequired()) {
			CubeCoreQuery pfmQuery = getPfmQuery(param, pfmRepo, cubeResultDtoMap, calcDataMap);
			pfmQuery.collectData();
		}

		// Bedolgozzuk a kalkulált adatokat az eredmény gyűjtőbe
		for (Map.Entry<String, CubeOcc> calcDataMapEntry : calcDataMap.entrySet()) {
			String groupByKey = calcDataMapEntry.getKey();
			CubeOcc calcEntity = calcDataMapEntry.getValue();
			// Kikeressük az eredmény gyűjtőből a groupByKey-nek megfelelő
			// elemet, hogy hozzáadhassuk a kalkulált adatokat
			CubeResultDto cubeResultDto = cubeResultDtoMap.get(groupByKey);
			for (Map.Entry<DataSource, List<MeasureParam>> mpl : param.getMeasures().entrySet()) {
				calcEntity.addToCubeResultDto(cubeResultDto, mpl.getValue(), false);
			}
		}

		return cubeResultDtoMap;
	}
}
