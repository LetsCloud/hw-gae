/**
 * 
 */
package hu.hw.cloud.shared.dto.chat;

import java.util.List;

import hu.hw.cloud.shared.dto.common.AccountChildDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class ChatDto extends AccountChildDto {

	private UserGroupDto receiver;

	private List<MessageDto> replies;

	public UserGroupDto getReceiver() {
		return receiver;
	}

	public void setReceiver(UserGroupDto receiver) {
		this.receiver = receiver;
	}

	public List<MessageDto> getReplies() {
		return replies;
	}

	public void setReplies(List<MessageDto> replies) {
		this.replies = replies;
	}

}
