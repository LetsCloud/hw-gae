package hu.hw.cloud.server.repository.ofy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.cube.CubeFcstCap;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.CubeFcstCapRepo;
import hu.hw.cloud.server.utils.DateUtils;

//@Repository("rmsCapacityForecastRepo")
public class CubeFcstCapRepoImpl extends CrudRepositoryImpl<CubeFcstCap> implements CubeFcstCapRepo {
	private static final Logger LOGGER = LoggerFactory.getLogger(CubeFcstCapRepoImpl.class.getName());

	protected CubeFcstCapRepoImpl() {
		super(CubeFcstCap.class);
	}

	@Override
	protected Object getParent(CubeFcstCap entity) {
		return entity.getHotel();
	}

	@Override
	public String getAccountId(String webSafeString) {
		LOGGER.info("getAccountId->id=" + webSafeString);
		Key<CubeFcstCap> key = getKey(webSafeString);
		return key.getParent().getParent().getString();
	}

	@Override
	public List<CubeFcstCap> getFromToBsnsDate(Hotel hotel, Date fromDate, Date toDate) {
		Map<String, Object> filters = new HashMap<String, Object>();
		fromDate = DateUtils.addDays(fromDate, -1);
		toDate = DateUtils.addDays(toDate, 1);
		filters.put("bsnsDate >", fromDate);
		filters.put("bsnsDate <", toDate);
		return getChildrenByFilters(hotel, filters);
	}

	@Override
	public List<CubeFcstCap> getFromToFcstDate(Hotel hotel, Date fromDate, Date toDate) {
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
	protected void loadUniqueIndexMap(CubeFcstCap entiy) {
		// TODO Auto-generated method stub
		
	}

}
