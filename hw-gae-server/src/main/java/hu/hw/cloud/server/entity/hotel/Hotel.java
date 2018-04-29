/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.ExceptionType;

/**
 * @author CR
 *
 */
@Entity
public class Hotel extends AccountChild {
	private static final Logger logger = Logger.getLogger(Hotel.class.getName());

	// private static final String PROPERTY_ACCOUNTID = "accounId";
	private static final String PROPERTY_CODE = "code";
	private static final String PROPERTY_NAME = "name";

	/**
	 * Egyedi azonosító
	 */
	@Index
	private String code;

	/**
	 * Név
	 */
	private String name;

	/**
	 * Üzleti dátum
	 */
	private Date businessDate;

	/**
	 * A hotel ügyviteli konfigurációja
	 */
	private HotelConfig config;

	/**
	 * Idegenforgalmi adó beállítások
	 */
	private List<CityTax> cityTaxes = new ArrayList<CityTax>();

	/**
	 * 
	 */
	public Hotel() {
		logger.info("Hotel()");
	}

	/**
	 * 
	 * @param dto
	 */
	public Hotel(HotelDto dto) {
		this();
		updEntityWithDto(dto);
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

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public HotelConfig getConfig() {
		return config;
	}

	public void setConfig(HotelConfig config) {
		this.config = config;
	}

	public List<CityTax> getCityTaxes() {
		return cityTaxes;
	}

	public void setCityTaxes(List<CityTax> cityTaxes) {
		this.cityTaxes = cityTaxes;
	}

	@Override
	public String toString() {
		return "Hotel [ generatedId=" + this.getId() + ", code=" + code + ", name=" + name + "]";
	}

	@Override
	public void validate() throws EntityValidationException {
		/*
		 * if (this.getAccountWebSafeKey() == null) { throw new
		 * EntityValidationException(ExceptionType.MISSING_VALUE,
		 * getClass().getSimpleName(), PROPERTY_ACCOUNTID); }
		 */
		if (this.getCode() == null) {
			throw new EntityValidationException(ExceptionType.MISSING_VALUE, getClass().getSimpleName(), PROPERTY_CODE);
		}
		if (this.getName() == null) {
			throw new EntityValidationException(ExceptionType.MISSING_VALUE, getClass().getSimpleName(), PROPERTY_NAME);
		}
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public Hotel updEntityWithDto(HotelDto dto) {
		clearUniqueIndexes();

		super.updEntityWithDto(dto);

		if (dto.getCode() != null) {
			setCode(dto.getCode());
			if (!dto.getCode().equals(this.getCode()))
				addUniqueIndex(PROPERTY_CODE, dto.getCode());
		}

		if (dto.getName() != null)
			setName(dto.getName());

		if (dto.getBusinessDate() != null)
			setBusinessDate(dto.getBusinessDate());

		if (dto.getConfig() != null)
			setConfig(new HotelConfig(dto.getConfig()));

		if (dto.getCityTaxes() != null)
			setCityTaxes(CityTax.createList(dto.getCityTaxes()));

		return this;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static HotelDto createDto(Hotel entity) {
		HotelDto dto = new HotelDto();
		dto = entity.updDtoWithEntity(dto);
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public HotelDto updDtoWithEntity(HotelDto dto) {
		dto = (HotelDto) super.updDtoWithEntity(dto);

		if (getCode() != null)
			dto.setCode(getCode());

		if (getName() != null)
			dto.setName(getName());
		
		if (getBusinessDate() != null)
			dto.setBusinessDate(getBusinessDate());
		
		if (getCityTaxes() != null)
			dto.setCityTaxes(CityTax.createDtos(getCityTaxes()));

		if (getConfig() != null)
			dto.setConfig(HotelConfig.createDto(getConfig()));

		return dto;
	}

	/**
	 * 
	 * @param entities
	 * @return
	 */
	public static List<HotelDto> createDtos(List<Hotel> entities) {
		List<HotelDto> dtos = new ArrayList<HotelDto>();
		for (Hotel entity : entities) {
			dtos.add(Hotel.createDto(entity));
		}
		return dtos;
	}

	/**
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<Hotel> createList(List<HotelDto> dtos) {
		List<Hotel> entities = new ArrayList<Hotel>();
		for (HotelDto dto : dtos) {
			entities.add(new Hotel(dto));
		}
		return entities;
	}

	public static Ref<Hotel> createRef(String webSafeString) {
		Key<Hotel> key = Key.create(webSafeString);
		return Ref.create(key);
	}

	public String createWebSafeKey(Ref<Hotel> ref) {
		Key<Hotel> key = ref.getKey();
		return key.getString();
	}
}
