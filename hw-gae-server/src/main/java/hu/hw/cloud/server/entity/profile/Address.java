/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.shared.cnst.PostalAddressLabel;

/**
 * Postacím.
 * 
 * @author CR
 *
 */
public class Address {
	private static final Logger logger = LoggerFactory.getLogger(Address.class.getName());

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
	private String formattedAddress;

	public Address() {
		logger.info("Address()");
	}

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
		logger.info("setPrimary()->" + primary);
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

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	/**
	 * 
	 * @param list
	 * @param label
	 * @return
	 */
	public static Address findPostalAddressByLabel(List<Address> list, final PostalAddressLabel label) {
		Predicate<Address> condition = new Predicate<Address>() {
			public boolean evaluate(Address object) {
				return (object.getLabel().equals(label));
			}
		};
		List<Address> result = (List<Address>) CollectionUtils.select(list, condition);

		if (result.isEmpty())
			return null;

		return result.get(0);
	}
}
