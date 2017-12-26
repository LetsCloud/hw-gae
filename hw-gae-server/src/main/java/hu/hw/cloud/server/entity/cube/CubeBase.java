/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.HotelChild;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.CubeResultDto;
import hu.hw.cloud.shared.dto.cube.hw.HwFroBaseDto;

/**
 * Minden Rms entitás alapja, Hotel entitás gyermeke, tartózkodás napja
 * 
 * @author CR
 *
 */
public abstract class CubeBase extends HotelChild {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeBase.class.getName());

	public static final String FIELD_STAYDATE = "stayDate";

	/**
	 * Ügyviteli nap.
	 */
	@Index
	private Date bsnsDate;

	/**
	 * Ügyviteli hét napja.
	 */
	@Ignore
	private Integer bsnsDay;

	/**
	 * Ügyviteli hét.
	 */
	@Ignore
	private Integer bsnsWeek;

	/**
	 * Ügyviteli hónap.
	 */
	@Ignore
	private Integer bsnsMonth;

	/**
	 * Szobatípus.
	 */
	private String roomType;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public CubeBase() {
	}

	/**
	 * 
	 * @param hotel
	 * @param dto
	 */
	public CubeBase(Hotel hotel, HwFroBaseDto dto) {
		this();
//		LOGGER.info("CubeBase(HwFroBaseDto dto)->" + dto.getDATUMA());
		this.setHotel(hotel);
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		try {
			this.bsnsDate = format.parse(dto.getDATUMA());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.roomType = dto.getSZOTIP();
	}

	public Date getBsnsDate() {
		return bsnsDate;
	}

	public void setBsnsDate(Date bsnsDate) {
		this.bsnsDate = bsnsDate;
	}

	public Integer getBsnsDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(bsnsDate);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public void setBsnsDay(Integer bsnsDay) {
		this.bsnsDay = bsnsDay;
	}

	public Integer getBsnsWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(bsnsDate);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public void setBsnsWeek(Integer bsnsWeek) {
		this.bsnsWeek = bsnsWeek;
	}

	public Integer getBsnsMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(bsnsDate);
		return cal.get(Calendar.MONTH);
	}

	public void setBsnsMonth(Integer bsnsMonth) {
		this.bsnsMonth = bsnsMonth;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * 
	 * @param dimNames
	 * @return
	 */
	public List<String> getDimValues(List<Dimension> dimNames) {
		List<String> dimValues = new ArrayList<String>();
		for (Dimension d : dimNames) {
			if (d.equals(Dimension.NONE)) {
				dimValues.add("");
			} else {
				dimValues.add(getDimValue(d));
			}
		}
		return dimValues;
	}

	/**
	 * Visszaadja a dimenzió mező értékét
	 * 
	 * @param dim
	 * @return
	 */
	public String getDimValue(Dimension dimName) {
		switch (dimName) {
		case NONE:
			return "";
		case HOTEL_CODE:
			return this.getHotel().getCode();
		case BSNS_DATE:
			return this.bsnsDate.toString();
		case BSNS_DAY:
			return this.bsnsDay.toString();
		case BSNS_WEEK:
			return this.bsnsWeek.toString();
		case ROOM_TYPE:
			return this.roomType;
		default:
			return "";
		}
	}

	/**
	 * Visszaadja az entitás measure paraméterekkel jelölt mezőjének értékét.
	 * 
	 * @param measure1
	 * @param measure2
	 * @return
	 */
	protected Double getValueOfMeasure(Measure measure1, Measure measure2) {
		return 0d;
	}

	/***
	 * A megadott CubeResultDto mezőit feltölti az entitás measures paraméterrel
	 * meghatározott mezőinek értékével.
	 * 
	 * @param model
	 * @param measures
	 * @return
	 */
	public CubeResultDto addToCubeResultDto(CubeResultDto model, List<MeasureParam> measures, Boolean doSubstract) {
		for (MeasureParam mp : measures)
			model = addToCubeResultDto(model, mp, doSubstract);
		return model;
	}

	/**
	 * A megadott CubeResultDto mezőit feltölti az entitás measure paraméterrel
	 * meghatározott mezőjének értékével.
	 * 
	 * @param model
	 * @param measure
	 * @return
	 */
	public CubeResultDto addToCubeResultDto(CubeResultDto model, MeasureParam measure, Boolean doSubstract) {
		if (doSubstract)
			model.addValue(measure.getWidgetIndex(), measure.getFieldIndex(),
					-getValueOfMeasure(measure.getMeasure1(), measure.getMeasure2()));
		else
			model.addValue(measure.getWidgetIndex(), measure.getFieldIndex(),
					getValueOfMeasure(measure.getMeasure1(), measure.getMeasure2()));
		return model;
	}

	public CubeOcc addToOccData(CubeOcc occData) {
		return occData;
	}

}
