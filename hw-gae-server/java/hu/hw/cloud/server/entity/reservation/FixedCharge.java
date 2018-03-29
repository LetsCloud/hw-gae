/**
 * 
 */
package hu.hw.cloud.server.entity.reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.entity.hotel.Service;
import hu.hw.cloud.shared.dto.reservation.FixedChargeDto;

/**
 * @author CR
 *
 */
public class FixedCharge {

	public static FixedChargeDto createDto(FixedCharge entity) {
		FixedChargeDto dto = new FixedChargeDto();
		dto.setCurrencyDto(Currency.createDto(entity.getCurrency()));
		dto.setFromDate(entity.getFromDate());
		dto.setPrice(entity.getPrice());
		dto.setQty(entity.getQty());
		dto.setServiceDto(Service.createDto(entity.getService()));
		dto.setToDate(entity.getToDate());
		return dto;
	}

	public static List<FixedChargeDto> createDtos(List<FixedCharge> entities) {
		List<FixedChargeDto> dtos = new ArrayList<FixedChargeDto>();
		for (FixedCharge entity : entities) {
			dtos.add(createDto(entity));
		}
		return dtos;
	}

	public static List<FixedCharge> createList(List<FixedChargeDto> dtos) {
		List<FixedCharge> ret = new ArrayList<FixedCharge>();

		for (FixedChargeDto dto : dtos) {
			ret.add(new FixedCharge(dto));
		}
		return ret;
	}

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
	private Ref<Service> serviceRef;

	/**
	 * Egységár.
	 */
	private Double price;

	/**
	 * Egységár valutaneme (hivatkozás).
	 */
	private Ref<Currency> currencyRef;

	/**
	 * Terhelendő mennyiség.
	 */
	private Integer qty;

	public FixedCharge() {
	}

	public FixedCharge(FixedChargeDto dto) {
		this();

		this.setCurrencyRef(Currency.createRef(dto.getCurrencyDto().getWebSafeKey()));
		this.setFromDate(dto.getFromDate());
		this.setPrice(dto.getPrice());
		this.setQty(dto.getQty());
		this.setServiceRef(Service.createRef(dto.getServiceDto().getWebSafeKey()));
		this.setToDate(dto.getToDate());
	}

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

	public Ref<Service> getServiceRef() {
		return serviceRef;
	}

	public void setServiceRef(Ref<Service> serviceRef) {
		this.serviceRef = serviceRef;
	}

	public Service getService() {
		return serviceRef.get();
	}

	public void setService(Service service) {
		this.serviceRef = Ref.create(service);
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}