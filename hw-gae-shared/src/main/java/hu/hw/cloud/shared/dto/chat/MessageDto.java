/**
 * 
 */
package hu.hw.cloud.shared.dto.chat;

import hu.hw.cloud.shared.dto.common.AccountChildDto;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class MessageDto extends AccountChildDto {
	
	private AppUserDto sender;

	private String content;

	public AppUserDto getSender() {
		return sender;
	}

	public void setSender(AppUserDto sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
