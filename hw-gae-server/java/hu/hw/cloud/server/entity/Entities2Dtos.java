/**
 * 
 */
package hu.hw.cloud.server.entity;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author CR
 *
 */
public abstract class Entities2Dtos<E extends BaseEntity, D extends BaseDto> {
	
	@SuppressWarnings("unchecked")
	public List<D> convert(List<E> entities) {
		List<D> dtos = new ArrayList<D>();
		for(E entity: entities) {
			D dto = createDto();
			dtos.add((D) entity.updateDto(dto));
		}
		return dtos;
	}
	
	protected abstract D createDto();

}
