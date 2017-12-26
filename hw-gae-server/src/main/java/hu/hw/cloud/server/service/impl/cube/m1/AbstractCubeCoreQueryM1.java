/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube.m1;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.cube.CubeBase;
import hu.hw.cloud.server.entity.cube.CubeOcc;
import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.entity.cube.MeasureParam;
import hu.hw.cloud.server.repository.CubeRepo;
import hu.hw.cloud.server.service.impl.cube.AbstractCubeCoreQuery;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;
import hu.hw.cloud.shared.dto.cube.DataWidgetValueM1Dto;

/**
 * @author CR
 *
 */
public abstract class AbstractCubeCoreQueryM1<T extends CubeBase> extends AbstractCubeCoreQuery<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCubeCoreQueryM1.class.getName());

	public AbstractCubeCoreQueryM1(CubeQueryParam params, DataSource cubeDataSource, CubeRepo<T> cubeRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		super(params, cubeDataSource, cubeRepo, cubeResultDtoMap, occData);
		LOGGER.info("AbstractCubeCoreQueryM1()");
	}

	@Override
	protected void sumCoreData(T coreData, List<String> dimValues, List<MeasureParam> measureParams,
			Boolean doSubstract) {
		// A magadat értékeit paraméter mutatónként adjuk hozzá az eredmény
		// gyűjtőhöz
		for (MeasureParam measureParam : measureParams) {
			// Összeállítjuk a csoportosító kulcsot
			String groupBykey = measureParam.getWidgetIndex() + "-" + measureParam.getFieldIndex() + "-" + dimValues;

			// Kikeressük a csoportosító kulcsnak megfelelő eredményelemet
			CubeResultDto cubeResultDto = cubeResultDtoMap.get(groupBykey);

			// Ha nincs megfelelő eredmény elem, akkor...
			if (cubeResultDto == null) {
				// ... létrehozzuk azt
				cubeResultDto = new DataWidgetValueM1Dto(measureParam.getWidgetIndex(), measureParam.getFieldIndex());
				cubeResultDtoMap.put(groupBykey, cubeResultDto);
			}

			// Megkérjük a mag entitást, hogy a paraméterben szereplő mutatók
			// alapján adja hozzá mezőértékeit az eredmény elemhez
			coreData.addToCubeResultDto(cubeResultDto, measureParam, doSubstract);
		}
	}

	@Override
	protected void sumCalcData(T entityData, List<String> dimValues, List<MeasureParam> measureParams) {
		// A magadat értékeit paraméter mutatónként adjuk hozzá a kalkulált
		// gyűjtőhöz
		for (MeasureParam measureParam : measureParams) {
			// Összeállítjuk a csoportosító kulcsot
			String groupByKey = measureParam.getWidgetIndex() + "-" + measureParam.getFieldIndex() + "-" + dimValues;

			// Kikeressük a csoportosító kulcsnak megfelelő kalkulált elemet
			CubeOcc calcData = calcDataMap.get(groupByKey);

			// Ha nincs megfelelő kalkulált elem, akkor...
			if (calcData == null) {
				// ... létrehozzuk azt
				calcData = new CubeOcc();
				calcDataMap.put(groupByKey, calcData);
			}

			// Megkérjük a mag entitást, hogy a paraméterben szereplő mutatók
			// alapján adja hozzá mezőértékeit a kalkulált elemhez
			entityData.addToOccData(calcData);
		}
	}

}
