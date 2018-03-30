/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.List;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.hotel.Hotel;

/**
 * @author CR
 *
 */
public interface HotelRepository extends CrudRepository<Hotel> {

	Hotel findByCode(Account parent, String code);

	Hotel findByGeneratedId(Long parentGeneratedId, Long generatedId);

	Long getGeneratedId(String webSafeString);

	void delete(Hotel hotel);

	List<Key<Hotel>> getKeysByCode(Account parent, String code);
}
