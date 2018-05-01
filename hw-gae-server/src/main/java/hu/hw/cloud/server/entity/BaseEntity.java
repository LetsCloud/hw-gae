/**
 * 
 */
package hu.hw.cloud.server.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;

import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.exception.EntityValidationException;

/**
 * @author CR
 *
 */
public abstract class BaseEntity {

	/**
	 * Generált ID amit az Objectify használ az entitás példány entitás típuson
	 * belüli azonosítására
	 */
	@Id
	private Long id;

	/**
	 * Az entitás kulcsából képzett egyedi adzonosító
	 */
	@Ignore
	private String webSafeKey;

	/**
	 * Az entitás verzió követését szolgálja
	 */
	private Long version = 0L;

	@Ignore
	private Map<String, Object> uniqueIndexes = new HashMap<String, Object>();

	/**
	 * Auto-increment version # whenever persisted
	 */
	@OnSave
	private void onSave() {
		this.version++;
	}

	/**
	 * Az entitás betöltésekor az id tul egyedi azonosítására szolgáló kulcsdd
	 * értéket az ID mezőnek.
	 */
	@OnLoad
	private void onLoad() {
		Key<BaseEntity> key = Key.create(this);
		this.webSafeKey = key.getString();
	}

	public BaseEntity() {
	}

	public BaseEntity(BaseDto dto) {
		this();
		this.setId(dto.getId());
		this.setVersion(dto.getVersion());
	}

	public BaseEntity(BaseEntity source) {
		this();
		this.id = source.id;
		this.version = source.version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWebSafeKey() {
		if (id != null) {
			Key<BaseEntity> key = Key.create(this);
			return key.getString();
		}
		return webSafeKey;
	}

	public void setWebSafeKey(String webSafeKey) {
		this.webSafeKey = webSafeKey;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Map<String, Object> getUniqueIndexes() {
		return uniqueIndexes;
	}

	@JsonIgnore
	public boolean isSaved() {
		return webSafeKey != null;
	}

	public void clearUniqueIndexes() {
		uniqueIndexes.clear();
	}

	public void addUniqueIndex(String property, Object value) {
		uniqueIndexes.put(property, value);
	}

	public void validate() throws EntityValidationException {
	}

	/**
	 * Entitás módosítása DTO adataival
	 * 
	 * @param dto
	 */
	public void updEntityWithDto(BaseDto dto) {
		if (dto.getId() != null)
			setId(dto.getId());
		if (dto.getVersion() != null)
			setVersion(dto.getVersion());
	}

	/**
	 * Entitás módosítása egy másik entitás adataival
	 * 
	 * @param entity
	 */
	public void updEntityWithEntity(BaseEntity entity) {
		if (entity.getId() != null)
			setId(entity.getId());
		if (entity.getVersion() != null)
			setVersion(entity.getVersion());
	}

	/**
	 * DTO módosítása zz entitás adataival
	 * 
	 * @param dto
	 * @return
	 */
	public BaseDto updDtoWithEntity(BaseDto dto) {
		if (getId() != null)
			dto.setId(getId());
		if (getVersion() != null)
			dto.setVersion(getVersion());
		if (getWebSafeKey() != null)
			dto.setWebSafeKey(getWebSafeKey());
		return dto;
	}
}
