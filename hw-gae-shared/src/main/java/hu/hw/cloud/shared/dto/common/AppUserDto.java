/**
 * 
 */
package hu.hw.cloud.shared.dto.common;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class AppUserDto extends AccountChildDto {

	private String picture;
	/**
	 * Kód
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

	/**
	 * Bejelentkezőnév
	 */
	private String username;

	/**
	 * Hash-elt jelszó
	 */
	private String password;

	/**
	 * Email cím
	 */
	private String emailAddress;

	/**
	 * 
	 */
	private Boolean enabled;

	/**
	 * 
	 */
	private Boolean admin;

	/**
	 * 
	 */
	private UserGroupDto group;

	/**
	 * 
	 */
	private List<RoleDto> roleDtos = new ArrayList<RoleDto>();

	/**
	 * 
	 */
	private List<HotelDto> accessibleHotelDtos = new ArrayList<HotelDto>();

	/**
	 * 
	 */
	private List<FcmTokenDto> fcmTokenDtos = new ArrayList<FcmTokenDto>();

	/**
	 * 
	 */
	private HotelDto defaultHotelDto;

	public AppUserDto() {
	}

	public AppUserDto(String picture, Boolean enabled, String name,String emailAddress) {
		this();
		this.picture = picture;
		this.enabled = enabled;
		this.name = name;
		this.emailAddress = emailAddress;
		this.picture = picture;
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(List<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}

	public HotelDto getDefaultHotelDto() {
		return defaultHotelDto;
	}

	public void setDefaultHotelDto(HotelDto defaultHotelDto) {
		this.defaultHotelDto = defaultHotelDto;
	}

	public List<HotelDto> getAccessibleHotelDtos() {
		return accessibleHotelDtos;
	}

	public void setAccessibleHotelDtos(List<HotelDto> accessibleHotelDtos) {
		this.accessibleHotelDtos = accessibleHotelDtos;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public UserGroupDto getGroup() {
		return group;
	}

	public void setGroup(UserGroupDto group) {
		this.group = group;
	}

	public List<FcmTokenDto> getFcmTokenDtos() {
		return fcmTokenDtos;
	}

	public void setFcmTokenDtos(List<FcmTokenDto> fcmTokenDtos) {
		this.fcmTokenDtos = fcmTokenDtos;
	}

	@Override
	public String toString() {
		String ret = "UserDto:{" + super.toString() + ", username=" + username + ", password=" + password
				+ ", emailAddress=" + emailAddress + ", enabled=" + enabled + ", admin=" + admin + ", roleDtos="
				+ roleDtos + "}";
		return ret;
	}
}
