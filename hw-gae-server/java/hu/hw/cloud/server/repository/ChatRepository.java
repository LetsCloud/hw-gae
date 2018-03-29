/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.List;

import hu.hw.cloud.server.entity.chat.Chat;

/**
 * @author robi
 *
 */
public interface ChatRepository extends CrudRepository<Chat> {
	List<Chat> getByAccount(Object account);

}
