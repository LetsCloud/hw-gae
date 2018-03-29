/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.entity.hotel.Hotel;

/**
 * @author CR
 *
 */
public interface CubeRepo<T extends BaseEntity> {
	
	List<T> getFromToBsnsDate(Hotel hotel, Date from, Date to);
	
	List<T> getFromToFcstDate(Hotel hotel, Date from, Date to);
}
