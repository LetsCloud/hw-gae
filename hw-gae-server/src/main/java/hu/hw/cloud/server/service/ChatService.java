/**
 * 
 */
package hu.hw.cloud.server.service;

import hu.hw.cloud.server.entity.chat.Chat;
import hu.hw.cloud.server.entity.chat.ChatPost;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author robi
 *
 */
public interface ChatService extends CrudService<Chat> {
	
	Chat addPost(String chatKey, ChatPost post) throws EntityValidationException, UniqueIndexConflictException;

}
