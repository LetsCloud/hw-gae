/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.Date;
import java.util.List;

import hu.hw.cloud.server.entity.hk.HkAssignment;
import hu.hw.cloud.server.entity.hotel.Hotel;

/**
 * @author CR
 *
 */
public interface HkAssignmentRepo extends CrudRepository<HkAssignment> {
	
	HkAssignment save(HkAssignment hkAssignment);

	List<HkAssignment> getHkAssignmentsByHotel(Hotel hotel);
	
	List<HkAssignment> getByDateAndHotel(String hotelWebSafeKey, Date date);

}
