/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.cnst.UrlAddressLabel;
import hu.hw.cloud.shared.dto.profile.WebPresenceDto;

/**
 * @author CR
 *
 */
public class WebPresence {

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

	public WebPresenceDto createDto() {
		WebPresenceDto dto = new WebPresenceDto();
		dto = updateDto(dto);
		return dto;
	}

	public WebPresenceDto updateDto(WebPresenceDto dto) {
		dto.setAddress(getAddress());
		dto.setLabel(getLabel());
		return dto;
	}

	public static List<WebPresenceDto> createDtos(List<WebPresence> enities) {
		List<WebPresenceDto> dtos = new ArrayList<WebPresenceDto>();
		for (WebPresence entity : enities) {
			dtos.add(entity.createDto());
		}
		return dtos;
	}

}