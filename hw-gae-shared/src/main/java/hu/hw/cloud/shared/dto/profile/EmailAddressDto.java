/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.cnst.EmailAddressLabel;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class EmailAddressDto implements Dto {

	/**
	 * Elsődleges email cím.
	 */
	private Boolean primary;

	/**
	 * Email cím típusa: Munkahelyi, Otthoni, Egyéb
	 */
	private EmailAddressLabel label;

	/**
	 * Email cím
	 */
	private String address;

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public EmailAddressLabel getLabel() {
		return label;
	}

	public void setLabel(EmailAddressLabel label) {
		this.label = label;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
