/**
 * 
 */
package hu.hw.cloud.shared.dto.reservation;

import hu.hw.cloud.shared.cnst.ProfileType;
import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ProfileLinkDto implements Dto {

	private ProfileType type;

	private OrganizationDto organization;

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public OrganizationDto getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDto organization) {
		this.organization = organization;
	}
}
