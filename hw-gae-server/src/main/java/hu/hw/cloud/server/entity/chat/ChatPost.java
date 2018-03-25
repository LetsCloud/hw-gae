/**
 * 
 */
package hu.hw.cloud.server.entity.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.shared.dto.chat.ChatPostDto;

/**
 * @author robi
 *
 */
public class ChatPost {

	private Date created;

	private Ref<AppUser> sender;

	private String message;

	public ChatPost() {
	}

	public ChatPost(AppUser sender, String message, Date created) {
		this();
		setSender(sender);
		this.message = message;
		this.created = created;
	}

	public ChatPost(ChatPostDto dto) {
		this();
		update(dto);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public AppUser getSender() {
		return sender.get();
	}

	public void setSender(AppUser sender) {
		this.sender = Ref.create(sender);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void update(ChatPostDto dto) {
		if (dto.getCreated() != null)
			setCreated(dto.getCreated());

		if (dto.getSender() != null)
			setSender(new AppUser(dto.getSender()));

		if (dto.getMessage() != null)
			setMessage(dto.getMessage());
	}

	public ChatPostDto updateDto(ChatPostDto dto) {
		if (this.getCreated() != null)
			dto.setCreated(this.getCreated());

		if (this.getSender() != null)
			dto.setSender(AppUser.createDto(this.getSender()));

		if (this.getMessage() != null)
			dto.setMessage(this.getMessage());

		return dto;
	}

	public static List<ChatPost> createList(List<ChatPostDto> dtos) {
		List<ChatPost> result = new ArrayList<ChatPost>();
		for (ChatPostDto dto : dtos) {
			result.add(new ChatPost(dto));
		}
		return result;
	}

	public static ChatPostDto createDto(ChatPost entity) {
		ChatPostDto dto = new ChatPostDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	public static List<ChatPostDto> createDtos(List<ChatPost> entities) {
		List<ChatPostDto> dtos = new ArrayList<ChatPostDto>();
		for (ChatPost entity : entities) {
			dtos.add(ChatPost.createDto(entity));
		}
		return dtos;
	}

}
