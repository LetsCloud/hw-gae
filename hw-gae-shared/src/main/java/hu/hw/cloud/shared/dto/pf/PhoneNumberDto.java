/**
 * 
 */
package hu.hw.cloud.shared.dto.pf;

import hu.hw.cloud.shared.cnst.PhoneNumberLabel;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class PhoneNumberDto implements Dto {

	/**
	 * Elsődleges telefonszám.
	 */
	private Boolean primary;

	/**
	 * Telefonszám típusa: Munkahelyi, Otthoni, Mobil ...
	 */
	private PhoneNumberLabel label;

	/**
	 * Emberi szemmel olvasható telefonszám
	 */
	private String text;

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public PhoneNumberLabel getLabel() {
		return label;
	}

	public void setLabel(PhoneNumberLabel label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
