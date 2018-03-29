/**
 * 
 */
package hu.hw.cloud.server.entity.hk;

import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.hotel.HotelChild;
import hu.hw.cloud.shared.dto.hk.CleanTypeDto;

/**
 * @author CR
 *
 */
@Entity
public class CleanType extends HotelChild {

	private String code;

	private String description;

	private Integer time;

	public CleanType() {
	}

	public CleanType(CleanTypeDto dto) {
		super(dto);
		this.update(dto);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public void update(CleanType dto) {
		this.setCode(dto.getCode());
		this.setDescription(dto.getDescription());
		this.setTime(dto.getTime());
	}

	/**
	 * Létrehozza az entitás DTO megfelelőjét
	 * 
	 * @param entity
	 * @return
	 */
	public static CleanTypeDto createDto(CleanType entity) {
		return entity.updateDto(new CleanTypeDto());
	}

	/**
	 * Az átadott DTO megmódosítása saját adatokkal
	 * 
	 * @param dto
	 * @return
	 */
	public CleanTypeDto updateDto(CleanTypeDto dto) {
		dto = (CleanTypeDto) super.updateDto(dto);
		dto.setCode(this.getCode());
		dto.setDescription(this.getDescription());
		dto.setTime(this.getTime());
		return dto;
	}
}
