/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.EmailAddress;
import hu.hw.cloud.server.entity.profile.Guest;
import hu.hw.cloud.server.entity.profile.Address;
import hu.hw.cloud.server.repository.GuestRepository;
import hu.hw.cloud.server.service.DataBuilderService;
import hu.hw.cloud.server.utils.DateUtils;
import hu.hw.cloud.shared.cnst.EmailAddressLabel;
import hu.hw.cloud.shared.cnst.Gender;
import hu.hw.cloud.shared.cnst.PostalAddressLabel;

/**
 * @author CR
 *
 */
public class GuestData extends BaseData<Guest> {

	private Guest g101;
	private Guest g102;

	private Guest g111;
	private Guest g112;

	private Guest g121;
	private Guest g122;

	private Guest g131;
	private Guest g132;

	private Account account;

	public GuestData(DataBuilderService dbs, GuestRepository repo) {
		super(dbs, repo);
	}

	public void build() {
		account = dbs.getAccount();
		getG101();
		getG102();
		
		getG111();
		getG112();
		
		getG121();
		getG122();
	}

	public Guest getG101() {
		if (g101 != null)
			return g101;

		// @formatter:off
		g101 = (Guest) new Guest.Builder()
				.nationality("USA")
				.dateOfBirth(DateUtils.getDate(1992, 7, 22))
				.gender(Gender.FEMALE)
				.language("en")
				.salutation("Miss")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "selenagomez@selenagomez.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "300 Clarice St.", "Grand Prairie", "Texas", "75051", "USA"))
				.name("Selena Marie Gomez")
				.build();
		// @formatter:on

		g101 = save(g101);

		return g101;
	}

	public Guest getG102() {
		if (g102 != null)
			return g102;

		// @formatter:off
		g102 = (Guest) new Guest.Builder()
				.nationality("USA")
				.dateOfBirth(DateUtils.getDate(1994, 3, 1))
				.gender(Gender.MALE)
				.language("en")
				.salutation("Mr.")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "selenagomez@selenagomez.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "1105 Wellington Rd", "London", "ON", "N6E 1V4", "Canada"))
				.name("Justin Bieber")
				.build();
		// @formatter:on

		g102 = save(g102);

		return g102;
	}

	public Guest getG111() {
		if (g111 != null)
			return g111;

		// @formatter:off
		g111 = (Guest) new Guest.Builder()
				.nationality("USA")
				.dateOfBirth(DateUtils.getDate(1942, 6, 13))
				.gender(Gender.MALE)
				.language("en")
				.salutation("Mr")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "harrison.ford@gmail.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "300 Clarice St.", "Grand Prairie", "Texas", "75051", "USA"))
				.name("Harrison Ford")
				.build();
		// @formatter:on

		g111 = save(g111);

		return g111;
	}

	public Guest getG112() {
		if (g112 != null)
			return g112;

		// @formatter:off
		g112 = (Guest) new Guest.Builder()
				.nationality("USA")
				.dateOfBirth(DateUtils.getDate(1964, 10, 11))
				.gender(Gender.MALE)
				.language("en")
				.salutation("Ms.")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "Calista.Flockhart@gmail.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "1105 Wellington Rd", "London", "ON", "N6E 1V4", "Canada"))
				.name("Calista Flockhart")
				.build();
		// @formatter:on

		g112 = save(g112);

		return g112;
	}

	public Guest getG121() {
		if (g121 != null)
			return g121;

		// @formatter:off
		g121 = (Guest) new Guest.Builder()
				.nationality("USA")
				.dateOfBirth(DateUtils.getDate(1963, 11, 18))
				.gender(Gender.MALE)
				.language("en")
				.salutation("Mr")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "Brad.Pitt@gmail.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "300 Clarice St.", "Grand Prairie", "Texas", "75051", "USA"))
				.name("Brad Pitt")
				.build();
		// @formatter:on

		g121 = save(g121);

		return g121;
	}

	public Guest getG122() {
		if (g122 != null)
			return g122;

		// @formatter:off
		g122 = (Guest) new Guest.Builder()
				.nationality("USA")
				.dateOfBirth(DateUtils.getDate(1964, 10, 11))
				.gender(Gender.FEMALE)
				.language("en")
				.salutation("Ms.")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "Angelina.Jolie@gmail.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "1105 Wellington Rd", "London", "ON", "N6E 1V4", "Canada"))
				.name("Angelina Jolie")
				.build();
		// @formatter:on

		g122 = save(g122);

		return g122;
	}

	public Guest getG131() {
		if (g131 != null)
			return g131;

		// @formatter:off
		g131 = (Guest) new Guest.Builder()
				.nationality("ARG")
				.dateOfBirth(DateUtils.getDate(1962, 11, 5))
				.gender(Gender.MALE)
				.language("es")
				.salutation("Mr")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "Jose.Cura@gmail.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "300 Clarice St.", "Grand Prairie", "Texas", "75051", "USA"))
				.name("José Cura")
				.build();
		// @formatter:on

		g131 = save(g131);

		return g131;
	}

	public Guest getG132() {
		if (g132 != null)
			return g132;

		// @formatter:off
		g132 = (Guest) new Guest.Builder()
				.nationality("ARG")
				.dateOfBirth(DateUtils.getDate(1964, 10, 11))
				.gender(Gender.FEMALE)
				.language("es")
				.salutation("Ms.")
				.account(account)
//				.addEmailAddress(new EmailAddress(true, EmailAddressLabel.WORK, "Silvia.Ibarra@gmail.com"))
//				.addPostalAddress(
//						new Address(true, PostalAddressLabel.WORK, "1105 Wellington Rd", "London", "ON", "N6E 1V4", "Canada"))
				.name("Silvia Ibarra")
				.build();
		// @formatter:on

		g122 = save(g122);

		return g122;
	}
}
