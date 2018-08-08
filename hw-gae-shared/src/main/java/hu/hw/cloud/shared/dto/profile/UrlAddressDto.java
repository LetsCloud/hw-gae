/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.cnst.UrlAddressLabel;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class UrlAddressDto implements Dto {

	/**
	 * Email cím típusa: Munkahelyi, Otthoni, Egyéb
	 */
	private UrlAddressLabel label;

	/**
	 * Email cím
	 */
	private String address;

	public UrlAddressLabel getLabel() {
		return label;
	}

	public void setLabel(UrlAddressLabel label) {
		this.label = label;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
