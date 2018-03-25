/**
 * 
 */
package hu.hw.cloud.server.service;

import hu.hw.cloud.server.entity.chat.Chat;
import hu.hw.cloud.shared.dto.chat.AddPostDto;
import hu.hw.cloud.shared.dto.chat.ChatDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public interface ChatService extends CrudService<Chat, ChatDto> {
	
	Chat addPost(AddPostDto dto) throws EntityValidationException, UniqueIndexConflictException;

}
