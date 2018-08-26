/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.common.Currency;

/**
 * @author CR
 *
 */
public class HotelConfig {

	public HotelConfig() {
	}

	/**
	 * Alapértelmezett valutanem.
	 */
	private Ref<Currency> currency;

	/**
	 * Árfolyamnyereség szolgáltatás.
	 */
	private Ref<Service> xrtGainService;

	/**
	 * Árfolyamvesztesség szolgáltatás.
	 */
	private Ref<Service> xrtLossService;

	public Currency getCurrency() {
		return currency.get();
	}

	public void setCurrency(Currency currency) {
		this.currency = Ref.create(currency);
	}

	public Service getXrtGainService() {
		return xrtGainService.get();
	}

	public void setXrtGainService(Service xrtGainService) {
		this.xrtGainService = Ref.create(xrtGainService);
	}

	public Service getXrtLossService() {
		return xrtLossService.get();
	}

	public void setXrtLossService(Service xrtLossService) {
		this.xrtLossService = Ref.create(xrtLossService);
	}

}
