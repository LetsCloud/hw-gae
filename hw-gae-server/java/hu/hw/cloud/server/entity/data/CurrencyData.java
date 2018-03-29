/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.entity.common.ExchangeRate;
import hu.hw.cloud.server.repository.CurrencyRepository;
import hu.hw.cloud.server.service.DataBuilderService;

/**
 * @author CR
 *
 */
public class CurrencyData extends BaseData<Currency> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyData.class.getName());

	private Currency currencyHuf;

	private Currency currencyEur;

	public CurrencyData(DataBuilderService dbs, CurrencyRepository repo) {
		super(dbs, repo);
		LOGGER.info("CurrencyData()");
	}

	public void build() {
		getCurrencyHuf();
		getCurrencyEur();
	}

	public Currency getCurrencyHuf() {
		if (currencyHuf != null)
			return currencyHuf;

		// @formatter:off
		ExchangeRate er01 = ExchangeRate.builder()
				.currencyBuying(1f)
				.currencySelling(1f)
				.foreignBuying(1f)
				.foreignSelling(1f)
				.internal(1f)
				.middle(1f)
				.validFrom(dbs.getBusinessDate())
				.build();

		currencyHuf = new Currency.Builder()
				.account(dbs.getAccount())
				.code("HUF")
				.description("Magyar forint")
				.displayedDecimals(0)
				.roundingPrecision(0f)
				.addExchangeRate(er01)
				.build();
		// @formatter:on

		currencyHuf = save(currencyHuf);

		return currencyHuf;
	}

	public Currency getCurrencyEur() {
		if (currencyEur != null)
			return currencyEur;

		// @formatter:off
		ExchangeRate er01 = ExchangeRate.builder()
				.currencyBuying(300f)
				.currencySelling(300f)
				.foreignBuying(300f)
				.foreignSelling(300f)
				.internal(300f)
				.middle(300f)
				.validFrom(dbs.getBusinessDate())
				.build();

		currencyEur = new Currency.Builder()
				.account(dbs.getAccount())
				.code("EUR")
				.description("Euro")
				.displayedDecimals(0)
				.roundingPrecision(0f)
				.addExchangeRate(er01)
				.build();
		// @formatter:on

		currencyEur = save(currencyEur);

		return currencyEur;
	}

}
