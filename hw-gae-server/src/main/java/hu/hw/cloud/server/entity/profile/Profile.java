/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.shared.dto.profile.ProfileDto;

/**
 * @author CR
 *
 */
@Entity
public class Profile extends AccountChild {

	private String name;

	private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

	private List<EmailAddress> emailAddresses = new ArrayList<EmailAddress>();

	private List<PostalAddress> postalAddresses = new ArrayList<PostalAddress>();

	private List<UrlAddress> urlAddresses = new ArrayList<UrlAddress>();

	public Profile() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public List<PostalAddress> getPostalAddresses() {
		return postalAddresses;
	}

	public void setPostalAddresses(List<PostalAddress> postalAddresses) {
		this.postalAddresses = postalAddresses;
	}

	public List<UrlAddress> getUrlAddresses() {
		return urlAddresses;
	}

	public void setUrlAddresses(List<UrlAddress> urlAddresses) {
		this.urlAddresses = urlAddresses;
	}

	public ProfileDto createDto() {
		ProfileDto dto = new ProfileDto();
		dto = updateDto(dto);
		return dto;
	}

	public ProfileDto updateDto(ProfileDto dto) {
		dto = (ProfileDto) super.updDtoWithEntity(dto);

		if (getEmailAddresses() != null)
			dto.setEmailAddressDtos(EmailAddress.createDtos(getEmailAddresses()));
		dto.setName(getName());
		if (getPhoneNumbers() != null)
			dto.setPhoneNumberDtos(PhoneNumber.createDtos(getPhoneNumbers()));
		if (getPostalAddresses() != null)
			dto.setPostalAddressDtos(PostalAddress.createDtos(getPostalAddresses()));
		if (getUrlAddresses() != null)
			dto.setUrlAddressDtos(UrlAddress.createDtos(getUrlAddresses()));

		return dto;
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder extends AccountChild.Builder<Builder> {

		private String name;

		private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

		private List<EmailAddress> emailAddresses = new ArrayList<EmailAddress>();

		private List<PostalAddress> postalAddresses = new ArrayList<PostalAddress>();

		private List<UrlAddress> urlAddresses = new ArrayList<UrlAddress>();

		public Builder() {
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder phoneNumbers(List<PhoneNumber> phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
			return this;
		}

		public Builder addPhoneNumber(PhoneNumber phoneNumber) {
			this.phoneNumbers.add(phoneNumber);
			return this;
		}

		public Builder emailAddresses(List<EmailAddress> emailAddresses) {
			this.emailAddresses = emailAddresses;
			return this;
		}

		public Builder addEmailAddress(EmailAddress emailAddress) {
			this.emailAddresses.add(emailAddress);
			return this;
		}

		public Builder postalAddresses(List<PostalAddress> postalAddresses) {
			this.postalAddresses = postalAddresses;
			return this;
		}

		public Builder addPostalAddress(PostalAddress postalAddress) {
			this.postalAddresses.add(postalAddress);
			return this;
		}

		public Builder urlAddresses(List<UrlAddress> urlAddresses) {
			this.urlAddresses = urlAddresses;
			return this;
		}

		public Builder addUrlAddress(UrlAddress urlAddresses) {
			this.urlAddresses.add(urlAddresses);
			return this;
		}

		public Profile build() {
			return new Profile(this);
		}
	}

	protected Profile(Builder builder) {
		super(builder);
		this.setName(builder.name);
		this.setPhoneNumbers(builder.phoneNumbers);
		this.setEmailAddresses(builder.emailAddresses);
		this.setPostalAddresses(builder.postalAddresses);
		this.setUrlAddresses(builder.urlAddresses);
	}
}
