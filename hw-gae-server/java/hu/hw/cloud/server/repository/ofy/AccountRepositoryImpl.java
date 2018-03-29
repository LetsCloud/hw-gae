/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.repository.AccountRepository;

/**
 * @author CR
 *
 */
public class AccountRepositoryImpl extends CrudRepositoryImpl<Account> implements AccountRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryImpl.class.getName());

	public AccountRepositoryImpl() {
		super(Account.class);
		LOGGER.info("AccountRepositoryImpl()");
	}

	@Override
	protected Object getParent(Account entity) {
		return null;
	}

	@Override
	public String getAccountId(String webSafeString) {
		return null;
	}

	@Override
	public String getWebSafeKeyById(Long id) {
		Key<Account> key = Key.create(Account.class, id);
		return key.getString();
	}
}
