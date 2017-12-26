/**
 * 
 */
package hu.hw.cloud.shared.dto.reservation;

import java.util.Date;

import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.common.CurrencyDto;
import hu.hw.cloud.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class FixedChargeDto implements Dto {

	/**
	 * Mettől terheljen.
	 */
	private Date fromDate;

	/**
	 * Meddig terheljen.
	 */
	private Date toDate;

	/**
	 * Szolgáltatáskód hivatkozás.
	 */
	private ServiceDto serviceDto;

	/**
	 * Egységár.
	 */
	private Double price;

	/**
	 * Egységár valutaneme (hivatkozás).
	 */
	private CurrencyDto currencyDto;

	/**
	 * Terhelendő mennyiség.
	 */
	private Integer qty;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public ServiceDto getServiceDto() {
		return serviceDto;
	}

	public void setServiceDto(ServiceDto serviceDto) {
		this.serviceDto = serviceDto;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CurrencyDto getCurrencyDto() {
		return currencyDto;
	}

	public void setCurrencyDto(CurrencyDto currencyDto) {
		this.currencyDto = currencyDto;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
