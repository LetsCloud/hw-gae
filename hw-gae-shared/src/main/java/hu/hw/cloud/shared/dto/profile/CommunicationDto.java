/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.cnst.CommMode;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class CommunicationDto implements Dto {

	/**
	 * Elsődleges telefonszám.
	 */
	private Boolean primary;

	/**
	 * Telefonszám típusa: Munkahelyi, Otthoni, Mobil ...
	 */
	private CommMode mode;

	/**
	 * Emberi szemmel olvasható telefonszám
	 */
	private String parameter;

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public CommMode getMode() {
		return mode;
	}

	public void setMode(CommMode mode) {
		this.mode = mode;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
