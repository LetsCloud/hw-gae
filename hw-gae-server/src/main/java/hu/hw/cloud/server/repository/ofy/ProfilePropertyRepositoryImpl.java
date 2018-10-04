/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.ProfileProperty;
import hu.hw.cloud.server.repository.ProfilePropertyRepository;

/**
 * @author robi
 *
 */
public class ProfilePropertyRepositoryImpl extends CrudRepositoryImpl<ProfileProperty>
		implements ProfilePropertyRepository {
	private static final Logger logger = LoggerFactory.getLogger(ProfilePropertyRepositoryImpl.class.getName());

	public ProfilePropertyRepositoryImpl() {
		super(ProfileProperty.class);
		logger.info("ProfilePropertyRepositoryImpl");
	}

	@Override
	protected Object getParent(ProfileProperty entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	public String getAccountId(String webSafeKey) {
		Key<ProfileProperty> key = getKey(webSafeKey);
		return key.getParent().getString();
	}

	@Override
	protected void loadUniqueIndexMap(ProfileProperty entiy) {

		if (entiy.getCode() != null) {
			entiy.addUniqueIndex(ProfileProperty.PROFILEPROPERTY_CODE, entiy.getCode());
		}
	}
}
