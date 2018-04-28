/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.Guest;
import hu.hw.cloud.server.repository.GuestRepository;

/**
 * @author CR
 *
 */
//@Repository("guestRepository")
public class GuestRepositoryImpl extends CrudRepositoryImpl<Guest> implements GuestRepository {

	protected GuestRepositoryImpl() {
		super(Guest.class);
	}

	@Override
	protected Object getParent(Guest entity) {
		return entity.getAccount();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<Guest> key = getKey(webSafeString);
		return key.getParent().getString();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Account> key = Key.create(parentWebSafeKey);
		return key;
	}

}
