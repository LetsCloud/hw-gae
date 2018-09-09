/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.List;
import java.util.Map;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public interface CrudRepository<T extends BaseEntity> {

	String getAccountId(String id);

	/**
	 * Az entitás mentését végző metódus definiciója.
	 * 
	 * @param entity A mnetésre váró entitás.
	 * @return Az elmentett entitás.
	 * @throws EntityValidationException    Validációs hiba esetén dobandó kivétel.
	 * @throws UniqueIndexConflictException Egyedi kulcs ütközés esetén dobandó
	 *                                      kivétel.
	 */
	T save(T entity) throws EntityValidationException, UniqueIndexConflictException;

	T findByWebSafeKey(String webSafeKey);

	T findById(Long id);

	void delete(String webSafeString);

	List<T> getAll(Object parent);

	void deleteAll(Object parent);

	List<T> getChildren(String parentWebSafeKey);

	List<T> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters);
}
