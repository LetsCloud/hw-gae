/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.Account;
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

	// private static final String PROPERTY_ACCOUNTID = "accounId";
	private static final String PROPERTY_CODE = "code";
	private static final String PROPERTY_NAME = "name";

	public static Ref<Hotel> createRef(String webSafeString) {
		Key<Hotel> key = Key.create(webSafeString);
		return Ref.create(key);
	}

	public String createWebSafeKey(Ref<Hotel> ref) {
		Key<Hotel> key = ref.getKey();
		return key.getString();
	}

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
	 */
	public Hotel() {
	}

	/**
	 * 
	 * @param dto
	 */
	public Hotel(HotelDto dto) {
		super();
		clearUniqueIndexes();
//		LOGGER.info("Hotel(HotelDto dto)-START");
		if (dto.getAccountDto() != null)
			this.setAccount(new Account(dto.getAccountDto()));
		/*
		 * if (dto.getAccountWebSafeKey() != null) {
		 * LOGGER.info("Hotel(HotelDto dto)-setAccountId");
		 * this.setAccountWebSafeKey(dto.getAccountWebSafeKey()); }
		 */
		if (dto.getBusinessDate() != null)
			this.setBusinessDate(dto.getBusinessDate());
		if (dto.getCityTaxes() != null)
			this.setCityTaxes(CityTax.createList(dto.getCityTaxes()));
		if (dto.getCode() != null) {
			this.setCode(dto.getCode());
			addUniqueIndex(PROPERTY_CODE, dto.getCode());
		}
		if (dto.getConfig() != null)
			this.setConfig(new HotelConfig(dto.getConfig()));
		if (dto.getId() != null)
			this.setId(dto.getId());
		if (dto.getWebSafeKey() != null) {
//			LOGGER.info("Hotel(HotelDto dto) id=" + dto.getWebSafeKey());
			this.setWebSafeKey(dto.getWebSafeKey());
			Key<Hotel> key = Key.create(dto.getWebSafeKey());
//			LOGGER.info("Hotel(HotelDto dto) generatedId=" + key.getId());
			this.setId(key.getId());
		}
		if (dto.getName() != null)
			this.setName(dto.getName());
		// this.setVersion(dto.getVersion());
//		LOGGER.info("Hotel(HotelDto dto)-END");
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

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public Hotel update(HotelDto dto) {
		clearUniqueIndexes();
//		LOGGER.info("update");
		if (dto.getBusinessDate() != null)
			this.setBusinessDate(dto.getBusinessDate());
		if (dto.getCode() != null) {
//			LOGGER.info("(dto.getCode() != null)");
			if (!this.getCode().equals(dto.getCode())) {
//				LOGGER.info("(!this.getCode().equals(dto.getCode()))");
				addUniqueIndex(PROPERTY_CODE, dto.getCode());
			}
			this.setCode(dto.getCode());
		}
		if (dto.getName() != null)
			this.setName(dto.getName());
		if (dto.getConfig() != null)
			this.setConfig(new HotelConfig(dto.getConfig()));
		return this;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static HotelDto createDto(Hotel entity) {
		HotelDto dto = new HotelDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public HotelDto updateDto(HotelDto dto) {
		dto = (HotelDto) super.updateDto(dto);
		if (getBusinessDate() != null)
			dto.setBusinessDate(getBusinessDate());
		if (getCityTaxes() != null)
			dto.setCityTaxes(CityTax.createDtos(getCityTaxes()));
		if (getCode() != null)
			dto.setCode(getCode());
		if (getConfig() != null)
			dto.setConfig(HotelConfig.createDto(getConfig()));
		if (getName() != null)
			dto.setName(getName());
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
}
