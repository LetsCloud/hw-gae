/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.ExceptionType;

/**
 * @author CR
 *
 */
@Entity
public class Hotel extends AccountChild {
//	private static final Logger logger = LoggerFactory.getLogger(Hotel.class.getName());

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
//		logger.info("Hotel()");
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
}
