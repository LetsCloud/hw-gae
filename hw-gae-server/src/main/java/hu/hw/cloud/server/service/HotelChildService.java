/**
 * 
 */
package hu.hw.cloud.server.service;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface HotelChildService<T extends BaseEntity, D extends BaseDto> extends CrudService<T> {
}
