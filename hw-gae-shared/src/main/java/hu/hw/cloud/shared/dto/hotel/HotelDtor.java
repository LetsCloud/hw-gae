/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import hu.hw.cloud.shared.dto.common.AccountChildDtor;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class HotelDtor extends AccountChildDtor {

	/**
	 * Egyedi azonosító
	 */
	private String code;

	/**
	 * Név
	 */
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HotelDtor:{code=" + code + ", name=" + name + ", " + super.toString() + "}";
	}
}
