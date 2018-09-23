/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.ProfileLink;
import hu.hw.cloud.server.repository.ProfileLinkRepository;

/**
 * @author robi
 *
 */
public class ProfileLinkRepositoryImpl extends CrudRepositoryImpl<ProfileLink> implements ProfileLinkRepository {
	private static final Logger logger = LoggerFactory.getLogger(ProfileLinkRepositoryImpl.class.getName());

	public ProfileLinkRepositoryImpl() {
		super(ProfileLink.class);
		logger.info("ProfileGroupRepositoryImpl");
	}

	@Override
	protected Object getParent(ProfileLink entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	public String getAccountId(String webSafeKey) {
		Key<ProfileLink> key = getKey(webSafeKey);
		return key.getParent().getString();
	}

	@Override
	protected void loadUniqueIndexMap(ProfileLink entiy) {
	}
}
