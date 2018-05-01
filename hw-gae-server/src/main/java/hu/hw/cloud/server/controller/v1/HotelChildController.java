/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.service.HotelChildService;
import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class HotelChildController<T extends BaseEntity, D extends BaseDto> extends CrudController<T, D> {
	
	public HotelChildController(HotelChildService<T, D> service) {
		super(service);
	}

}
