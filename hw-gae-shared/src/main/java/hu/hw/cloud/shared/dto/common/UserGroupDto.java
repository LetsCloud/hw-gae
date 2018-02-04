/**
 * 
 */
package hu.hw.cloud.shared.dto.common;

import java.util.List;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class UserGroupDto extends AccountChildDto {
	
	private String name;
	
	private List<AppUserDto> memberDtos;

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
