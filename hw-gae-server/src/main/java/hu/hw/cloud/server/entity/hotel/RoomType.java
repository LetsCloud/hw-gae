/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.cnst.SalesType;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author CR
 *
 */
@Entity
public class RoomType extends HotelChild {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomType.class.getName());

	/**
	 * 
	 */
	private static final String PROPERTY_CODE = "code";

	/**
	 * 
	 * @param entityRefs
	 * @return
	 */
	public static List<String> createDtos(List<Ref<RoomType>> entityRefs) {
		List<String> dtos = new ArrayList<String>();
		for (Ref<RoomType> entityRef : entityRefs) {
			dtos.add(entityRef.getKey().getString());
		}
		return dtos;
	}

	/**
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<Ref<RoomType>> createRefList(List<String> ids) {
		List<Ref<RoomType>> entityRefs = new ArrayList<Ref<RoomType>>();
		for (String id : ids) {
			Key<RoomType> key = Key.create(id);
			entityRefs.add(Ref.create(key));
		}
		return entityRefs;
	}

	@Index
	private String code;

	private String name;

	private String description;

	private SalesType salesType;

	private List<Ref<RoomType>> components;

	private Boolean active;

	public RoomType() {
	}

	/**
	 * 
	 * @param dto
	 */
	public RoomType(RoomTypeDto dto) {
		super();
		update(dto);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SalesType getSalesType() {
		return salesType;
	}

	public void setSalesType(SalesType salesType) {
		this.salesType = salesType;
	}

	public List<Ref<RoomType>> getComponents() {
		return components;
	}

	public void setComponents(List<Ref<RoomType>> components) {
		this.components = components;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public void update(RoomTypeDto dto) {
//		LOGGER.info("update->dto=" + dto);

		clearUniqueIndexes();

		this.setHotel(new Hotel(dto.getHotelDto()));
		/*
		 * if (dto.getHotelWebSafeKey() != null)
		 * this.setHotelWebSafeKey(dto.getHotelWebSafeKey());
		 */
		if (dto.getCode() != null) {
			this.code = dto.getCode();
			addUniqueIndex(PROPERTY_CODE, dto.getCode());
		}

		if (dto.getName() != null)
			this.name = dto.getName();

		if (dto.getDescription() != null)
			this.description = dto.getDescription();

		if (dto.getSalesType() != null)
			this.salesType = dto.getSalesType();

		if (dto.getComponentIds() != null)
			this.components = createRefList(dto.getComponentIds());

		if (dto.getActive() != null)
			this.active = dto.getActive();

//		LOGGER.info("update-END");
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static RoomTypeDto createDto(RoomType entity) {
		RoomTypeDto dto = new RoomTypeDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	public RoomTypeDto updateDto(RoomTypeDto dto) {
		dto = (RoomTypeDto) super.updateDto(dto);
		if (this.getActive() != null)
			dto.setActive(this.getActive());

		if (this.getCode() != null)
			dto.setCode(this.getCode());

		if (this.getComponents() != null)
			dto.setComponentIds(createDtos(this.getComponents()));

		if (this.getDescription() != null)
			dto.setDescription(this.getDescription());

		if (this.getName() != null)
			dto.setName(this.getName());

		if (this.getSalesType() != null)
			dto.setSalesType(this.getSalesType());

		return dto;
	}

}
