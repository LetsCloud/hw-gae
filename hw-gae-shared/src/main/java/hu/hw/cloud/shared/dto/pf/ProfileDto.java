/**
 * 
 */
package hu.hw.cloud.shared.dto.pf;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.dto.common.AccountChildDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ProfileDto extends AccountChildDto {

	private String name;

	private List<PhoneNumberDto> phoneNumberDtos = new ArrayList<PhoneNumberDto>();

	private List<EmailAddressDto> emailAddressDtos = new ArrayList<EmailAddressDto>();

	private List<PostalAddressDto> postalAddressDtos = new ArrayList<PostalAddressDto>();

	private List<UrlAddressDto> urlAddressDtos = new ArrayList<UrlAddressDto>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PhoneNumberDto> getPhoneNumberDtos() {
		return phoneNumberDtos;
	}

	public void setPhoneNumberDtos(List<PhoneNumberDto> phoneNumberDtos) {
		this.phoneNumberDtos = phoneNumberDtos;
	}

	public List<EmailAddressDto> getEmailAddressDtos() {
		return emailAddressDtos;
	}

	public void setEmailAddressDtos(List<EmailAddressDto> emailAddressDtos) {
		this.emailAddressDtos = emailAddressDtos;
	}

	public List<PostalAddressDto> getPostalAddressDtos() {
		return postalAddressDtos;
	}

	public void setPostalAddressDtos(List<PostalAddressDto> postalAddressDtos) {
		this.postalAddressDtos = postalAddressDtos;
	}

	public List<UrlAddressDto> getUrlAddressDtos() {
		return urlAddressDtos;
	}

	public void setUrlAddressDtos(List<UrlAddressDto> urlAddressDtos) {
		this.urlAddressDtos = urlAddressDtos;
	}
}
