/**
 * 
 */
package hu.hw.cloud.server.entity.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.shared.dto.chat.ChatDto;

/**
 * @author robi
 *
 */
@Entity
public class Chat extends AccountChild {

	private Date created;

	private Date updated;

	private Ref<AppUser> sender;

	private List<Ref<AppUser>> receivers = new ArrayList<Ref<AppUser>>();

	private String message;

	private Date closed;

	private List<ChatPost> posts = new ArrayList<ChatPost>();
	
	private String url;

	public Chat() {

	}

	public Chat(ChatDto dto) {
		this();
		updateEntity(dto);
	}

	public AppUser getSender() {
		return sender.get();
	}

	public void setSender(AppUser sender) {
		this.sender = Ref.create(sender);
	}

	public List<AppUser> getReceivers() {
		return AppUser.ref2EntityList(receivers);
	}

	public void setReceivers(List<AppUser> receivers) {
		this.receivers = AppUser.entity2RefList(receivers);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ChatPost> getPosts() {
		return posts;
	}

	public void setPosts(List<ChatPost> posts) {
		this.posts = posts;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		this.closed = closed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Entitás módosítása DTO alapján
	 * 
	 * @param dto
	 */
	public void updateEntity(ChatDto dto) {
		clearUniqueIndexes();

		super.update(dto);

		if (dto.getCreated() != null)
			setCreated(dto.getCreated());

		if (dto.getUpdated() != null)
			setUpdated(dto.getUpdated());

		if (dto.getSender() != null)
			setSender(new AppUser(dto.getSender()));

		if (dto.getReceivers() != null)
			setReceivers(AppUser.createList(dto.getReceivers()));

		if (dto.getMessage() != null)
			setMessage(dto.getMessage());

		if (dto.getClosed() != null)
			setClosed(dto.getClosed());

		if (dto.getPosts() != null)
			setPosts(ChatPost.createList(dto.getPosts()));

		if (dto.getUrl() != null)
			setUrl(dto.getUrl());
	}

	public static ChatDto createDto(Chat entity) {
		ChatDto dto = new ChatDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	public ChatDto updateDto(ChatDto dto) {
		dto = (ChatDto) super.updateDto(dto);

		if (this.getCreated() != null)
			dto.setCreated(this.getCreated());

		if (this.getUpdated() != null)
			dto.setUpdated(this.getUpdated());

		if (this.getSender() != null)
			dto.setSender(AppUser.createDto(this.getSender()));

		if (this.getReceivers() != null)
			dto.setReceivers(AppUser.createDtos(this.getReceivers()));

		if (this.getMessage() != null)
			dto.setMessage(this.getMessage());

		if (this.getClosed() != null)
			dto.setClosed(this.getClosed());

		if (this.getPosts() != null)
			dto.setPosts(ChatPost.createDtos(this.getPosts()));

		if (this.getUrl() != null)
			dto.setUrl(this.getUrl());

		return dto;
	}

}
