/**
 * 
 */
package hu.hw.cloud.server.service.impl.cube;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.cube.CubeBase;
import hu.hw.cloud.server.entity.cube.CubeOcc;
import hu.hw.cloud.server.entity.cube.MeasureParam;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.cube.CubeQueryParam;
import hu.hw.cloud.server.repository.CubeRepo;
import hu.hw.cloud.shared.cnst.cube.Aggregation;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;

/**
 * Az AbstractCubeCoreQuery leszármazottja megadott Core adatforrás (Cap vagy
 * Pfm) adatait felgyűjti és betölti egy String, ModelD3M6Dto leképezésbe,
 * valamint szükség esetén foglaltságok leképezuésbe is betölti.
 * 
 * @author CR
 *
 */
public abstract class AbstractCubeCoreQuery<T extends CubeBase> implements CubeCoreQuery {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCubeCoreQuery.class.getName());

	protected final CubeQueryParam params;
	private final DataSource cubeDataSource;
	protected final CubeRepo<T> cubeRepo;
	protected Map<String, CubeResultDto> cubeResultDtoMap;
	protected Map<String, CubeOcc> calcDataMap;

	public AbstractCubeCoreQuery(CubeQueryParam params, DataSource cubeDataSource, CubeRepo<T> cubeRepo,
			Map<String, CubeResultDto> cubeResultDtoMap, Map<String, CubeOcc> occData) {
		LOGGER.info("AbstractCubeCoreQuery()");
		this.params = params;
		this.cubeDataSource = cubeDataSource;
		this.cubeRepo = cubeRepo;
		this.cubeResultDtoMap = cubeResultDtoMap;
		this.calcDataMap = occData;
	}

	/**
	 * Mag adatok beolvasása az adatbázisból.
	 * 
	 * @param hotel
	 * @param fromDate
	 * @param toDate
	 * @param periodField
	 * @return
	 */
	protected List<T> readCoreData(Hotel hotel, Date fromDate, Date toDate, Dimension periodField) {
		if (periodField.equals(Dimension.FCST_DATE)) {
			return cubeRepo.getFromToFcstDate(hotel, fromDate, toDate);
		} else
			return cubeRepo.getFromToBsnsDate(hotel, fromDate, toDate);
	}

	/**
	 * Az átadot core entitás megadott mutatóit beösszesíti az eredmény
	 * gyüjtőbe.
	 * 
	 * @param coreData
	 * @param dimValues
	 * @param measures
	 */
	protected abstract void sumCoreData(T coreData, List<String> dimValues, List<MeasureParam> measures,
			Boolean doSubstract);

	/**
	 * Az átadot core entitás megadott mutatóit beösszesíti a kalkulált
	 * gyüjtőbe.
	 * 
	 * @param coreData
	 * @param dimValues
	 * @param measures
	 */
	protected abstract void sumCalcData(T coreData, List<String> dimValues, List<MeasureParam> measures);

	@Override
	public void collectData() {
		// Összesítünk
		if (params.getAggrFunc().equals(Aggregation.SUM)) {
			sumData(params.getHotel(), params.getFromDate(), params.getToDate(), params.getPeriodField(), false);
		} else if (params.getAggrFunc().equals(Aggregation.CHANGE)) {
			sumData(params.getHotel(), params.getFromDate(), params.getToDate(), params.getPeriodField(), false);
			sumData(params.getHotel(), params.getFromDateBase(), params.getToDateBase(), params.getPeriodField(), true);
		}
	}

	/**
	 * Beolvassa és dimenzionálva összesíti a mag és a kalkulált adatokat.
	 * 
	 * @param hotel
	 * @param fromtDat
	 * @param toDate
	 * @param periodField
	 * @param doSubstract
	 */
	private void sumData(Hotel hotel, Date fromtDat, Date toDate, Dimension periodField, Boolean doSubstract) {
		// Kiolvassuk az adatbázisból a mag adatokat
		List<T> coreEntities = readCoreData(hotel, fromtDat, toDate, periodField);
		LOGGER.info("sumData()->coreEntities.size()=" + coreEntities.size());
		for (T coreEntity : coreEntities) {
			// Begyűjtjök a dimenzionáló mezők értékeit, mert ezekre kell
			// összesíteni a megadott mutatók értékét.
			List<String> dimValues = coreEntity.getDimValues(params.getDimensions());

			// Ha szükség van a kalkulált adatokra, akkor
			if (params.checkOccDataStore(cubeDataSource))
				// akkor összesítjük az azokhoz szükséges mutatókat is
				sumCalcData(coreEntity, dimValues, params.getAllMeasure());

			// HA TÉNY KAPACITÁS ADATOKAT KELL LEKÉRNI
			// sumCoreData(entity, dimValues,
			// params.getMeasures(cubeDataSource), doSubstract);
			sumCoreData(coreEntity, dimValues, params.getAllMeasure(), doSubstract);
		}
	}
}
