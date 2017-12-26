/**
 * 
 */
package hu.hw.cloud.server.repository;

import hu.hw.cloud.server.entity.common.Account;

/**
 * @author CR
 *
 */
public interface AccountRepository extends CrudRepository<Account> {

	String getWebSafeKeyById(Long id);

}
