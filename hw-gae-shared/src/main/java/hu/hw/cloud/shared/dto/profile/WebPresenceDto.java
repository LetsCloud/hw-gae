/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.cnst.WebPresenceType;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class WebPresenceDto implements Dto {

	/**
	 * Email cím típusa: Munkahelyi, Otthoni, Egyéb
	 */
	private WebPresenceType label;

	/**
	 * Email cím
	 */
	private String url;

	public WebPresenceDto() {}

	public WebPresenceDto(WebPresenceType label) {
		this.label = label;		
	}
	
	public WebPresenceType getLabel() {
		return label;
	}

	public void setLabel(WebPresenceType label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
