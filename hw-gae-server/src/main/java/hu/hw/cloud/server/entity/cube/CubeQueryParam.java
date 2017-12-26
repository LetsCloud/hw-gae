/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.cnst.cube.Aggregation;
import hu.hw.cloud.shared.cnst.cube.DataSource;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.dto.cube.query.CubeMeasureParamDto;
import hu.hw.cloud.shared.dto.cube.query.CubeQueryParamDto;

/**
 * @author CR
 *
 */
public class CubeQueryParam {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeQueryParam.class.getName());

	private Hotel hotel;

	private Date fromDate;

	private Date toDate;

	private Date fromDateBase;

	private Date toDateBase;

	private Dimension periodField;

	private Aggregation aggrFunc;

	private List<Dimension> dimensions;

	private Map<DataSource, List<MeasureParam>> measures;

	public CubeQueryParam() {
	}

	public CubeQueryParam(CubeQueryParamDto dto) {
		LOGGER.info("CubeQueryParam");

		hotel = new Hotel(dto.getHotelDto());
		LOGGER.info("CubeQueryParam->hotel=" + hotel);

		fromDate = dto.getFromDate();
		toDate = dto.getToDate();
		fromDateBase = dto.getFromBaseDate();
		toDateBase = dto.getToBaseDate();
		periodField = dto.getDimensions().get(0);
		aggrFunc = Aggregation.SUM;

		setDimensions(dto.getDimensions());

		measures = new HashMap<DataSource, List<MeasureParam>>();
		for (CubeMeasureParamDto mpd : dto.getMeasures()) {
			List<MeasureParam> m = measures.get(mpd.getDataSource());
			if (m == null) {
				m = new ArrayList<MeasureParam>();
				measures.put(mpd.getDataSource(), m);
			}
			m.add(new MeasureParam(mpd.getWidgetIndex(), mpd.getFieldIndex(), mpd.getMeasure1(), mpd.getMeasure2()));
		}
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<Dimension> getDimensions() {
		return dimensions;
	}

	public void setDimensions(List<Dimension> dimensions) {
		this.dimensions = dimensions;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Map<DataSource, List<MeasureParam>> getMeasures() {
		return measures;
	}

	public List<MeasureParam> getAllMeasure() {
		List<MeasureParam> result = new ArrayList<MeasureParam>();
		for (Map.Entry<DataSource, List<MeasureParam>> mpl : measures.entrySet()) {
			result.addAll(mpl.getValue());
		}
		return result;
	}

	public List<MeasureParam> getMeasures(DataSource dataSource) {
		return measures.get(dataSource);
	}

	public void setMeasures(Map<DataSource, List<MeasureParam>> measures) {
		this.measures = measures;
	}

	public Date getFromDateBase() {
		return fromDateBase;
	}

	public void setFromDateBase(Date fromDateBase) {
		this.fromDateBase = fromDateBase;
	}

	public Date getToDateBase() {
		return toDateBase;
	}

	public void setToDateBase(Date toDateBase) {
		this.toDateBase = toDateBase;
	}

	public Dimension getPeriodField() {
		return periodField;
	}

	public void setPeriodField(Dimension periodField) {
		this.periodField = periodField;
	}

	public Aggregation getAggrFunc() {
		return aggrFunc;
	}

	public void setAggrFunc(Aggregation aggrFunc) {
		this.aggrFunc = aggrFunc;
	}

	public DataSource getOccDataSource(DataSource coreDataSource) {
		switch (coreDataSource) {
		case FRO_BDGT_CAPY:
			return getOcc(DataSource.FRO_BDGT_CALC);
		case FRO_BDGT_PERF:
			return getOcc(DataSource.FRO_BDGT_CALC);
		case FRO_ACTL_CAPY:
			return getOcc(DataSource.FRO_ACTL_CALC);
		case FRO_ACTL_PERF:
			return getOcc(DataSource.FRO_ACTL_CALC);
		case FRO_FCST_CAPY:
			return getOcc(DataSource.FRO_FCST_CALC);
		case FRO_FCST_PERF:
			return getOcc(DataSource.FRO_FCST_CALC);
		default:
			return null;
		}
	}

	private DataSource getOcc(DataSource occDataSource) {
		List<MeasureParam> o = measures.get(occDataSource);
		if (o != null)
			return occDataSource;
		return null;
	}

	public Boolean checkOccDataStore(DataSource coreDataStore) {
		switch (coreDataStore) {
		case FRO_BDGT_CAPY:
			return getMeasures().containsKey(DataSource.FRO_BDGT_CALC);
		case FRO_BDGT_PERF:
			return getMeasures().containsKey(DataSource.FRO_BDGT_CALC);
		case FRO_ACTL_CAPY:
			return getMeasures().containsKey(DataSource.FRO_ACTL_CALC);
		case FRO_ACTL_PERF:
			return getMeasures().containsKey(DataSource.FRO_ACTL_CALC);
		case FRO_FCST_CAPY:
			return getMeasures().containsKey(DataSource.FRO_FCST_CALC);
		case FRO_FCST_PERF:
			return getMeasures().containsKey(DataSource.FRO_FCST_CALC);
		default:
			return false;
		}
	}

	public Boolean isBdgtRequired() {
		return (getMeasures().containsKey(DataSource.FRO_BDGT_CAPY) || getMeasures().containsKey(DataSource.FRO_BDGT_CALC)
				|| getMeasures().containsKey(DataSource.FRO_BDGT_PERF));
	}

	public Boolean isActlRequired() {
		return (getMeasures().containsKey(DataSource.FRO_ACTL_CAPY) || getMeasures().containsKey(DataSource.FRO_ACTL_CALC)
				|| getMeasures().containsKey(DataSource.FRO_ACTL_PERF));
	}

	public Boolean isFcstRequired() {
		return (getMeasures().containsKey(DataSource.FRO_FCST_CAPY) || getMeasures().containsKey(DataSource.FRO_FCST_CALC)
				|| getMeasures().containsKey(DataSource.FRO_FCST_PERF));
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName() + " CubeQueryParam {" + NEW_LINE);
		result.append(" hotel: " + hotel + NEW_LINE);
		result.append(" fromDate: " + fromDate + NEW_LINE);
		result.append(" toDate: " + toDate + NEW_LINE);
		result.append(" fromDateBase: " + fromDateBase + NEW_LINE);
		// Note that Collections and Maps also override toString
		result.append(" toDateBase: " + toDateBase + NEW_LINE);
		result.append(" periodField: " + periodField + NEW_LINE);
		result.append(" aggrFunc: " + aggrFunc + NEW_LINE);
		result.append(" dimensions: " + dimensions + NEW_LINE);
		result.append(" measures: " + measures + NEW_LINE);
		result.append("}");

		return result.toString();
	}
}
