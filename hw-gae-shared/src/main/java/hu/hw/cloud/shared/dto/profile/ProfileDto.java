/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.dto.common.AccountChildDtor;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ProfileDto extends AccountChildDtor {

	private String name;

	private ProfileGroupDto profileGroup;

	private List<CommunicationDto> communications = new ArrayList<CommunicationDto>();

	private List<AddressDto> addresses = new ArrayList<AddressDto>();

	private List<WebPresenceDto> webPresences = new ArrayList<WebPresenceDto>();

	private List<ProfileLinkDto> profileLinks = new ArrayList<ProfileLinkDto>();

	public ProfileDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProfileGroupDto getProfileGroup() {
		return profileGroup;
	}

	public void setProfileGroup(ProfileGroupDto profileGroup) {
		this.profileGroup = profileGroup;
	}

	public List<CommunicationDto> getCommunications() {
		return communications;
	}

	public void setCommunications(List<CommunicationDto> communications) {
		this.communications = communications;
	}

	public List<AddressDto> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}

	public List<WebPresenceDto> getWebPresences() {
		return webPresences;
	}

	public void setWebPresences(List<WebPresenceDto> webPresences) {
		this.webPresences = webPresences;
	}

	public List<ProfileLinkDto> getProfileLinks() {
		return profileLinks;
	}

	public void setProfileLinks(List<ProfileLinkDto> profileLinks) {
		this.profileLinks = profileLinks;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "ProfileDto:{" + super.toString() + ", name=" + name + ", profileGroup=" + profileGroup
				+ ", communications=";

		for (CommunicationDto communication : communications)
			ret = ret + communication;

		ret = ret + ", addresses=";

		for (AddressDto address : addresses)
			ret = ret + address;

		ret = ret + ", webPresences=";

		for (WebPresenceDto webPresence : webPresences)
			ret = ret + webPresence;

		ret = ret + "}";
		return ret;
	}

}
