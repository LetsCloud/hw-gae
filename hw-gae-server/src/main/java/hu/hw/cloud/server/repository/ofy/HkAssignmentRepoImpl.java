/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.hk.HkAssignment;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.HkAssignmentRepo;

/**
 * @author CR
 *
 */
// @Repository("hkAssignmentRepo")
public class HkAssignmentRepoImpl extends CrudRepositoryImpl<HkAssignment> implements HkAssignmentRepo {
	private static final Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class.getName());

	protected HkAssignmentRepoImpl() {
		super(HkAssignment.class);
	}

	@Override
	public HkAssignment save(HkAssignment hkAssignment) {
		logger.info("Saving: ");
		return putAndLoad(hkAssignment);
	}

	@Override
	protected Object getParent(HkAssignment entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccountId(String webSafeString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HkAssignment findByWebSafeKey(String webSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HkAssignment findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HkAssignment> getHkAssignmentsByHotel(Hotel hotel) {
		return getChildren(hotel);
	}

	@Override
	public List<HkAssignment> getByDateAndHotel(String hotelWebSafeKey, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Hotel> key = Key.create(parentWebSafeKey);
		return key;
	}

	@Override
	protected void loadUniqueIndexMap(HkAssignment entiy) {
		// TODO Auto-generated method stub
		
	}
}
