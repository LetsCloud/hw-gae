/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.shared.cnst.CityTaxBase;
import hu.hw.cloud.shared.dto.hotel.CityTaxDto;

/**
 * IFA mérték.
 * 
 * @author CR
 *
 */
public class CityTax {

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
	private Ref<Service> serviceRef;

	/**
	 * Mértéke, a számítás alapjától függően százalék vagy fix összeg.
	 */
	private Float value;

	/**
	 * Objectify miatt
	 */
	public CityTax() {
	}

	/**
	 * Entitás létrehozása DTO-ból
	 * 
	 * @param dto
	 */
	public CityTax(CityTaxDto dto) {
		this();
		update(dto);
	}

	/**
	 * Entitás módosítása DTO alapján
	 * 
	 * @param dto
	 */
	public void update(CityTaxDto dto) {
		this.setBased(dto.getBased());
		this.setService(new Service(dto.getServiceRef()));
		this.setValidFrom(dto.getValidFrom());
		this.setValue(dto.getValue());
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public CityTaxBase getBased() {
		return based;
	}

	public void setBased(CityTaxBase type) {
		this.based = type;
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

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	/**
	 * DTO létrehozása entitás alapján
	 * 
	 * @param entity
	 * @return
	 */
	public static CityTaxDto createDto(CityTax entity) {
		CityTaxDto dto = new CityTaxDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	/**
	 * DTO módosítása entitás alapján
	 * 
	 * @param dto
	 * @return
	 */
	public CityTaxDto updateDto(CityTaxDto dto) {
		dto.setBased(this.getBased());
		dto.setServiceRef(Service.createDto(this.getService()));
		dto.setValidFrom(this.getValidFrom());
		dto.setValue(this.getValue());
		return dto;
	}

	/**
	 * 
	 * @param entities
	 * @return
	 */
	public static List<CityTaxDto> createDtos(List<CityTax> entities) {
		List<CityTaxDto> dtos = new ArrayList<CityTaxDto>();
		for (CityTax entity : entities)
			dtos.add(createDto(entity));
		return dtos;
	}

	/**
	 * 
	 * @param dtos
	 * @return
	 */
	public static List<CityTax> createList(List<CityTaxDto> dtos) {
		List<CityTax> entities = new ArrayList<CityTax>();
		for (CityTaxDto dto : dtos)
			entities.add(new CityTax(dto));
		return entities;
	}

	/**
	 * Az IFA mértékek érvényeségi dátum szerint rendezését szolgáló Comparator
	 */
	public static Comparator<CityTax> ORDER_BY_VALIDFROM = new Comparator<CityTax>() {
		public int compare(CityTax one, CityTax other) {
			return one.getValidFrom().compareTo(other.getValidFrom());
		}
	};

	/**
	 * A megadott dátum alkalmaával érvényes IFA mértéket adja vissza.
	 * 
	 * @param cityTaxes
	 *            Az IFA mértékek listája amelyből választ az eljárás.
	 * @param date
	 *            A megadott dátum.
	 * @return Az érvényes CityTax objektum.
	 */
	public static CityTax getValidCityTax(List<CityTax> cityTaxes, final Date date) {
		Predicate<CityTax> condition = new Predicate<CityTax>() {
			public boolean evaluate(CityTax object) {
				return (object.getValidFrom().compareTo(date) >= 0);
			}
		};
		List<CityTax> result = (List<CityTax>) CollectionUtils.select(cityTaxes, condition);

		if (result.isEmpty())
			return null;

		return Collections.max(result, CityTax.ORDER_BY_VALIDFROM);
	}

}
