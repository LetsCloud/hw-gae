/**
 * 
 */
package hu.hw.cloud.shared.dto.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class UserGroupDto extends AccountChildDto {

	private String name;

	private List<AppUserDto> memberDtos = new ArrayList<AppUserDto>();

	public UserGroupDto() {
	}

	public UserGroupDto(AppUserDto appUserDto) {
		super(appUserDto);
		this.name = appUserDto.getName();
		this.memberDtos.add(appUserDto);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUserDto> getMemberDtos() {
		return memberDtos;
	}

	public void setMemberDtos(List<AppUserDto> memberDtos) {
		this.memberDtos = memberDtos;
	}

}
