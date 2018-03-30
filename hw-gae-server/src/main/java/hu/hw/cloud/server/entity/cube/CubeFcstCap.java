/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.cnst.cube.Dimension;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * @author CR
 *
 */
@Entity
public class CubeFcstCap extends CubeActlCap {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeFcstCap.class.getName());

	/**
	 * Előrejelzés napja.
	 */
	@Index
	private Date fcstDate;

	/**
	 * Előrejelzés hete.
	 */
	@Ignore
	private Integer fcstWeek;

	/**
	 * Előrejelzés hónapja.
	 */
	@Ignore
	private Integer fcstMonth;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public CubeFcstCap() {
	}

	/**
	 * Konstruktor FRO előrejelzés adatokból.
	 * 
	 * @param hotel
	 * @param dto
	 */
	public CubeFcstCap(Hotel hotel, HwFroFocDto dto) {
		super(hotel, dto);
//		LOGGER.info("CubeCapFcst(HwFroFocDto dto)->" + dto);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		try {
			this.fcstDate = format.parse(dto.getFCSTDATE());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getFcstDate() {
		return fcstDate;
	}

	public void setFcstDate(Date fcstDate) {
		this.fcstDate = fcstDate;
	}

	public Integer getFcstWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fcstDate);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public void setFcstWeek(Integer fcstWeek) {
		this.fcstWeek = fcstWeek;
	}

	public Integer getFcstMonth() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fcstDate);
		return cal.get(Calendar.MONTH);
	}

	public void setFcstMonth(Integer fcstMonth) {
		this.fcstMonth = fcstMonth;
	}

	/**
	 * Visszaadja a dimenzió mező értékét
	 * 
	 * @param dim
	 * @return
	 */
	@Override
	public String getDimValue(Dimension dimName) {
		switch (dimName) {
		case FCST_DATE:
			return this.fcstDate.toString();
		case FCST_WEEK:
			return this.fcstWeek.toString();
		case FCST_MONTH:
			return this.fcstMonth.toString();
		default:
			return super.getDimValue(dimName);
		}
	}

}
