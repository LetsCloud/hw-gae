/**
 * 
 */
package hu.hw.cloud.server.entity.reservation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.profile.Customer;
import hu.hw.cloud.shared.cnst.ProfileType;
import hu.hw.cloud.shared.dto.reservation.ProfileLinkDto;

/**
 * @author CR
 *
 */
public class ProfileLink {

	/**
	 * 
	 * @param links
	 * @param type
	 * @return
	 */
	public static Customer getCustomerByType(List<ProfileLink> links, final ProfileType type) {
		Predicate<ProfileLink> condition = new Predicate<ProfileLink>() {
			public boolean evaluate(ProfileLink object) {
				return (object.getType().equals(type));
			}
		};
		List<ProfileLink> result = (List<ProfileLink>) CollectionUtils.select(links, condition);

		if (result.isEmpty())
			return null;

		return result.get(0).getCustomer();
	}

	private ProfileType type;

	private Ref<Customer> customerRef;

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public Ref<Customer> getCustomerRef() {
		return customerRef;
	}

	public void setCustomerRef(Ref<Customer> customerRef) {
		this.customerRef = customerRef;
	}

	public Customer getCustomer() {
		return customerRef.get();
	}

	public void setCustomer(Customer customer) {
		this.customerRef = Ref.create(customer);
	}

	public ProfileLinkDto createDto() {
		ProfileLinkDto dto = new ProfileLinkDto();
		if (getCustomer() != null)
			dto.setCustomerDto(Customer.createDto(getCustomer()));
		dto.setType(getType());
		return dto;
	}

	public static List<ProfileLinkDto> createDtos(List<ProfileLink> entities) {
		List<ProfileLinkDto> dtos = new ArrayList<ProfileLinkDto>();
		for (ProfileLink entity : entities) {
			dtos.add(entity.createDto());
		}
		return dtos;
	}

}
