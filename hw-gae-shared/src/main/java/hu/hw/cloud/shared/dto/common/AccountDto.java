/**
 * 
 */
package hu.hw.cloud.shared.dto.common;

import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.dto.profile.AddressDto;

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
	private AddressDto address = new AddressDto();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	@Override
	public String toString() {
		String ret = "AccountDto:{name=" + name + ", " + super.toString() + "}";
		return ret;
	}

}
