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

import hu.hw.cloud.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
@Entity
public class MarketGroup extends HotelChild {
//	private static final Logger logger = LoggerFactory.getLogger(RoomType.class.getName());

	/**
	 * 
	 */
	private static final String MARKET_GROUP_CODE = "code";

	@Index
	private String code;

	private String description;

	private Boolean active;
	
	public MarketGroup() {
//		logger.info("RoomType()");
	}

	/**
	 * 
	 * @param dto
	 */
	public MarketGroup(MarketGroupDto dto) {
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

	/**
	 * 
	 * @param dto
	 */
	public void updEntityWithDto(MarketGroupDto dto) {
		clearUniqueIndexes();

		super.updEntityWithDto(dto);

		if (dto.getCode() != null) {
			this.code = dto.getCode();
			addUniqueIndex(MARKET_GROUP_CODE, dto.getCode());
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
	public MarketGroupDto updDtoWithEntity(MarketGroupDto dto) {
		dto = (MarketGroupDto) super.updDtoWithEntity(dto);
		
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
	public static MarketGroupDto createDto(MarketGroup entity) {
		MarketGroupDto dto = new MarketGroupDto();
		dto = entity.updDtoWithEntity(dto);
		return dto;
	}

	/**
	 * 
	 * @param entityRefs
	 * @return
	 */
	public static List<String> createDtos(List<Ref<MarketGroup>> entityRefs) {
		List<String> dtos = new ArrayList<String>();
		for (Ref<MarketGroup> entityRef : entityRefs) {
			dtos.add(entityRef.getKey().getString());
		}
		return dtos;
	}

	public static List<Ref<MarketGroup>> createRefList(List<String> ids) {
		List<Ref<MarketGroup>> entityRefs = new ArrayList<Ref<MarketGroup>>();
		for (String id : ids) {
			Key<MarketGroup> key = Key.create(id);
			entityRefs.add(Ref.create(key));
		}
		return entityRefs;
	}
}
