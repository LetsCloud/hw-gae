/**
 * 
 */
package hu.hw.cloud.server.entity.common;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
@Entity
public class UserGroup extends AccountChild {
	/**
	 * 
	 */
	private static final String PROPERTY_NAME = "name";

	@Index
	private String name;

	private List<AppUser> members = new ArrayList<AppUser>();

	public UserGroup() {
	}

	public UserGroup(UserGroupDto dto) {
		this();
		update(dto);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUser> getMembers() {
		return members;
	}

	public void setMembers(List<AppUser> members) {
		this.members = members;
	}

	public static UserGroupDto createDto(UserGroup entity) {
		UserGroupDto dto = new UserGroupDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	public static List<UserGroupDto> createDtos(List<UserGroup> entities) {
		List<UserGroupDto> dtos = new ArrayList<UserGroupDto>();
		for (UserGroup entity : entities) {
			dtos.add(UserGroup.createDto(entity));
		}
		return dtos;
	}

	public UserGroupDto updateDto(UserGroupDto dto) {
		dto = (UserGroupDto) super.updateDto(dto);

		if (this.getName() != null)
			dto.setName(this.getName());

		if (this.getMembers() != null) {
			dto.setMemberDtos(AppUser.createDtos(this.getMembers()));
		} else {
			dto.setMemberDtos(new ArrayList<AppUserDto>());
		}

		return dto;
	}

	/**
	 * Entitás módosítása DTO alapján
	 * 
	 * @param dto
	 */
	public void update(UserGroupDto dto) {
		super.update(dto);

		clearUniqueIndexes();

		if (dto.getName() != null) {
			setName(dto.getName());
			addUniqueIndex(PROPERTY_NAME, dto.getName());
		}

		if (dto.getMemberDtos() != null)
			setMembers(AppUser.createList(dto.getMemberDtos()));
	}

	@Override
	public String toString() {
		String ret = "UserG:roup{" + super.toString() + ", name=" + name + "}";
		return ret;
	}

	/**
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<UserGroup> createList(List<UserGroupDto> dtos) {
		List<UserGroup> result = new ArrayList<UserGroup>();
		for (UserGroupDto dto : dtos) {
			result.add(new UserGroup(dto));
		}
		return result;
	}

}
