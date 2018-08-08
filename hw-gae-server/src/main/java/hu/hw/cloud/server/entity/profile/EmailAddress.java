/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.cnst.EmailAddressLabel;
import hu.hw.cloud.shared.dto.profile.EmailAddressDto;

/**
 * Email cím entitás.
 * 
 * @author CR
 *
 */
public class EmailAddress {

	public static EmailAddressDto createDto(EmailAddress entity) {
		EmailAddressDto dto = new EmailAddressDto();
		dto.setAddress(entity.getAddress());
		dto.setLabel(entity.getLabel());
		dto.setPrimary(entity.getPrimary());
		return dto;
	}

	public static List<EmailAddressDto> createDtos(List<EmailAddress> entities) {
		List<EmailAddressDto> dtos = new ArrayList<EmailAddressDto>();
		for (EmailAddress entity : entities) {
			dtos.add(EmailAddress.createDto(entity));
		}
		return dtos;
	}

	/**
	 * Elsődleges email cím.
	 */
	private Boolean primary;

	/**
	 * Email cím típusa: Munkahelyi, Otthoni, Egyéb
	 */
	private EmailAddressLabel label;

	/**
	 * Email cím
	 */
	private String address;

	public EmailAddress() {
	}

	public EmailAddress(Boolean primary, EmailAddressLabel label, String address) {
		this.primary = primary;
		this.label = label;
		this.address = address;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public EmailAddressLabel getLabel() {
		return label;
	}

	public void setLabel(EmailAddressLabel label) {
		this.label = label;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}