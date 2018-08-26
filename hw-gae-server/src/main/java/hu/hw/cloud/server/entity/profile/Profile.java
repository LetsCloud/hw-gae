/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.common.AccountChild;

/**
 * A Profil entitás a Customer, Contact és Guest entitások őse.
 * 
 * @author CR
 *
 */
@Entity
public class Profile extends AccountChild {

	/**
	 * A Profil neve.
	 */
	private String name;

	/**
	 * A Profil csoportja.
	 */
	private Ref<ProfileGroup> profileGroup;

	private List<Communication> communications = new ArrayList<Communication>();

	private List<Address> addresses = new ArrayList<Address>();

	private List<WebPresence> webPresences = new ArrayList<WebPresence>();

	public Profile() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProfileGroup getProfileGroup() {
		if (profileGroup == null)
			return null;
		return profileGroup.get();
	}

	public void setProfileGroup(ProfileGroup profileGroup) {
		if (profileGroup != null)
			this.profileGroup = Ref.create(profileGroup);
	}

	public List<Communication> getCommunications() {
		return communications;
	}

	public void setCommunications(List<Communication> communications) {
		this.communications = communications;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<WebPresence> getWebPresences() {
		return webPresences;
	}

	public void setWebPresences(List<WebPresence> webPresences) {
		this.webPresences = webPresences;
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder extends AccountChild.Builder<Builder> {

		private String name;

		private List<Communication> phoneNumbers = new ArrayList<Communication>();

		private List<EmailAddress> emailAddresses = new ArrayList<EmailAddress>();

		private List<Address> postalAddresses = new ArrayList<Address>();

		private List<WebPresence> urlAddresses = new ArrayList<WebPresence>();

		public Builder() {
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder phoneNumbers(List<Communication> phoneNumbers) {
			this.phoneNumbers = phoneNumbers;
			return this;
		}

		public Builder addPhoneNumber(Communication phoneNumber) {
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

		public Builder postalAddresses(List<Address> postalAddresses) {
			this.postalAddresses = postalAddresses;
			return this;
		}

		public Builder addPostalAddress(Address postalAddress) {
			this.postalAddresses.add(postalAddress);
			return this;
		}

		public Builder urlAddresses(List<WebPresence> urlAddresses) {
			this.urlAddresses = urlAddresses;
			return this;
		}

		public Builder addUrlAddress(WebPresence urlAddresses) {
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
		this.setWebPresences(builder.urlAddresses);
	}
}
