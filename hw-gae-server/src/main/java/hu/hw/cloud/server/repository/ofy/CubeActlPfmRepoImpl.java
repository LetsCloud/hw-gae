/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.cube.CubeActlPfm;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.CubeActlPfmRepo;
import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
public class CubeActlPfmRepoImpl extends CrudRepositoryImpl<CubeActlPfm> implements CubeActlPfmRepo {

	protected CubeActlPfmRepoImpl() {
		super(CubeActlPfm.class);
	}

	@Override
	protected Object getParent(CubeActlPfm entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		Key<CubeActlPfm> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	public List<CubeActlPfm> getFromToBsnsDate(Hotel hotel, Date fromDate, Date toDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		fromDate = DateUtils.addDays(fromDate, -1);
		toDate = DateUtils.addDays(toDate, 1);
		filters.put("bsnsDate >", fromDate);
		filters.put("bsnsDate <", toDate);
		return getChildrenByFilters(hotel, filters);
	}

	@Override
	public List<CubeActlPfm> getFromToFcstDate(Hotel hotel, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
