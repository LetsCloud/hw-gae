/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Ignore;

import hu.hw.cloud.shared.cnst.cube.Measure;

/**
 * @author CR
 *
 */
public class CubeDepDetail {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeDepDetail.class.getName());

	private Double room;

	private Double breakfast;

	private Double cityTax;

	private Double ven;

	private Double ren;

	private Double spa;

	private Double fit;

	private Double other;

	@Ignore
	private Double total;

	private Double deposit;

	public Double getRoom() {
		return room;
	}

	public void setRoom(Double room) {
		this.room = room;
	}

	public Double getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Double breakfast) {
		this.breakfast = breakfast;
	}

	public Double getCityTax() {
		return cityTax;
	}

	public void setCityTax(Double cityTax) {
		this.cityTax = cityTax;
	}

	public Double getVen() {
		return ven;
	}

	public void setVen(Double ven) {
		this.ven = ven;
	}

	public Double getRen() {
		return ren;
	}

	public void setRen(Double ren) {
		this.ren = ren;
	}

	public Double getSpa() {
		return spa;
	}

	public void setSpa(Double spa) {
		this.spa = spa;
	}

	public Double getFit() {
		return fit;
	}

	public void setFit(Double fit) {
		this.fit = fit;
	}

	public Double getOther() {
		return other;
	}

	public void setOther(Double other) {
		this.other = other;
	}

	public Double getTotal() {
		return room + breakfast + cityTax + ven + ren + spa + fit + other;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public void clear() {
		room = breakfast = cityTax = ven = ren = spa = fit = other = total = deposit = 0d;
	}

	public void add(CubeDepDetail dd) {
		room += dd.getRoom();
		breakfast += dd.getBreakfast();
		cityTax += dd.getCityTax();
		ven += dd.getVen();
		ren += dd.getRen();
		spa += dd.getSpa();
		fit += dd.getFit();
		other += dd.getOther();
		total += dd.getTotal();
		deposit += dd.getDeposit();
	}

	public Double getMeasValue(Measure measure) {
		switch (measure) {

		case DEP_ROOM:
//			LOGGER.info("getMeasValue()->DEP_ROOM");
			if (this.getRoom() != null) {
//				LOGGER.info("getMeasValue()->DEP_ROOM=" + this.getRoom().doubleValue());
				return this.getRoom().doubleValue();
			}

		case DEP_BRKF:
			if (this.getBreakfast() != null)
				return this.getBreakfast().doubleValue();

		case DEP_CITY_TAX:
			if (this.getCityTax() != null)
				return this.getCityTax().doubleValue();

		case DEP_VEN:
			if (this.getVen() != null)
				return this.getVen().doubleValue();

		case DEP_REN:
			if (this.getRen() != null)
				return this.getRen().doubleValue();

		case DEP_SPA:
			if (this.getSpa() != null)
				return this.getSpa().doubleValue();

		case DEP_FIT:
			if (this.getFit() != null)
				return this.getFit().doubleValue();

		case DEP_OTHER:
			if (this.getOther() != null)
				return this.getOther().doubleValue();

		case DEP_TOTAL:
			if (this.getTotal() != null)
				return this.getTotal().doubleValue();

		case DEP_DEPOSIT:
			if (this.getDeposit() != null)
				return this.getDeposit().doubleValue();

		default:
			return 0d;
		}
	}
}
