/**
 * 
 */
package hu.hw.cloud.server.repository;

import java.util.List;
import java.util.Map;

import hu.hw.cloud.server.entity.BaseEntity;

/**
 * @author robi
 *
 */
public interface HotelChildRepository<T extends BaseEntity> extends CrudRepository<T> {
}
