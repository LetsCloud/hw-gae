/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import hu.hw.cloud.shared.cnst.WebPresenceType;

/**
 * @author CR
 *
 */
public class WebPresence {

	/**
	 * Email cím típusa: Munkahelyi, Otthoni, Egyéb
	 */
	private WebPresenceType label;

	/**
	 * Email cím
	 */
	private String url;

	public WebPresence() {}
	
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