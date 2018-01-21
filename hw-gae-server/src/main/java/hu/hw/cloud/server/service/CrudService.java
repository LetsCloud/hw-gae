/**
 * 
 */
package hu.hw.cloud.server.service;

import java.util.List;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author CR
 *
 */
public interface CrudService<T extends BaseEntity, D extends BaseDto> {

	T create(D dto) throws Throwable;

	T read(String id) throws Throwable;

	T update(T entity) throws Throwable;

	T update(D dto) throws Throwable;

	Boolean delete(String id) throws Throwable;

	List<T> getAll(Long accountId);

	List<T> getAll(String accountWebSafeKey);

	void deleteAll(Long accountId);
}
