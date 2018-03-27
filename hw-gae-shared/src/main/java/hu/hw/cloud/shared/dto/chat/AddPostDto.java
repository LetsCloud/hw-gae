/**
 * 
 */
package hu.hw.cloud.shared.dto.chat;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class AddPostDto extends ChatPostDto {
	
	private String chatWebSafeKey;

	public String getChatWebSafeKey() {
		return chatWebSafeKey;
	}

	public void setChatWebSafeKey(String chatWebSafeKey) {
		this.chatWebSafeKey = chatWebSafeKey;
	}

}
