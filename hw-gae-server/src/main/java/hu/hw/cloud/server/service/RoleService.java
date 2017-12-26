/**
 * 
 */
package hu.hw.cloud.server.service;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.Role;
import hu.hw.cloud.shared.dto.common.RoleDto;

/**
 * @author CR
 *
 */
public interface RoleService extends CrudService<Role, RoleDto> {

	void createDefaultRoles(Account account);

}
