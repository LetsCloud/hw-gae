/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.List;
import java.util.logging.Logger;

//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.repository.AppUserRepository;

/**
 * @author CR
 *
 */
//@Repository("appUserRepository")
public class AppUserRepositoryImpl extends CrudRepositoryImpl<AppUser> implements AppUserRepository {
	private static final Logger LOGGER = Logger.getLogger(AppUserRepositoryImpl.class.getName());

	private static final String USERNAME_PROPERTY = "username";
	private static final String EMAIL_PROPERTY = "emailAddress";

	public AppUserRepositoryImpl() {
		super(AppUser.class);
	}

	@Override
	public AppUser findByUsername(Account account, String username) {
		AppUser user = getChildByProperty(account, USERNAME_PROPERTY, username);
//		user.setAccount(account);
//		user.getAccount();
		return user;
	}

	@Override
	public AppUser findByEmailAdddress(String emailAddress) {
		return getFirstByProperty(EMAIL_PROPERTY, emailAddress);
	}

	@Override
	protected Object getParent(AppUser entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		LOGGER.info("getAccountId->id=" + webSafeString);
		Key<AppUser> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	public List<AppUser> getByAccount(Object account) {
		return getChildren(account);
	}

	@Override
	public AppUser findByToken(String token) {
		return getFirstByProperty("verificationTokens.token", token);
	}
}
