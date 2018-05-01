/** 
 * 
 */
package hu.hw.cloud.server.service.impl;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Work;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.repository.CrudRepository;
import hu.hw.cloud.server.service.CrudService;
import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.exception.EntityVersionConflictException;

/**
 * @author CR
 *
 */
public abstract class CrudServiceImpl<T extends BaseEntity, D extends BaseDto, R extends CrudRepository<T>>
		implements CrudService<T, D> {
	private static final Logger logger = LoggerFactory.getLogger(CrudServiceImpl.class.getName());

	protected R repository;

	public CrudServiceImpl(R repository) {
		logger.info("CrudServiceImpl");
		this.repository = repository;
	}

	protected abstract T createEntity(D dto);

	protected abstract T updateEntity(T entity, D dto);

	protected abstract T updateEntity(T oldEntity, T newEntity);

	protected abstract List<Object> getParents(Long accountId);

	protected abstract List<Object> getParents(String accountWebSafeKey);

	@Override
	public T create(final D dto) throws Throwable {
		// A tranzakció végrehajtása folyamán jelentkező kivétel elfogása
		// céljából...
		try {
			// Objectify tranzakció indul
			T th = ofy().transact(new Work<T>() {
				public T run() {
					// A DTO-ból létrehozzuk a Hotel entitást
					T entity = createEntity(dto);
					try {
						// majd megpróbáljuk elmenteni
						entity = repository.save(entity);
						return entity;
					} catch (Throwable e) {
						// Kivétel esetén csomagoljuk azt egy futásidejű
						// kivételbe, majd kiváltjuk
						throw new RuntimeException(e);
					}
				}
			});
			return th;
		} catch (RuntimeException re) {
			// A csomagolt kivételt elcsípjük és továbbküldjük
			throw re.getCause();
		}
	}

	@Override
	public T read(String webSafeKey) throws Throwable {
		return repository.findByWebSafeKey(webSafeKey);
	}

	@Override
	public T update(final T entity) throws Throwable {
		try {
			T th = ofy().transact(new Work<T>() {
				public T run() {
					try {
						T entity2 = repository.save(entity);
						return entity2;
					} catch (Throwable e) {
						e.printStackTrace(System.out);
						throw new RuntimeException(e);
					}
				}
			});
			return th;
		} catch (RuntimeException re) {
			throw re.getCause();
		}
	}

	@Override
	public T update(final D dto) throws Throwable {
		logger.info("update");

		try {
			T th = ofy().transact(new Work<T>() {
				public T run() {
					T entity = repository.findByWebSafeKey(dto.getWebSafeKey());
					try {
						if (entity.getVersion() > dto.getVersion())
							throw new EntityVersionConflictException();
						entity = updateEntity(entity, dto);
						entity = repository.save(entity);
						return entity;
					} catch (Throwable e) {
						e.printStackTrace(System.out);
						throw new RuntimeException(e);
					}
				}
			});
			return th;
		} catch (RuntimeException re) {
			throw re.getCause();
		}
	}

	@Override
	public Boolean delete(String webSafeKey) {
		repository.delete(webSafeKey);
		return true;
	}

	@Override
	public List<T> getAll(Long accountId) {
		List<Object> parents = getParents(accountId);
		return getAllChildren(parents);
	}

	@Override
	public List<T> getAll(String accountWebSafeKey) {
		List<Object> parents = getParents(accountWebSafeKey);
		return getAllChildren(parents);
	}

	private List<T> getAllChildren(List<Object> parents) {
		List<T> entities = new ArrayList<T>();
		for (Object parent : parents)
			entities.addAll(repository.getAll(parent));
		return entities;
	}

	@Override
	public void deleteAll(Long accountId) {
		List<Object> parents = getParents(accountId);
		for (Object parent : parents)
			repository.deleteAll(parent);
	}

	@Override
	public List<T> getChildren(String parentWebSafeKey) {
		return repository.getChildren(parentWebSafeKey);
	}

	@Override
	public List<T> getChildrenByFilters(String parentWebSafeKey, Map<String, Object> filters) {
		if ((filters == null) || (filters.isEmpty()))
			return repository.getChildren(parentWebSafeKey);
		return repository.getChildrenByFilters(parentWebSafeKey, filters);
	}

}
