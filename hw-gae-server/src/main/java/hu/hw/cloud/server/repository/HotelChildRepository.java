/**
 * 
 */
package hu.hw.cloud.server.repository;

import hu.hw.cloud.server.entity.BaseEntity;

/**
 * @author robi
 *
 */
public interface HotelChildRepository<T extends BaseEntity> extends CrudRepository<T> {
}
