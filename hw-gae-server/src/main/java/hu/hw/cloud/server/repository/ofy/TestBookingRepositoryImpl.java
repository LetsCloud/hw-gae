/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.TestBooking;
import hu.hw.cloud.server.repository.TestBookingRepository;

/**
 * @author CR
 *
 */
//@Repository("testBookingRepository")
public class TestBookingRepositoryImpl extends CrudRepositoryImpl<TestBooking> implements TestBookingRepository {

	protected TestBookingRepositoryImpl() {
		super(TestBooking.class);
	}

	@Override
	protected Object getParent(TestBooking entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<TestBooking> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

}
