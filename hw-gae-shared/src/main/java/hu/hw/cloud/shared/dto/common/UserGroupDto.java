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
	
	private List<AppUserDto> members;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppUserDto> getMembers() {
		return members;
	}

	public void setMembers(List<AppUserDto> members) {
		this.members = members;
	}

}
