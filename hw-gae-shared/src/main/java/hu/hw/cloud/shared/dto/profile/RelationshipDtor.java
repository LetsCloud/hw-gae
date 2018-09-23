/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.dto.common.AccountChildDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class RelationshipDtor extends AccountChildDtor {

	private String forward;

	private String reverse;

	private Boolean active;

	public RelationshipDtor() {
	};

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getReverse() {
		return reverse;
	}

	public void setReverse(String reverse) {
		this.reverse = reverse;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "RelationshipDtor:{" + "forward=" + forward + ", reverse=" + reverse + ", active=" + active + ", "
				+ super.toString() + "}";
		return ret;
	}

}
