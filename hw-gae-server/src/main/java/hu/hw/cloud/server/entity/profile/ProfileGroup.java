/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto.ProfileType;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.ExceptionType;

/**
 * @author robi
 *
 */
@Entity
public class ProfileGroup extends AccountChild {
	private static final String PROFILEGROUP_CODE = "code";
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
//		logger.info("Hotel()");
	}

	/**
	 * 
	 * @param dto
	 */
	public ProfileGroup(ProfileGroupDto dto) {
		this();
		updEntityWithDto(dto);
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
		return "ProfileGroup [ generatedId=" + this.getId() + ", code=" + code + ", name=" + description + "]";
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

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public ProfileGroup updEntityWithDto(ProfileGroupDto dto) {
		clearUniqueIndexes();

		super.updEntityWithDto(dto);

		if (dto.getCode() != null) {
			setCode(dto.getCode());
			if (!dto.getCode().equals(this.getCode()))
				addUniqueIndex(PROFILEGROUP_CODE, dto.getCode());
		}

		if (dto.getDescription() != null)
			setDescription(dto.getDescription());

		if (dto.getType() != null)
			setType(dto.getType());

		if (dto.getActive() != null)
			setActive(dto.getActive());

		return this;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static ProfileGroupDto createDto(ProfileGroup entity) {
		ProfileGroupDto dto = new ProfileGroupDto();
		dto = entity.updDtoWithEntity(dto);
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public ProfileGroupDto updDtoWithEntity(ProfileGroupDto dto) {
		dto = (ProfileGroupDto) super.updDtoWithEntity(dto);

		if (getCode() != null)
			dto.setCode(getCode());

		if (getDescription() != null)
			dto.setDescription(getDescription());

		if (getType() != null)
			dto.setType(getType());

		if (getActive() != null)
			dto.setActive(getActive());

		return dto;
	}

	/**
	 * 
	 * @param entities
	 * @return
	 */
	public static List<ProfileGroupDto> createDtos(List<ProfileGroup> entities) {
		List<ProfileGroupDto> dtos = new ArrayList<ProfileGroupDto>();
		for (ProfileGroup entity : entities) {
			dtos.add(ProfileGroup.createDto(entity));
		}
		return dtos;
	}

	/**
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<ProfileGroup> createList(List<ProfileGroupDto> dtos) {
		List<ProfileGroup> entities = new ArrayList<ProfileGroup>();
		for (ProfileGroupDto dto : dtos) {
			entities.add(new ProfileGroup(dto));
		}
		return entities;
	}

	public static Ref<Hotel> createRef(String webSafeString) {
		Key<Hotel> key = Key.create(webSafeString);
		return Ref.create(key);
	}

	public String createWebSafeKey(Ref<Hotel> ref) {
		Key<Hotel> key = ref.getKey();
		return key.getString();
	}
}
