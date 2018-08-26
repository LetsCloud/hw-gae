/**
 * 
 */
package hu.hw.cloud.server.service;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.Role;

/**
 * @author CR
 *
 */
public interface RoleService extends CrudService<Role> {

	void createDefaultRoles(Account account);

}
