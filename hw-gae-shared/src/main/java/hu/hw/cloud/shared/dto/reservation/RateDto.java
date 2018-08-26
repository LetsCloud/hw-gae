/**
 * 
 */
package hu.hw.cloud.shared.dto.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.hw.cloud.shared.cnst.CityTaxMethod;
import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.common.CurrencyDto;
import hu.hw.cloud.shared.dto.hotel.RateElementDto;
import hu.hw.cloud.shared.dto.hotel.RatePlanDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RateDto implements Dto {

	/**
	 * Mettől érvényes az árkód.
	 */
	private Date fromDate;

	/**
	 * Meddig érvényes az árkód.
	 */
	private Date toDate;

	/**
	 * Árkód.
	 */
	private RatePlanDto ratePlan;

	/**
	 * Ár.
	 */
	private Double rate;

	/**
	 * Valutanem.
	 */
	private CurrencyDto currency;

	/**
	 * IFA kalkulálás módja.
	 */
	private CityTaxMethod cityTaxMethod;

	/**
	 * Árkód összetevők.
	 */
	private List<RateElementDto> rateElements = new ArrayList<RateElementDto>();

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

	public RatePlanDto getRatePlan() {
		return ratePlan;
	}

	public void setRatePlan(RatePlanDto ratePlan) {
		this.ratePlan = ratePlan;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public CurrencyDto getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyDto currency) {
		this.currency = currency;
	}

	public CityTaxMethod getCityTaxMethod() {
		return cityTaxMethod;
	}

	public void setCityTaxMethod(CityTaxMethod cityTaxMethod) {
		this.cityTaxMethod = cityTaxMethod;
	}

	public List<RateElementDto> getRateElements() {
		return rateElements;
	}

	public void setRateElements(List<RateElementDto> rateElements) {
		this.rateElements = rateElements;
	}

}
