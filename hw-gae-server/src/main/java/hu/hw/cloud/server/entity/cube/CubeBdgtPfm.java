/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;

import hu.hw.cloud.shared.cnst.cube.Measure;

/**
 * @author CR
 *
 */
@Entity
public class CubeBdgtPfm extends CubeBasePfm {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeBdgtPfm.class.getName());

	/**
	 * Vendégéjszaka (M).
	 */
	private Integer guestNts;

	/**
	 * Bruttó szállás árbevétel (M).
	 */
	private CubeDepDetail grsRev;

	/**
	 * Bruttó szállás árbevétel (M).
	 */
	private CubeDepDetail netRev;

	/**
	 * Bruttó szoba átlagár
	 */
	@Ignore
	private Double grsAdr;

	/**
	 * Nettó szoba átlagár
	 */
	@Ignore
	private Double netAdr;

	/**
	 * Üres konstruktor az Objectify kedvéért.
	 */
	public CubeBdgtPfm() {
//		LOGGER.info("CubePrfmBdgt()");
	}

	public Integer getGuestNts() {
		return guestNts;
	}

	public void setGuestNts(Integer guestNts) {
		this.guestNts = guestNts;
	}

	public CubeDepDetail getGrsRev() {
		return grsRev;
	}

	public void setGrsRev(CubeDepDetail grsRev) {
		this.grsRev = grsRev;
	}

	public CubeDepDetail getNetRev() {
		return netRev;
	}

	public void setNetRev(CubeDepDetail netRev) {
		this.netRev = netRev;
	}

	public Double getGrsAdr() {
		return getGrsRev().getRoom() / getRoomNts();
	}

	public void setGrsAdr(Double grsAdr) {
		this.grsAdr = grsAdr;
	}

	public Double getNetAdr() {
		return getNetRev().getRoom() / getRoomNts();
	}

	public void setNetAdr(Double netAdr) {
		this.netAdr = netAdr;
	}

	/**
	 * Visszaadja az entitás measure paraméterrel jelölt mezőjének értékét.
	 * 
	 * @param measure1
	 * @param measure2
	 * @return
	 */
	@Override
	protected Double getValueOfMeasure(Measure measure1, Measure measure2) {
		switch (measure1) {

		case GUEST_NTS:
			if (this.getGuestNts() != null)
				return this.getGuestNts().doubleValue();

		case GRS_REV:
			if (this.getGrsRev() != null)
				return this.getGrsRev().getMeasValue(measure2);

		case NET_REV:
			if (this.getNetRev() != null)
				return this.getNetRev().getMeasValue(measure2);

		default:
			return super.getValueOfMeasure(measure1, measure2);
		}
	}

	@Override
	public CubeOcc addToOccData(CubeOcc joined) {
		joined = super.addToOccData(joined);
		joined.addGuestNts(getGuestNts());
		return joined;
	}
}
