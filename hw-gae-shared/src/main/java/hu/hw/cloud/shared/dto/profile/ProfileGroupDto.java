/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.cnst.ProfileType;
import hu.hw.cloud.shared.dto.common.AccountChildDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ProfileGroupDto extends AccountChildDtor {

	/**
	 * Egyedi azonosító
	 */
	private String code;

	/**
	 * Név
	 */
	private String description;

	/**
	 * Profil típus
	 */
	private ProfileType type;

	/**
	 * Aktív, Inaktív
	 */
	private Boolean active;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		String ret = "ProfileGroupDto:{" + super.toString() + ", code=" + code + ", name=" + description + ", type=" + type
				+ "}";
		return ret;
	}
}
