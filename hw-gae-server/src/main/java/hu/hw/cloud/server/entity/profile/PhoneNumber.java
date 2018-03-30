/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.cnst.PhoneNumberLabel;
import hu.hw.cloud.shared.dto.pf.PhoneNumberDto;

/**
 * Telefonszám entitás.
 * 
 * @author CR
 *
 */
public class PhoneNumber {

	public static PhoneNumberDto createDto(PhoneNumber entity) {
		PhoneNumberDto dto = new PhoneNumberDto();
		dto.setLabel(entity.getLabel());
		dto.setPrimary(entity.getPrimary());
		dto.setText(entity.getText());
		return dto;
	}

	public static List<PhoneNumberDto> createDtos(List<PhoneNumber> enities) {
		List<PhoneNumberDto> dtos = new ArrayList<PhoneNumberDto>();
		for (PhoneNumber entity: enities) {
			dtos.add(createDto(entity));
		}
		return dtos;
	}

	/**
	 * Elsődleges telefonszám.
	 */
	private Boolean primary;

	/**
	 * Telefonszám típusa: Munkahelyi, Otthoni, Mobil ...
	 */
	private PhoneNumberLabel label;

	/**
	 * Emberi szemmel olvasható telefonszám
	 */
	private String text;

	public PhoneNumber() {
	}

	public PhoneNumber(Boolean primary, PhoneNumberLabel label, String text) {
		this.primary = primary;
		this.label = label;
		this.text = text;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public PhoneNumberLabel getLabel() {
		return label;
	}

	public void setLabel(PhoneNumberLabel label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}