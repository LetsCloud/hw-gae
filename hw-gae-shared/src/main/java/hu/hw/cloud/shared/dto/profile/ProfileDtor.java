/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.dto.common.AccountChildDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class ProfileDtor extends AccountChildDtor {

	private String name;

	public ProfileDtor() {
	}

	public ProfileDtor(Long id, Long accountId, String Name) {
		setId(id);
		setAccount(new BaseDto(accountId));
		setName(Name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * toString
	 */
	@Override
	public String toString() {
		String ret = "ProfileDtor:{" + "name=" + name + ", " + super.toString() + "}";
		return ret;
	}

}
