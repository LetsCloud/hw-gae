/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.ProfileGroup;
import hu.hw.cloud.server.repository.ProfileGroupRepository;

/**
 * @author robi
 *
 */
public class ProfileGroupRepositoryImpl extends CrudRepositoryImpl<ProfileGroup> implements ProfileGroupRepository {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroupRepositoryImpl.class.getName());

	public ProfileGroupRepositoryImpl() {
		super(ProfileGroup.class);
		logger.info("ProfileGroupRepositoryImpl");
	}

	@Override
	protected Object getParent(ProfileGroup entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	public String getAccountId(String webSafeKey) {
		Key<ProfileGroup> key = getKey(webSafeKey);
		return key.getParent().getString();
	}
}
