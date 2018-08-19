/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.dto.hotel.VipLevelDto;

/**
 * @author robi
 *
 */
@Entity
public class VipLevel extends HotelChild {
//	private static final Logger logger = LoggerFactory.getLogger(RoomType.class.getName());

	/**
	 * 
	 */
	private static final String VIP_CODE = "code";

	@Index
	private String code;

	private String description;

	private Integer sequence;

	private String color;

	private Boolean active;
	
	public VipLevel() {
//		logger.info("RoomType()");
	}

	/**
	 * 
	 * @param dto
	 */
	public VipLevel(VipLevelDto dto) {
		this();
		updEntityWithDto(dto);
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static String getVipCode() {
		return VIP_CODE;
	}

	/**
	 * 
	 * @param dto
	 */
	public void updEntityWithDto(VipLevelDto dto) {
		clearUniqueIndexes();

		super.updEntityWithDto(dto);

		if (dto.getCode() != null) {
			this.code = dto.getCode();
			addUniqueIndex(VIP_CODE, dto.getCode());
		}

		if (dto.getDescription() != null)
			this.description = dto.getDescription();

		if (dto.getActive() != null)
			this.active = dto.getActive();
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public VipLevelDto updDtoWithEntity(VipLevelDto dto) {
		dto = (VipLevelDto) super.updDtoWithEntity(dto);
		
		if (this.getActive() != null)
			dto.setActive(this.getActive());

		if (this.getCode() != null)
			dto.setCode(this.getCode());

		if (this.getDescription() != null)
			dto.setDescription(this.getDescription());

		return dto;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static VipLevelDto createDto(VipLevel entity) {
		VipLevelDto dto = new VipLevelDto();
		dto = entity.updDtoWithEntity(dto);
		return dto;
	}

	/**
	 * 
	 * @param entityRefs
	 * @return
	 */
	public static List<String> createDtos(List<Ref<VipLevel>> entityRefs) {
		List<String> dtos = new ArrayList<String>();
		for (Ref<VipLevel> entityRef : entityRefs) {
			dtos.add(entityRef.getKey().getString());
		}
		return dtos;
	}

	/**
	 * 
	 * @param ids
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
}
