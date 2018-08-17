/**
 * 
 */
package hu.hw.cloud.shared.dto.reservation;

import hu.hw.cloud.shared.cnst.ProfileType;
import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.profile.CustomerDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ProfileLinkDto implements Dto {

	private ProfileType type;

	private CustomerDto customerDto;

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public CustomerDto getCustomerDto() {
		return customerDto;
	}

	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
}
