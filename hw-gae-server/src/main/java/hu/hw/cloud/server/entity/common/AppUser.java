/**
 * 
 */
package hu.hw.cloud.server.entity.common;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.FcmTokenDto;
import hu.hw.cloud.shared.dto.common.RoleDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.server.entity.VerificationToken;
import hu.hw.cloud.server.entity.chat.FcmToken;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.security.RegistrationListener;

/**
 * @author CR
 *
 */
@Entity
public class AppUser extends AccountChild {
	private static final Logger LOGGER = Logger.getLogger(RegistrationListener.class.getName());

	/**
	 * 
	 */
	private static final String PROPERTY_USERNAME = "username";
	private static final String PROPERTY_EMAILADDRESS = "emailAddress";

	/**
	 * Kód
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

	/**
	 * Fotó
	 */
	private String picture;

	/**
	 * Bejelentkezőnév
	 */
	@Index
	private String username;

	/**
	 * Hash-elt jelszó
	 */
	private String password;

	/**
	 * Email cím
	 */
	@Index
	private String emailAddress;

	/**
	 * Verifikációs tóken
	 */
	private List<VerificationToken> verificationTokens;

	/**
	 * Engedélyezett
	 */
	private Boolean enabled;

	/**
	 * Rendszergazda kapcsoló
	 */
	private Boolean admin;

	/**
	 * Szerepkörök
	 */
	private List<Ref<Role>> roles = new ArrayList<Ref<Role>>();

	/**
	 * Alapértelmezett hotel
	 */
	private Ref<Hotel> defaultHotel;

	/**
	 * Hotelek
	 */
	private List<Ref<Hotel>> accessibleHotels = new ArrayList<Ref<Hotel>>();

	/**
	 * Firebase Messaging Token
	 */
	private List<FcmToken> fcmTokens = new ArrayList<FcmToken>();

	/**
	 * Firebase Messaging Token
	 */
	private List<Ref<UserGroup>> userGroups = new ArrayList<Ref<UserGroup>>();

	/**
	 * Paraméter nélküli kontruktor Objectify-hoz
	 */
	public AppUser() {
		LOGGER.info("AppUser()");
		this.enabled = false;
		this.admin = false;
	}

	/**
	 * Konstruktor DTO-ból
	 * 
	 * @param dto
	 */
	public AppUser(AppUserDto dto) {
		this();
		update(dto);
	}

	/**
	 * Kontruktor RegisterDTO-ból
	 * 
	 * @param registerDto
	 */
	public AppUser(RegisterDto registerDto) {
		this();
		setUsername(registerDto.getUsername());
		setPassword(registerDto.getUserPassword());
		setEmailAddress(registerDto.getUserEmail());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	public void setPassword(String hashedPassword) {
		this.password = hashedPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public List<VerificationToken> getVerificationTokens() {
		return verificationTokens;
	}

	public void setVerificationTokens(List<VerificationToken> verificationTokens) {
		this.verificationTokens = verificationTokens;
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

	public List<Role> getRoles() {
		List<Role> list = new ArrayList<Role>();
		for (Ref<Role> ref : roles) {
			list.add(ref.get());
		}
		return list;
	}

	public void setRoles(List<Role> roles) {
		ArrayList<Ref<Role>> list = new ArrayList<Ref<Role>>();
		for (Role role : roles) {
			list.add(Ref.create(role));
		}
		this.roles = list;
	}

	public List<Hotel> getAccessibleHotels() {
		List<Hotel> list = new ArrayList<Hotel>();
		for (Ref<Hotel> ref : accessibleHotels) {
			list.add(ref.get());
		}
		return list;
	}

	public void setAccessibleHotels(List<Hotel> accessibleHotels) {
		ArrayList<Ref<Hotel>> list = new ArrayList<Ref<Hotel>>();
		for (Hotel hotel : accessibleHotels) {
			list.add(Ref.create(hotel));
		}
		this.accessibleHotels = list;
	}

	public Hotel getDefaultHotel() {
		try {
			return defaultHotel.get();
		} catch (NullPointerException e) {
			return null;
		}

	}

	public void setDefaultHotel(Hotel defaultHotel) {
		this.defaultHotel = Ref.create(defaultHotel);
	}

	public List<FcmToken> getFcmTokens() {
		return fcmTokens;
	}

	public void setFcmTokens(List<FcmToken> fcmTokens) {
		this.fcmTokens = fcmTokens;
	}

	public List<UserGroup> getUserGroups() {
		List<UserGroup> list = new ArrayList<UserGroup>();
		for (Ref<UserGroup> ref : userGroups) {
			list.add(ref.get());
		}
		return list;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		ArrayList<Ref<UserGroup>> list = new ArrayList<Ref<UserGroup>>();
		for (UserGroup userGroup : userGroups) {
			list.add(Ref.create(userGroup));
		}
		this.userGroups = list;
	}

	/**
	 * Entitás módosítása DTO alapján
	 * 
	 * @param dto
	 */
	public void update(AppUserDto dto) {
		clearUniqueIndexes();

		super.update(dto);

		if (dto.getCode() != null)
			setCode(dto.getCode());

		if (dto.getName() != null)
			setName(dto.getName());

		if (dto.getPicture() != null)
			setPicture(dto.getPicture());

		if (dto.getAdmin() != null)
			setAdmin(dto.getAdmin());

		if (dto.getEmailAddress() != null) {
			if (!dto.getEmailAddress().equals(getEmailAddress())) {
				LOGGER.info("PROPERTY_EMAILADDRESS"+dto.getEmailAddress()+"/"+getEmailAddress());
				setEmailAddress(dto.getEmailAddress());
				addUniqueIndex(PROPERTY_EMAILADDRESS, dto.getEmailAddress());
			}
		}

		if (dto.getEnabled() != null)
			setEnabled(dto.getEnabled());

		if (dto.getUsername() != null) {
			if (!dto.getUsername().equals(getUsername())) {
				LOGGER.info("PROPERTY_USERNAME->"+dto.getUsername()+"/"+getUsername());
				setUsername(dto.getUsername());
				addUniqueIndex(PROPERTY_USERNAME, dto.getUsername());
			}
		}

		if (dto.getPassword() != null)
			setPassword(dto.getPassword());

		if (dto.getRoleDtos() != null)
			setRoles(Role.createList(dto.getRoleDtos()));

		if (dto.getAccessibleHotelDtos() != null)
			setAccessibleHotels(Hotel.createList(dto.getAccessibleHotelDtos()));

		if (dto.getFcmTokenDtos() != null)
			setFcmTokens(FcmToken.createList(dto.getFcmTokenDtos()));

		if (dto.getDefaultHotelDto() != null)
			setDefaultHotel(new Hotel(dto.getDefaultHotelDto()));

		if (dto.getUserGroupDtos() != null)
			setUserGroups(UserGroup.createList(dto.getUserGroupDtos()));
	}

	/**
	 * DTO létrehozása entitásból
	 * 
	 * @param entity
	 * @return
	 */
	public static AppUserDto createDto(AppUser entity) {
		AppUserDto dto = new AppUserDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * DTO módosítása entitás attribútumokkal
	 * 
	 * @param dto
	 * @return
	 */
	public AppUserDto updateDto(AppUserDto dto) {
		dto = (AppUserDto) super.updateDto(dto);

		if (this.getCode() != null)
			dto.setCode(this.getCode());

		if (this.getName() != null)
			dto.setName(this.getName());

		if (this.getPicture() != null)
			dto.setPicture(this.getPicture());

		if (this.getUsername() != null)
			dto.setUsername(this.getUsername());

		if (this.getPassword() != null)
			dto.setPassword(this.getPassword());

		if (this.getEmailAddress() != null)
			dto.setEmailAddress(this.getEmailAddress());

		if (this.getEnabled() != null)
			dto.setEnabled(this.getEnabled());

		if (this.getAdmin() != null)
			dto.setAdmin(this.getAdmin());

		if (this.getRoles() != null) {
			dto.setRoleDtos(Role.createDtos(this.getRoles()));
		} else {
			dto.setRoleDtos(new ArrayList<RoleDto>());
		}

		if (this.getAccessibleHotels() != null) {
			dto.setAccessibleHotelDtos(Hotel.createDtos(this.getAccessibleHotels()));
		} else {
			dto.setAccessibleHotelDtos(new ArrayList<HotelDto>());
		}

		if (this.getFcmTokens() != null) {
			dto.setFcmTokenDtos(FcmToken.createDtos(this.getFcmTokens()));
		} else {
			dto.setFcmTokenDtos(new ArrayList<FcmTokenDto>());
		}

		if (this.getDefaultHotel() != null)
			dto.setDefaultHotelDto(Hotel.createDto(this.getDefaultHotel()));

		if (this.getUserGroups() != null) {
			dto.setUserGroupDtos(UserGroup.createDtos(this.getUserGroups()));
		} else {
			dto.setUserGroupDtos(new ArrayList<UserGroupDto>());
		}

		return dto;
	}

	public static List<AppUserDto> createDtos(List<AppUser> entities) {
		List<AppUserDto> dtos = new ArrayList<AppUserDto>();
		for (AppUser entity : entities) {
			dtos.add(AppUser.createDto(entity));
		}
		return dtos;
	}

	/**
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<AppUser> createList(List<AppUserDto> dtos) {
		List<AppUser> result = new ArrayList<AppUser>();
		for (AppUserDto dto : dtos) {
			result.add(new AppUser(dto));
		}
		return result;
	}

	@Override
	public String toString() {
		String ret = "User:{" + super.toString() + ", username=" + username + ", password=" + password
				+ ", emailAddress=" + emailAddress + ", enabled=" + enabled + ", admin=" + admin + "}";
		return ret;
	}

	public static List<AppUser> ref2EntityList(List<Ref<AppUser>> refList) {
		List<AppUser> list = new ArrayList<AppUser>();
		for (Ref<AppUser> ref : refList) {
			list.add(ref.get());
		}
		return list;
	}

	public static List<Ref<AppUser>> entity2RefList(List<AppUser> entityList) {
		List<Ref<AppUser>> list = new ArrayList<Ref<AppUser>>();
		for (AppUser entity : entityList) {
			list.add(Ref.create(entity));
		}
		return list;
	}
}
