/**
 * 
 */
package hu.hw.cloud.server.entity.reservation;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.profile.Organization;
import hu.hw.cloud.shared.cnst.ProfileType;

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
	public static Organization getCustomerByType(List<ProfileLink> links, final ProfileType type) {
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

	private Ref<Organization> customerRef;

	public ProfileType getType() {
		return type;
	}

	public void setType(ProfileType type) {
		this.type = type;
	}

	public Ref<Organization> getCustomerRef() {
		return customerRef;
	}

	public void setCustomerRef(Ref<Organization> customerRef) {
		this.customerRef = customerRef;
	}

	public Organization getCustomer() {
		return customerRef.get();
	}

	public void setCustomer(Organization customer) {
		this.customerRef = Ref.create(customer);
	}

}
