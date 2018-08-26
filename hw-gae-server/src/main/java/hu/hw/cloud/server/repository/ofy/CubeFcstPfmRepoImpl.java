/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.cube.CubeFcstPfm;
import hu.hw.cloud.server.entity.cube.CubeBase;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.CubeFcstPfmRepo;
import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
@Repository("rmsMeasuresForecastRepo")
public class CubeFcstPfmRepoImpl extends CrudRepositoryImpl<CubeFcstPfm> implements CubeFcstPfmRepo {
	// private static final Logger LOGGER = LoggerFactory
	// .getLogger(RmsCapacityForecastRepoImpl.class.getName());

	protected CubeFcstPfmRepoImpl() {
		super(CubeFcstPfm.class);
	}

	@Override
	protected Object getParent(CubeFcstPfm entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		// LOGGER.info("getAccountId->id=" + webSafeString);
		Key<CubeFcstPfm> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	public List<CubeFcstPfm> getPickupForStayDate(String hotelWebSafeKey, Date stayDate) {
		Key<Hotel> hotelKey = Key.create(hotelWebSafeKey);
		// LOGGER.info("getPickupForStayDate->hotelKey=" + hotelKey);
		return getChildrenByProperty(hotelKey, CubeBase.FIELD_STAYDATE, stayDate);
		// return this.getChildren(hotelKey);
	}

	@Override
	public List<CubeFcstPfm> getFromToBsnsDate(Hotel hotel, Date fromDate, Date toDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		fromDate = DateUtils.addDays(fromDate, -1);
		toDate = DateUtils.addDays(toDate, 1);
		filters.put("bsnsDate >", fromDate);
		filters.put("bsnsDate <", toDate);
		return getChildrenByFilters(hotel, filters);
	}

	@Override
	public List<CubeFcstPfm> getFromToFcstDate(Hotel hotel, Date fromDate, Date toDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		fromDate = DateUtils.addDays(fromDate, -1);
		toDate = DateUtils.addDays(toDate, 1);
		filters.put("fcstDate >", fromDate);
		filters.put("fcstDate <", toDate);
		return getChildrenByFilters(hotel, filters);
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void loadUniqueIndexMap(CubeFcstPfm entiy) {
		// TODO Auto-generated method stub
		
	}

}
