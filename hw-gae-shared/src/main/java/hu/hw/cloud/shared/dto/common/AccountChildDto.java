/**
 * 
 */
package hu.hw.cloud.shared.dto.common;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class AccountChildDto extends BaseDto {

	private AccountDto account;

	public AccountChildDto() {
	}

	public AccountChildDto(AccountChildDto dto) {
		super(dto);
		this.account = dto.getAccount();
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "AccountChildDto:{account=" + account + ", " + super.toString() + "}";
		return ret;
	}

}
