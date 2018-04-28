/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import com.googlecode.objectify.Key;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.HotelChild;
import hu.hw.cloud.server.repository.HotelChildRepository;

/**
 * @author robi
 *
 */
public abstract class HotelChildRepositoryImpl<T extends HotelChild> extends CrudRepositoryImpl<T>
		implements HotelChildRepository<T> {

	protected HotelChildRepositoryImpl(Class<T> clazz) {
		super(clazz);
	}

	@Override
	protected Object getParent(T entity) {
		return entity.getHotel();
	}

	@Override
	protected Object getParentKey(String parentWebSafeKey) {
		Key<Hotel> key = Key.create(parentWebSafeKey);
		return key;
	}
}
