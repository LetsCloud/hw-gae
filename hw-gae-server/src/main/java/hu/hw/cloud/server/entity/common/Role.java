/**
 * 
 */
package hu.hw.cloud.server.entity.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.dto.common.RoleDto;

/**
 * @author CR
 *
 */
@Entity
public class Role extends AccountChild {
	private static final Logger LOGGER = LoggerFactory.getLogger(Role.class.getName());

	private static final String PROPERTY_CODE = "code";

	/**
	 * Szerepkör kódja. Indexelt, hogy egyedi kúlcsként működjön.
	 */
	@Index
	private String code;

	/**
	 * Szerepkör megnevezése
	 */
	private String title;

	/**
	 * Szerepkör leírása
	 */
	private String description;

	/**
	 * Jogosultságok
	 */
	private List<String> permissions = new ArrayList<String>();

	public Role() {
		LOGGER.info("Role()");
	}

	public Role(RoleDto dto) {
		super();
		update(dto);
	}

	/**
	 * 
	 * @param dto
	 */
	public void update(RoleDto dto) {
		clearUniqueIndexes();

		super.updEntityWithDto(dto);

		if (dto.getCode() != null) {
			setCode(dto.getCode());
			addUniqueIndex(PROPERTY_CODE, dto.getCode());
		}

		if (dto.getTitle() != null)
			setTitle(dto.getTitle());

		if (dto.getDescription() != null)
			setTitle(dto.getDescription());

		if (dto.getPermissions() != null)
			setPermissions(dto.getPermissions());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	/**
	 * Az entitásból DTO-t hoz létre.
	 * 
	 * @param entity
	 * @return
	 */
	public static RoleDto createDto(Role entity) {
		RoleDto dto = new RoleDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public RoleDto updateDto(RoleDto dto) {
		LOGGER.info("updateDto()-1");
		dto = (RoleDto) super.updDtoWithEntity(dto);
		LOGGER.info("updateDto()-2");

		if (this.getCode() != null)
			dto.setCode(this.getCode());

		if (this.getTitle() != null)
			dto.setTitle(this.getTitle());

		if (this.getDescription() != null)
			dto.setDescription(this.getDescription());

		if (this.getPermissions() != null)
			dto.setPermissions(this.getPermissions());

		return dto;
	}

	/**
	 * Az entitások listájából DTO listát készít
	 * 
	 * @param entities
	 * @return
	 */
	public static List<RoleDto> createDtos(List<Role> entities) {
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for (Role entity : entities)
			dtos.add(createDto(entity));
		return dtos;
	}

	/**
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<Role> createList(List<RoleDto> dtos) {
		List<Role> result = new ArrayList<Role>();
		for (RoleDto dto : dtos) {
			result.add(new Role(dto));
		}
		return result;
	}
}
