/**
 * 
 */
package hu.hw.cloud.server.entity.common;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

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

/**
 * @author CR
 *
 */
@Entity
public class AppUser extends AccountChild {
//	private static final Logger logger = LoggerFactory.getLogger(AppUser.class.getName());

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
	 * Név
	 */
	private String title;

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
	private List<Ref<Hotel>> availableHotels = new ArrayList<Ref<Hotel>>();

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
//		logger.info("AppUser()");
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
		updEntityWithDto(dto);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Hotel> getAvailableHotels() {
		List<Hotel> list = new ArrayList<Hotel>();
		for (Ref<Hotel> ref : availableHotels) {
			list.add(ref.get());
		}
		return list;
	}

	public void setAvailableHotels(List<Hotel> accessibleHotels) {
		ArrayList<Ref<Hotel>> list = new ArrayList<Ref<Hotel>>();
		for (Hotel hotel : accessibleHotels) {
			list.add(Ref.create(hotel));
		}
		this.availableHotels = list;
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
	 * Entitás módosítása DTO adataival
	 * 
	 * @param dto
	 */
	public void updEntityWithDto(AppUserDto dto) {
		clearUniqueIndexes();

		super.updEntityWithDto(dto);

		if (dto.getCode() != null)
			setCode(dto.getCode());

		if (dto.getName() != null)
			setName(dto.getName());

		if (dto.getTitle() != null)
			setTitle(dto.getTitle());

		if (dto.getPicture() != null)
			setPicture(dto.getPicture());

		if (dto.getAdmin() != null)
			setAdmin(dto.getAdmin());

		if (dto.getEmailAddress() != null) {
			setEmailAddress(dto.getEmailAddress());
			if (!dto.getEmailAddress().equals(getEmailAddress()))
				addUniqueIndex(PROPERTY_EMAILADDRESS, dto.getEmailAddress());
		}

		if (dto.getEnabled() != null)
			setEnabled(dto.getEnabled());

		if (dto.getUsername() != null) {
			setUsername(dto.getUsername());
			if (!dto.getUsername().equals(getUsername()))
				addUniqueIndex(PROPERTY_USERNAME, dto.getUsername());
		}

		if (dto.getPassword() != null)
			setPassword(dto.getPassword());

		if (dto.getRoleDtos() != null)
			setRoles(Role.createList(dto.getRoleDtos()));

		if (dto.getAvailableHotelDtos() != null)
			setAvailableHotels(Hotel.createList(dto.getAvailableHotelDtos()));

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
		dto = entity.updDtoWithEntity(dto);
		return dto;
	}

	/**
	 * DTO módosítása az entitás adataival
	 * 
	 * @param dto
	 * @return
	 */
	public AppUserDto updDtoWithEntity(AppUserDto dto) {
		dto = (AppUserDto) super.updDtoWithEntity(dto);

		if (this.getCode() != null)
			dto.setCode(this.getCode());

		if (this.getName() != null)
			dto.setName(this.getName());

		if (this.getTitle() != null)
			dto.setTitle(this.getTitle());

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

		if (this.getAvailableHotels() != null) {
			dto.setAvailableHotelDtos(Hotel.createDtos(this.getAvailableHotels()));
		} else {
			dto.setAvailableHotelDtos(new ArrayList<HotelDto>());
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
