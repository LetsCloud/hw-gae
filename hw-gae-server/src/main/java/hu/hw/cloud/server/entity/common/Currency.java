/**
 * 
 */
package hu.hw.cloud.server.entity.common;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.shared.dto.common.CurrencyDto;

/**
 * @author CR
 *
 */
@Entity
public class Currency extends AccountChild {

	public static Ref<Currency> createRef(String webSafeString) {
		Key<Currency> key = Key.create(webSafeString);
		return Ref.create(key);
	}

	public String createWebSafeKey(Ref<Currency> ref) {
		Key<Currency> key = ref.getKey();
		return key.getString();
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public static CurrencyDto createDto(Currency entity) {
		CurrencyDto dto = new CurrencyDto();
		dto.setAccountDto(Account.createDto(entity.getAccount()));
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());
		dto.setDisplayedDecimals(entity.getDisplayedDecimals());
		dto.setExchangeRateDtos(ExchangeRate.createDtos(entity.getExchangeRates()));
		dto.setId(entity.getId());
		dto.setWebSafeKey(entity.getWebSafeKey());
		dto.setRateMultiplier(entity.getRateMultiplier());
		dto.setRoundingPrecision(entity.getRoundingPrecision());
		dto.setVersion(entity.getVersion());
		return dto;
	}

	/**
	 * A valutanem egyedi azonosítója.
	 */
	@Index
	private String code;

	/**
	 * A valutanem megnevezése
	 */
	private String description;

	/**
	 * Tizedes megjelenítése
	 */
	private Integer displayedDecimals;

	/**
	 * Kerekítés pontosság (0; 0,1 ; 0,01)
	 */
	private Float roundingPrecision;

	private Float rateMultiplier;

	private List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();

	public Currency() {
	}

	public Currency(CurrencyDto dto) {
		this();
		this.setAccount(new Account(dto.getAccountDto()));
		this.setCode(dto.getCode());
		this.setDescription(dto.getDescription());
		this.setDisplayedDecimals(dto.getDisplayedDecimals());
		this.setExchangeRates(ExchangeRate.createList(dto.getExchangeRateDtos()));
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

	public Integer getDisplayedDecimals() {
		return displayedDecimals;
	}

	public void setDisplayedDecimals(Integer displayedDecimals) {
		this.displayedDecimals = displayedDecimals;
	}

	public Float getRoundingPrecision() {
		return roundingPrecision;
	}

	public void setRoundingPrecision(Float roundingPrecision) {
		this.roundingPrecision = roundingPrecision;
	}

	public Float getRateMultiplier() {
		return rateMultiplier;
	}

	public void setRateMultiplier(Float rateMultiplier) {
		this.rateMultiplier = rateMultiplier;
	}

	public List<ExchangeRate> getExchangeRates() {
		return exchangeRates;
	}

	public void setExchangeRates(List<ExchangeRate> exchangeRates) {
		this.exchangeRates = exchangeRates;
	}

	public void update(CurrencyDto dto) {
		clearUniqueIndexes();

		super.updEntityWithDto(dto);

		if (dto.getCode() != null)
			this.setCode(dto.getCode());

		if (dto.getDescription() != null)
			this.setDescription((dto.getDescription()));

		if (dto.getDisplayedDecimals() != null)
			this.setDisplayedDecimals((dto.getDisplayedDecimals()));

		if (dto.getExchangeRateDtos() != null)
			this.setExchangeRates(ExchangeRate.createList(dto.getExchangeRateDtos()));

		if (dto.getRateMultiplier() != null)
			this.setRateMultiplier((dto.getRateMultiplier()));

		if (dto.getRoundingPrecision() != null)
			this.setRoundingPrecision((dto.getRoundingPrecision()));
	}

	/**
	 * 
	 * @author CR
	 *
	 */
	public static class Builder extends AccountChild.Builder<Builder> {

		private String code;

		private String description;

		private Integer displayedDecimals;

		private Float roundingPrecision;

		private Float rateMultiplier;

		private List<ExchangeRate> exchangeRates = new ArrayList<ExchangeRate>();

		public Builder() {
		}

		public Builder code(String code) {
			this.code = code;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder displayedDecimals(Integer displayedDecimals) {
			this.displayedDecimals = displayedDecimals;
			return this;
		}

		public Builder roundingPrecision(Float roundingPrecision) {
			this.roundingPrecision = roundingPrecision;
			return this;
		}

		public Builder rateMultiplier(Float rateMultiplier) {
			this.rateMultiplier = rateMultiplier;
			return this;
		}

		public Builder exchangeRates(List<ExchangeRate> exchangeRates) {
			this.exchangeRates = exchangeRates;
			return this;
		}

		public Builder addExchangeRate(ExchangeRate exchangeRate) {
			this.exchangeRates.add(exchangeRate);
			return this;
		}

		public Currency build() {
			return new Currency(this);
		}
	}

	protected Currency(Builder builder) {
		super(builder);
		this.code = builder.code;
		this.description = builder.description;
		this.displayedDecimals = builder.displayedDecimals;
		this.exchangeRates = builder.exchangeRates;
		this.rateMultiplier = builder.rateMultiplier;
		this.roundingPrecision = builder.roundingPrecision;
	}
}
