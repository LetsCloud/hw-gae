/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.shared.cnst.ProfileType;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.ExceptionType;

/**
 * @author robi
 *
 */
@Entity
public class ProfileGroup extends AccountChild {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroup.class);

	public static final String PROFILEGROUP_CODE = "code";
	private static final String PROFILEGROUP_DESCRIPTION = "description";

	/**
	 * Egyedi azonosító
	 */
	@Index
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

	/**
	 * 
	 */
	public ProfileGroup() {
		logger.info("ProfileGroup()");
	}

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
		return "ProfileGroup:[code=" + code + ", description=" + description + ", type=" + type + ", active=" + active
				+ "]>>" + super.toString();
	}

	@Override
	public void validate() throws EntityValidationException {
		/*
		 * if (this.getAccountWebSafeKey() == null) { throw new
		 * EntityValidationException(ExceptionType.MISSING_VALUE,
		 * getClass().getSimpleName(), PROPERTY_ACCOUNTID); }
		 */
		if (this.getCode() == null) {
			throw new EntityValidationException(ExceptionType.MISSING_VALUE, getClass().getSimpleName(),
					PROFILEGROUP_CODE);
		}
		if (this.getDescription() == null) {
			throw new EntityValidationException(ExceptionType.MISSING_VALUE, getClass().getSimpleName(),
					PROFILEGROUP_DESCRIPTION);
		}
	}
}
