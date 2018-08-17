/**
 * 
 */
package hu.hw.cloud.server.entity.profile;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import hu.hw.cloud.shared.cnst.PostalAddressLabel;
import hu.hw.cloud.shared.dto.profile.PostalAddressDto;

/**
 * Postacím.
 * 
 * @author CR
 *
 */
public class PostalAddress {

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

	public PostalAddress() {
	}

	public PostalAddress(PostalAddressDto dto) {
		this();
		update(dto);
	}

	public void update(PostalAddressDto dto) {
		if (dto.getCity() != null)
			this.setCity(dto.getCity());

		if (dto.getCountry() != null)
			this.setCountry(dto.getCountry());

		if (dto.getFullAddress() != null)
			this.setFormattedAddress(dto.getFullAddress());

		if (dto.getLabel() != null)
			this.setLabel(dto.getLabel());

		if (dto.getPostcode() != null)
			this.setPostcode(dto.getPostcode());

		if (dto.getPrimary() != null)
			this.setPrimary(dto.getPrimary());

		if (dto.getRegion() != null)
			this.setRegion(dto.getRegion());

		if (dto.getStreet() != null)
			this.setStreet(dto.getStreet());
	}

	public PostalAddress(Boolean primary, PostalAddressLabel label, String street, String city, String postcode,
			String region, String country) {
		this.primary = primary;
		this.label = label;
		this.street = street;
		this.city = city;
		this.postcode = postcode;
		this.region = region;
		this.country = country;
		this.formattedAddress = country + postcode + " " + city + "," + street;
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

	public static PostalAddressDto createDto(PostalAddress entity) {
		PostalAddressDto dto = new PostalAddressDto();
		dto = entity.updateDto(dto);
		return dto;
	}

	public PostalAddressDto updateDto(PostalAddressDto dto) {
		dto.setCity(getCity());
		dto.setCountry(getCountry());
		dto.setFullAddress(getFormattedAddress());
		dto.setLabel(getLabel());
		dto.setPostcode(getPostcode());
		dto.setPrimary(getPrimary());
		dto.setRegion(getRegion());
		dto.setStreet(getStreet());
		return dto;
	}

	public static List<PostalAddressDto> createDtos(List<PostalAddress> enities) {
		List<PostalAddressDto> dtos = new ArrayList<PostalAddressDto>();
		for (PostalAddress entity : enities) {
			dtos.add(PostalAddress.createDto(entity));
		}
		return dtos;
	}

	/**
	 * 
	 * @param list
	 * @param label
	 * @return
	 */
	public static PostalAddress findPostalAddressByLabel(List<PostalAddress> list, final PostalAddressLabel label) {
		Predicate<PostalAddress> condition = new Predicate<PostalAddress>() {
			public boolean evaluate(PostalAddress object) {
				return (object.getLabel().equals(label));
			}
		};
		List<PostalAddress> result = (List<PostalAddress>) CollectionUtils.select(list, condition);

		if (result.isEmpty())
			return null;

		return result.get(0);
	}
}
