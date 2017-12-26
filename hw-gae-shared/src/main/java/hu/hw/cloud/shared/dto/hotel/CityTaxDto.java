/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import java.util.Date;

import hu.hw.cloud.shared.cnst.CityTaxBase;
import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class CityTaxDto implements Dto {

	/**
	 * Mettől érvényes.
	 */
	private Date validFrom;

	/**
	 * Számítás alapja.
	 */
	private CityTaxBase based;

	/**
	 * Szolgáltatgáskódja.
	 */
	private ServiceDto serviceRef;

	/**
	 * Mértéke, a számítás alapjától függően százalék vagy fiy összeg.
	 */
	private Float value;

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public CityTaxBase getBased() {
		return based;
	}

	public void setBased(CityTaxBase based) {
		this.based = based;
	}

	public ServiceDto getServiceRef() {
		return serviceRef;
	}

	public void setServiceRef(ServiceDto serviceRef) {
		this.serviceRef = serviceRef;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
}
