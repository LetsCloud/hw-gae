/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import hu.hw.cloud.shared.dto.Dto;
import hu.hw.cloud.shared.dto.common.CurrencyDto;
import hu.hw.cloud.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class HotelConfigDto implements Dto {

	/**
	 * Alapértelmezett valutanem.
	 */
	private CurrencyDto currencyRef;

	/**
	 * Árfolyamnyereség szolgáltatás.
	 */
	private ServiceDto xrtGainServiceRef;

	/**
	 * Árfolyamvesztesség szolgáltatás.
	 */
	private ServiceDto xrtLossServiceRef;

	public CurrencyDto getCurrencyRef() {
		return currencyRef;
	}

	public void setCurrencyRef(CurrencyDto currencyRef) {
		this.currencyRef = currencyRef;
	}

	public ServiceDto getXrtGainServiceRef() {
		return xrtGainServiceRef;
	}

	public void setXrtGainServiceRef(ServiceDto xrtGainServiceRef) {
		this.xrtGainServiceRef = xrtGainServiceRef;
	}

	public ServiceDto getXrtLossServiceRef() {
		return xrtLossServiceRef;
	}

	public void setXrtLossServiceRef(ServiceDto xrtLossServiceRef) {
		this.xrtLossServiceRef = xrtLossServiceRef;
	}
}
