/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.Relationship;
import hu.hw.cloud.server.repository.RelationshipRepository;

/**
 * @author robi
 *
 */
public class RelationshipRepositoryImpl extends CrudRepositoryImpl<Relationship> implements RelationshipRepository {
	private static final Logger logger = LoggerFactory.getLogger(RelationshipRepositoryImpl.class.getName());

	public RelationshipRepositoryImpl() {
		super(Relationship.class);
		logger.info("RelationshipRepositoryImpl");
	}

	@Override
	protected Object getParent(Relationship entity) {
		return entity.getAccount();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	public String getAccountId(String webSafeKey) {
		Key<Relationship> key = getKey(webSafeKey);
		return key.getParent().getString();
	}

	@Override
	protected void loadUniqueIndexMap(Relationship entiy) {
	}
}
