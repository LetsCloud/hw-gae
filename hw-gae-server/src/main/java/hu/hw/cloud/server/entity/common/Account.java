/**
 * 
 */
package hu.hw.cloud.server.entity.common;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.entity.profile.PostalAddress;
import hu.hw.cloud.shared.cnst.PostalAddressLabel;
import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.dto.common.AccountDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.ExceptionType;

/**
 * @author CR
 *
 */
@Entity
public class Account extends BaseEntity {
	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(Account.class.getName());

	/**
	 * Név
	 */
	@Index
	private String name;

	/**
	 * Cím
	 */
	private PostalAddress postalAddress = new PostalAddress();

	/**
	 * Objectify miatt
	 */
	public Account() {
		// LOGGER.info("Account()");
	}

	/**
	 * Entitás létrehozása DTO-ból
	 * 
	 * @param dto
	 */
	public Account(AccountDto dto) {
		this();
		update(dto);
	}

	/**
	 * Entitás létrehozása RegisterDto-ból
	 * 
	 * @param registerDto
	 */
	public Account(RegisterDto registerDto) {
		this();
		PostalAddress postalAddress = new PostalAddress();
		postalAddress.setPrimary(true);
		postalAddress.setLabel(PostalAddressLabel.WORK);
		postalAddress.setStreet(registerDto.getStreet());
		postalAddress.setCity(registerDto.getCity());
		postalAddress.setPostcode(registerDto.getPostcode());
		postalAddress.setCountry(registerDto.getPostcode());
		this.postalAddress = postalAddress;
		this.name = registerDto.getAccountName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PostalAddress getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
	}

	/**
	 * Entitás módosítása DTO alapján
	 * 
	 * @param dto
	 */
	public void update(AccountDto dto) {
		super.update(dto);

		if (dto.getName() != null)
			this.setName(dto.getName());

		if (dto.getPostalAddressDto() != null)
			this.setPostalAddress(new PostalAddress(dto.getPostalAddressDto()));
	}

	/**
	 * DTO létrehozása entitás alapján
	 * 
	 * @param entity
	 * @return
	 */
	public static AccountDto createDto(Account entity) {
		AccountDto dto = new AccountDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * DTO módosítása entitás alapján
	 * 
	 * @param dto
	 * @return
	 */
	public AccountDto updateDto(AccountDto dto) {
		dto = (AccountDto) super.updateDto(dto);
		dto.setName(this.getName());
		dto.setPostalAddressDto(PostalAddress.createDto(this.getPostalAddress()));
		return dto;
	}

}
