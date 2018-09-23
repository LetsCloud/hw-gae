/**
 * 
 */
package hu.hw.cloud.shared.dto.common;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class AccountChildDtor extends BaseDto {

	private BaseDto account;

	public BaseDto getAccount() {
		return account;
	}

	public void setAccount(BaseDto account) {
		this.account = account;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "AccountChildDtor:{" + "account=" + account + ", " + super.toString() + "}";
		return ret;
	}

}
