/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.UserGroup;
import hu.hw.cloud.server.repository.UserGroupRepository;

/**
 * @author robi
 *
 */
public class UserGroupRepositoryImpl extends CrudRepositoryImpl<UserGroup> implements UserGroupRepository {

	/**
	 * 
	 */
	private static final String PROPERTY_NAME = "name";

	protected UserGroupRepositoryImpl() {
		super(UserGroup.class);
	}

	@Override
	protected Object getParent(UserGroup entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<UserGroup> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	public List<UserGroup> getByAccount(Object account) {
		return getChildren(account);
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(UserGroup entiy) {
		if ((entiy.getName() != null) && (!entiy.getName().isEmpty()))
			entiy.addUniqueIndex(PROPERTY_NAME, entiy.getName());
	}

}
