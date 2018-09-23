/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.dto.common.AccountChildDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ProfileLinkDto extends AccountChildDtor {

	private ProfileDtor profile;

	private RelationshipDtor relationship;

	private Boolean switched;

	public ProfileLinkDto() {
	}

	public ProfileDtor getProfile() {
		return profile;
	}

	public void setProfile(ProfileDtor profile) {
		this.profile = profile;
	}

	public Boolean getSwitched() {
		return switched;
	}

	public void setSwitched(Boolean switched) {
		this.switched = switched;
	}

	public RelationshipDtor getRelationship() {
		return relationship;
	}

	public void setRelationship(RelationshipDtor relationship) {
		this.relationship = relationship;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "ProfileLinkDto:{ profile=" + profile + ", relationship=" + relationship + ", reverse=" + switched
				+ ", " + super.toString() + "}";
		return ret;
	}
}
