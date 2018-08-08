/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.cnst.UrlAddressLabel;
import hu.hw.cloud.shared.dto.profile.UrlAddressDto;

/**
 * @author CR
 *
 */
public class UrlAddress {

	/**
	 * Email cím típusa: Munkahelyi, Otthoni, Egyéb
	 */
	private UrlAddressLabel label;

	/**
	 * Email cím
	 */
	private String address;

	public UrlAddressLabel getLabel() {
		return label;
	}

	public void setLabel(UrlAddressLabel label) {
		this.label = label;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UrlAddressDto createDto() {
		UrlAddressDto dto = new UrlAddressDto();
		dto = updateDto(dto);
		return dto;
	}

	public UrlAddressDto updateDto(UrlAddressDto dto) {
		dto.setAddress(getAddress());
		dto.setLabel(getLabel());
		return dto;
	}

	public static List<UrlAddressDto> createDtos(List<UrlAddress> enities) {
		List<UrlAddressDto> dtos = new ArrayList<UrlAddressDto>();
		for (UrlAddress entity : enities) {
			dtos.add(entity.createDto());
		}
		return dtos;
	}

}