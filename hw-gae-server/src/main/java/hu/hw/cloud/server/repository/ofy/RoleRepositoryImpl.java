/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.logging.Logger;

//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Role;
import hu.hw.cloud.server.repository.RoleRepository;

/**
 * @author CR
 *
 */
//@Repository("roleRepository")
public class RoleRepositoryImpl extends CrudRepositoryImpl<Role> implements RoleRepository {
	private static final Logger LOGGER = Logger.getLogger(RoleRepositoryImpl.class.getName());

	protected RoleRepositoryImpl() {
		super(Role.class);
	}

	@Override
	protected Object getParent(Role entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		LOGGER.info("getAccountId->id=" + webSafeString);
		Key<Role> key = getKey(webSafeString);
		return key.getParent().getString();
	}

}
