/**
 * 
 */
package hu.hw.cloud.server.controller.v1;

import org.modelmapper.ModelMapper;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.service.HotelChildService;
import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class HotelChildController<T extends BaseEntity, D extends BaseDto> extends CrudController<T, D> {

	public HotelChildController(Class<T> clazz, HotelChildService<T> service, ModelMapper modelMapper) {
		super(clazz, service, modelMapper);
	}

}
