/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.repository.CrudRepository;
import hu.hw.cloud.server.service.DataBuilderService;

/**
 * @author CR
 *
 */
public class BaseData<T extends BaseEntity> {

	protected DataBuilderService dbs;

	protected CrudRepository<T> repo;

	public BaseData(DataBuilderService dbs, CrudRepository<T> repo) {
		this.dbs = dbs;
		this.repo = repo;
	}

	protected T save(T entity) {
		if (repo != null) {
			try {
				entity = repo.save(entity);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else {
			Key<T> key = ObjectifyService.ofy().save().entity(entity).now();
			entity = ObjectifyService.ofy().load().key(key).now();
		}
		return entity;
	}

}
