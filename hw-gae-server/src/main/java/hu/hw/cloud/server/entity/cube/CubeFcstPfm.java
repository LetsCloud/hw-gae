/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.dto.cube.hw.HwFroFocDto;

/**
 * @author CR
 *
 */
@Entity
public class CubeFcstPfm extends CubeAcstPfm {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeFcstPfm.class.getName());

	public static Comparator<CubeFcstPfm> ORDER_BY_FORECASTDATE = new Comparator<CubeFcstPfm>() {
		public int compare(CubeFcstPfm one, CubeFcstPfm other) {
			return one.getFcstDate().compareTo(other.getFcstDate());
		}
	};

	public static Comparator<CubeFcstPfm> ORDER_BY_STAYDATE = new Comparator<CubeFcstPfm>() {
		public int compare(CubeFcstPfm one, CubeFcstPfm other) {
			return one.getBsnsDate().compareTo(other.getBsnsDate());
		}
	};

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

	private String status;

	public CubeFcstPfm() {
//		LOGGER.info("CubePrfmFcst()");
	}
	public CubeFcstPfm(Hotel hotel, HwFroFocDto dto) {
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
		return fcstWeek;
	}

	public void setFcstWeek(Integer fcstWeek) {
		this.fcstWeek = fcstWeek;
	}

	public Integer getFcstMonth() {
		return fcstMonth;
	}

	public void setFcstMonth(Integer fcstMonth) {
		this.fcstMonth = fcstMonth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
