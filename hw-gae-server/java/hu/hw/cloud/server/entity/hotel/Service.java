/**
 * 
 */
package hu.hw.cloud.server.entity.hotel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.shared.cnst.ServiceType;
import hu.hw.cloud.shared.dto.common.ServiceDto;

/**
 * @author CR
 *
 */
@Entity
public class Service extends AccountChild {

	public static Ref<Service> createRef(String webSafeString) {
		Key<Service> key = Key.create(webSafeString);
		return Ref.create(key);
	}

	public String createWebSafeKey(Ref<Service> ref) {
		Key<Service> key = ref.getKey();
		return key.getString();
	}

	public static ServiceDto createDto(Service entity) {
		ServiceDto dto = new ServiceDto();
		dto.setAccountDto(Account.createDto(entity.getAccount()));
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		dto.setId(entity.getId());
		dto.setWebSafeKey(entity.getWebSafeKey());
		dto.setType(entity.getType());
		dto.setVatCode(entity.getVatCode());
		dto.setVersion(entity.getVersion());
		return dto;
	}

	/**
	 * Egyedi azonosító
	 */
	@Index
	private String code;

	/**
	 * Megnevezés
	 */
	private String description;

	private ServiceType type;

	/**
	 * ÁFA kód
	 */
	private String vatCode;

	public Service() {
	}

	public Service(ServiceDto dto) {
		super();
		this.setAccount(new Account(dto.getAccountDto()));
		this.setCode(dto.getCode());
		this.setDescription(dto.getDescription());
		this.setId(dto.getId());
		this.setWebSafeKey(dto.getWebSafeKey());
		this.setType(dto.getType());
		this.setVatCode(dto.getVatCode());
		this.setVersion(dto.getVersion());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ServiceType getType() {
		return type;
	}

	public void setType(ServiceType type) {
		this.type = type;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}

}
