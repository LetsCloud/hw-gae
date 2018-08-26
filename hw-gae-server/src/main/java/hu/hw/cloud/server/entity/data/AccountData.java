/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.profile.Address;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.service.DataBuilderService;
import hu.hw.cloud.shared.cnst.PostalAddressLabel;

/**
 * @author CR
 *
 */
public class AccountData extends BaseData<Account> {

	public AccountData(DataBuilderService dbs, AccountRepository repo) {
		super(dbs, repo);
	}

	private Account account1;

	public void build() {
		getAccount1();
	}

	public Account getAccount1() {
		if (account1 != null)
			return account1;

		account1 = new Account();
		account1.setName("Test Hotel Chain 1");
//		Address postalAddress = new Address(true, PostalAddressLabel.WORK, "Street", "City", "PostCode",
//				"Region", "Country");
//		account1.setAddress(postalAddress);
		
		account1 = save(account1);

		return account1;
	}
}
