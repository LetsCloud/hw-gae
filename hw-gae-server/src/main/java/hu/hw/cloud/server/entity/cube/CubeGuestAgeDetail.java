/**
 * 
 */
package hu.hw.cloud.server.entity.cube;

import com.googlecode.objectify.annotation.Ignore;

import hu.hw.cloud.shared.cnst.cube.Measure;

/**
 * @author CR
 *
 */
public class CubeGuestAgeDetail {

	private Integer adult;

	private Integer teen;

	private Integer child;

	private Integer infant;

	@Ignore
	private Integer total;

	public CubeGuestAgeDetail() {
		adult = teen = child = infant = 0;
	}

	public Integer getAdult() {
		return adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public Integer getTeen() {
		return teen;
	}

	public void setTeen(Integer teen) {
		this.teen = teen;
	}

	public Integer getChild() {
		return child;
	}

	public void setChild(Integer child) {
		this.child = child;
	}

	public Integer getInfant() {
		return infant;
	}

	public void setInfant(Integer infant) {
		this.infant = infant;
	}

	public Integer getTotal() {
		return adult + teen + child;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getMeasValue(Measure measure) {
		switch (measure) {

		case GUEST_ADULT:
			if (this.getAdult() != null)
				return this.getAdult().doubleValue();

		case GUEST_TEEN:
			if (this.getTeen() != null)
				return this.getTeen().doubleValue();

		case GUEST_CHILD:
			if (this.getChild() != null)
				return this.getChild().doubleValue();

		case GUEST_INFANT:
			if (this.getInfant() != null)
				return this.getInfant().doubleValue();

		case GUEST_TOTAL:
			if (this.getTotal() != null)
				return this.getTotal().doubleValue();

		default:
			return 0d;
		}
	}

}
