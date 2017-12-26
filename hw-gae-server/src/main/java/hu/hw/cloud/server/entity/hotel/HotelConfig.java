/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.shared.dto.hotel.HotelConfigDto;

/**
 * @author CR
 *
 */
public class HotelConfig {

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static HotelConfigDto createDto(HotelConfig entity) {
		HotelConfigDto dto = new HotelConfigDto();
		if (entity.getCurrency() != null)
			dto.setCurrencyRef(Currency.createDto(entity.getCurrency()));
		dto.setXrtGainServiceRef(Service.createDto(entity.getXrtGainService()));
		dto.setXrtLossServiceRef(Service.createDto(entity.getXrtLossService()));
		return dto;
	}

	public HotelConfig() {
	}

	public HotelConfig(HotelConfigDto dto) {
		super();
		if (dto.getCurrencyRef() != null)
			this.setCurrency(new Currency(dto.getCurrencyRef()));
	}

	/**
	 * Alapértelmezett valutanem.
	 */
	private Ref<Currency> currencyRef;

	/**
	 * Árfolyamnyereség szolgáltatás.
	 */
	private Ref<Service> xrtGainServiceRef;

	/**
	 * Árfolyamvesztesség szolgáltatás.
	 */
	private Ref<Service> xrtLossServiceRef;

	public Ref<Currency> getCurrencyRef() {
		return currencyRef;
	}

	public void setCurrencyRef(Ref<Currency> currencyRef) {
		this.currencyRef = currencyRef;
	}

	public Currency getCurrency() {
		return currencyRef.get();
	}

	public void setCurrency(Currency currency) {
		this.currencyRef = Ref.create(currency);
	}

	public Ref<Service> getXrtGainServiceRef() {
		return xrtGainServiceRef;
	}

	public void setXrtGainServiceRef(Ref<Service> xrtGainServiceRef) {
		this.xrtGainServiceRef = xrtGainServiceRef;
	}

	public Ref<Service> getXrtLossServiceRef() {
		return xrtLossServiceRef;
	}

	public void setXrtLossServiceRef(Ref<Service> xrtLossServiceRef) {
		this.xrtLossServiceRef = xrtLossServiceRef;
	}

	public Service getXrtGainService() {
		return xrtGainServiceRef.get();
	}

	public void setXrtGainService(Service xrtGainService) {
		this.xrtGainServiceRef = Ref.create(xrtGainService);
	}

	public Service getXrtLossService() {
		return xrtLossServiceRef.get();
	}

	public void setXrtLossService(Service xrtLossService) {
		this.xrtLossServiceRef = Ref.create(xrtLossService);
	}

}
