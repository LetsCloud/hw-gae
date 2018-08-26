/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.cube.CubeBdgtPfm;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.CubeBdgtPfmRepo;
import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
public class CubeBdgtPfmRepoImpl extends CrudRepositoryImpl<CubeBdgtPfm> implements CubeBdgtPfmRepo {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeBdgtPfmRepoImpl.class.getName());

	protected CubeBdgtPfmRepoImpl() {
		super(CubeBdgtPfm.class);
	}

	@Override
	protected Object getParent(CubeBdgtPfm entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<CubeBdgtPfm> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	public List<CubeBdgtPfm> getFromToBsnsDate(Hotel hotel, Date fromDate, Date toDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		fromDate = DateUtils.addDays(fromDate, -1);
		toDate = DateUtils.addDays(toDate, 1);
		filters.put("bsnsDate >", fromDate);
		filters.put("bsnsDate <", toDate);
		return getChildrenByFilters(hotel, filters);
	}

	@Override
	public List<CubeBdgtPfm> getFromToFcstDate(Hotel hotel, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void loadUniqueIndexMap(CubeBdgtPfm entiy) {
		// TODO Auto-generated method stub
		
	}

}
