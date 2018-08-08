/**
 * 
 */
package hu.hw.cloud.shared.dto.common;

import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.dto.profile.PostalAddressDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class AccountDto extends BaseDto {

	/**
	 * Név
	 */
	private String name;

	/**
	 * Cím
	 */
	private PostalAddressDto postalAddress = new PostalAddressDto();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PostalAddressDto getPostalAddressDto() {
		return postalAddress;
	}

	public void setPostalAddressDto(PostalAddressDto postalAddress) {
		this.postalAddress = postalAddress;
	}

	@Override
	public String toString() {
		String ret = "AccountDto:{" + super.toString() + ", name=" + name + "}";
		return ret;
	}

}
