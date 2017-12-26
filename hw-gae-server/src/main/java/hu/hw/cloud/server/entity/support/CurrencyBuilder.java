/**
 * 
 */
package hu.hw.cloud.server.entity.support;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.repository.CurrencyRepository;

/**
 * @author CR
 *
 */
public class CurrencyBuilder extends EntityBuilder<Currency> {

	@Autowired
	private CurrencyRepository repo;

	@Override
	void initProduct() {
		this.product = new Currency();
	}

	@Override
	Currency assembleProduct() {
		return this.product;
	}

	@Override
	Currency saveProduct(Currency entity) {
		if (repo != null) {
			try {
				this.product = repo.save(entity);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Key<Currency> key = ObjectifyService.ofy().save().entity(entity).now();
			this.product = ObjectifyService.ofy().load().key(key).now();
		}
		return this.product;
	}

	public CurrencyBuilder account(Account account) {
		this.product.setAccount(account);
		return this;
	}

	public CurrencyBuilder code(String code) {
		this.product.setCode(code);
		return this;
	}

	public CurrencyBuilder description(String description) {
		this.product.setDescription(description);
		return this;
	}

	public CurrencyBuilder displayedDecimals(Integer displayedDecimals) {
		this.product.setDisplayedDecimals(displayedDecimals);
		return this;
	}

	public CurrencyBuilder roundingPrecision(Float roundingPrecision) {
		this.product.setRoundingPrecision(roundingPrecision);
		return this;
	}

}
