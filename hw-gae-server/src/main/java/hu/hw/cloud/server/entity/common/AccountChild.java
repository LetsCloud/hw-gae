/**
 * 
 */
package hu.hw.cloud.server.entity.common;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Parent;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.shared.dto.common.AccountChildDto;

/**
 * @author CR
 *
 */
public class AccountChild extends BaseEntity {
//	private static final Logger logger = LoggerFactory.getLogger(AccountChild.class.getName());

	@Parent
	private Ref<Account> accountRef;

	/**
	 * Objectify miatt
	 */
	public AccountChild() {
//		logger.info("AccountChild()");
	}

	public Account getAccount() {
		return accountRef.get();
	}

	public void setAccount(Account account) {
		this.accountRef = Ref.create(account);
	}

	/**
	 * Entitás módosítása DTO adataival
	 * 
	 * @param dto
	 */
	public void updEntityWithDto(AccountChildDto dto) {
		super.updEntityWithDto(dto);

		if (dto.getAccountDto() != null)
			setAccount(new Account(dto.getAccountDto()));
	}

	/**
	 * DTO módosítása entitás attribútumokkal
	 * 
	 * @param dto
	 * @return
	 */
	public AccountChildDto updDtoWithEntity(AccountChildDto dto) {
		dto = (AccountChildDto) super.updDtoWithEntity(dto);
		if (getAccount() != null)
			dto.setAccountDto(Account.createDto(getAccount()));
		return dto;
	}

	/**
	 * 
	 * @author CR
	 *
	 * @param <T>
	 */
	public static class Builder<T extends Builder<?>> {

		private Account account;

		public Builder() {
		}

		@SuppressWarnings("unchecked")
		public T account(Account account) {
			this.account = account;
			return (T) this;
		}

		public AccountChild build() {
			return new AccountChild(this);
		}
	}

	protected AccountChild(Builder<?> builder) {
		this.setAccount(builder.account);
	}
}
