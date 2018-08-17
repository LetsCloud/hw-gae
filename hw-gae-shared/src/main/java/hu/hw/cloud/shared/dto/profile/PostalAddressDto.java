/**
 * 
 */
package hu.hw.cloud.shared.dto.profile;

import hu.hw.cloud.shared.cnst.PostalAddressLabel;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class PostalAddressDto implements Dto {

	private PostalAddressLabel label;

	/**
	 * Elsődleges postacím.
	 */
	private Boolean primary;

	/**
	 * Utca, házszám.
	 */
	private String street;

	/**
	 * Város.
	 */
	private String city;

	/**
	 * Postai irányítószám.
	 */
	private String postcode;

	/**
	 * Régió, állam, tartomány
	 */
	private String region;

	/**
	 * Ország.
	 */
	private String country;

	/**
	 * Formázott cím.
	 */
	private String fullAddress;

	public PostalAddressLabel getLabel() {
		return label;
	}

	public void setLabel(PostalAddressLabel label) {
		this.label = label;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
}
