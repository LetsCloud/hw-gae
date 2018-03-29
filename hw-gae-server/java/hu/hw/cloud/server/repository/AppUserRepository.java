/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.List;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;

/**
 * @author CR
 *
 */
public interface AppUserRepository extends CrudRepository<AppUser> {

	AppUser findByUsername(Account account, String username);

	AppUser findByEmailAdddress(String emailAddress);

	List<AppUser> getByAccount(Object account);

	AppUser findByToken(String token);
}
