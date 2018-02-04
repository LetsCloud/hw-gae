/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.List;

import hu.hw.cloud.server.entity.common.UserGroup;

/**
 * @author robi
 *
 */
public interface UserGroupRepository extends CrudRepository<UserGroup> {

	List<UserGroup> getByAccount(Object account);

}
