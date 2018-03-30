/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.cnst.cube.Measure;
import hu.hw.cloud.shared.dto.cube.hw.HwFroCubDto;

/**
 * @author CR
 *
 */
@Entity
public class CubeActlPfm extends CubeAcstPfm {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeActlPfm.class.getName());

	private Double pmntCash;

	private Double pmntCredit;

	private Double pmntBank;

	private Double pmntOther;

	@Ignore
	private Double pmntTotal;

	/**
	 * 
	 */
	public CubeActlPfm() {
	}

	/**
	 * 
	 * @param hotel
	 * @param dto
	 */
	public CubeActlPfm(Hotel hotel, HwFroCubDto dto) {
		super(hotel, dto);
//		LOGGER.info("CubePrfmActl(HwFroCubDto dto)->" + dto);

	}

	public Double getPmntCash() {
		return pmntCash;
	}

	public void setPmntCash(Double pmntCash) {
		this.pmntCash = pmntCash;
	}

	public Double getPmntCredit() {
		return pmntCredit;
	}

	public void setPmntCredit(Double pmntCredit) {
		this.pmntCredit = pmntCredit;
	}

	public Double getPmntBank() {
		return pmntBank;
	}

	public void setPmntBank(Double pmntBank) {
		this.pmntBank = pmntBank;
	}

	public Double getPmntOther() {
		return pmntOther;
	}

	public void setPmntOther(Double pmntOther) {
		this.pmntOther = pmntOther;
	}

	public Double getPmntTotal() {
		return pmntTotal;
	}

	public void setPmntTotal(Double pmntTotal) {
		this.pmntTotal = pmntTotal;
	}

	/**
	 * Visszaadja az entitás measure paraméterrel jelölt mezőjének értékét.
	 * 
	 * @param measNamePart1
	 * @param measNamePart2
	 * @return
	 */
	@Override
	protected Double getValueOfMeasure(Measure measure1, Measure measure2) {
		switch (measure1) {

		case PMNT_CASH:
			if (this.getPmntCash() != null)
				return this.getPmntCash();

		case PMNT_CREDIT:
			if (this.getPmntCredit() != null)
				return this.getPmntCredit();

		case PMNT_BANK:
			if (this.getPmntBank() != null)
				return this.getPmntBank();

		case PMNT_OTHER:
			if (this.getPmntOther() != null)
				return this.getPmntOther();

		case PMNT_TOTAL:
			if (this.getPmntTotal() != null)
				return this.getPmntTotal();

		default:
			return super.getValueOfMeasure(measure1, measure2);
		}
	}
}
