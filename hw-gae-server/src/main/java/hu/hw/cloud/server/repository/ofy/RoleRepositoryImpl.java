/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.logging.Logger;

//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.Role;
import hu.hw.cloud.server.repository.RoleRepository;

/**
 * @author CR
 *
 */
//@Repository("roleRepository")
public class RoleRepositoryImpl extends CrudRepositoryImpl<Role> implements RoleRepository {
	private static final Logger LOGGER = Logger.getLogger(RoleRepositoryImpl.class.getName());

	private static final String PROPERTY_CODE = "code";

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

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(Role entiy) {
		if ((entiy.getCode() != null) && (!entiy.getCode().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_CODE, entiy.getCode());
	}

}
