/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

import hu.hw.cloud.shared.dto.profile.CustomerDto;
import hu.hw.cloud.shared.dto.profile.ProfileDto;

/**
 * @author CR
 *
 */
@Subclass(index = true)
public class Customer extends Profile {

	public static CustomerDto createDto(Customer entity) {
		CustomerDto dto = new CustomerDto();
		dto.setCode(entity.getCode());
		
		dto.setEmailAddressDtos(EmailAddress.createDtos(entity.getEmailAddresses()));
		dto.setName(entity.getName());
		dto.setPhoneNumberDtos(PhoneNumber.createDtos(entity.getPhoneNumbers()));
		
		return dto;
	}

	@Index
	private String code;

	private String taxNumber;

	private String euTaxNumber;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getEuTaxNumber() {
		return euTaxNumber;
	}

	public void setEuTaxNumber(String euTaxNumber) {
		this.euTaxNumber = euTaxNumber;
	}

}
