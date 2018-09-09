/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import hu.hw.cloud.shared.cnst.RateBase;
import hu.hw.cloud.shared.cnst.RatePostingRhythm;
import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.common.CurrencyDto;
import hu.hw.cloud.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RateElementDto implements Dto {

	/**
	 * Szolgáltatáskód hivatkozás.
	 */
	private ServiceDto service;

	/**
	 * Egységár.
	 */
	private Double price;

	/**
	 * Egységár valutaneme (hivatkozás).
	 */
	private CurrencyDto currency;

	/**
	 * Terhelendő mennyiség.
	 */
	private Integer qty;

	/**
	 * Árkalkulálás módja.
	 */
	private RateBase rateBase;

	/**
	 * Terhelés ritmusa.
	 */
	private RatePostingRhythm postingRhythm;

	/**
	 * Igénybevétel következő nap.
	 */
	private Boolean useNextDay;

	/**
	 * Szállásba bújtatva.
	 */
	private Boolean arrangement;

	public ServiceDto getService() {
		return service;
	}

	public void setService(ServiceDto service) {
		this.service = service;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public RateBase getRateBase() {
		return rateBase;
	}

	public void setRateBase(RateBase rateBase) {
		this.rateBase = rateBase;
	}

	public RatePostingRhythm getPostingRhythm() {
		return postingRhythm;
	}

	public void setPostingRhythm(RatePostingRhythm postingRhythm) {
		this.postingRhythm = postingRhythm;
	}

	public Boolean getUseNextDay() {
		return useNextDay;
	}

	public void setUseNextDay(Boolean useNextDay) {
		this.useNextDay = useNextDay;
	}

	public Boolean getArrangement() {
		return arrangement;
	}

	public void setArrangement(Boolean arrangement) {
		this.arrangement = arrangement;
	}

}
