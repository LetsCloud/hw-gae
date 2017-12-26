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

import hu.hw.cloud.server.entity.cube.CubeBdgtCap;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.CubeBdgtCapRepo;
import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
public class CubeBdgtCapRepoImpl extends CrudRepositoryImpl<CubeBdgtCap> implements CubeBdgtCapRepo {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeBdgtCapRepoImpl.class.getName());

	protected CubeBdgtCapRepoImpl() {
		super(CubeBdgtCap.class);
	}

	@Override
	protected Object getParent(CubeBdgtCap entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		LOGGER.info("getAccountId->id=" + webSafeString);
		Key<CubeBdgtCap> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	public List<CubeBdgtCap> getFromToBsnsDate(Hotel hotel, Date fromDate, Date toDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		fromDate = DateUtils.addDays(fromDate, -1);
		toDate = DateUtils.addDays(toDate, 1);
		filters.put("bsnsDate >", fromDate);
		filters.put("bsnsDate <", toDate);
		return getChildrenByFilters(hotel, filters);
	}

	@Override
	public List<CubeBdgtCap> getFromToFcstDate(Hotel hotel, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

}
