/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.chat.Chat;
import hu.hw.cloud.server.repository.ChatRepository;

/**
 * @author robi
 *
 */
public class ChatRepositoryImpl extends CrudRepositoryImpl<Chat> implements ChatRepository {

	protected ChatRepositoryImpl() {
		super(Chat.class);
	}

	@Override
	protected Object getParent(Chat entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<Chat> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	public List<Chat> getByAccount(Object account) {
		return getChildren(account);
	}

}
