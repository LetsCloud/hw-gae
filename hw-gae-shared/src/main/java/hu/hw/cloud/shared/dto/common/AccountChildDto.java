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

	private AccountDto accountDto;

	private String accountWebSafeKey;

	public AccountDto getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}

	public String getAccountWebSafeKey() {
		return accountWebSafeKey;
	}

	public void setAccountWebSafeKey(String accountWebSafeKey) {
		this.accountWebSafeKey = accountWebSafeKey;
	}

	/*
	 * toString 
	 */
	@Override
	public String toString() {
		String ret = "AccountChildDto:{" + super.toString() + ", accountDto=" + accountDto + ", accountWebSafeKey="
				+ accountWebSafeKey + "}";
		return ret;
	}

}
