/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.Role;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.RoleRepository;
import hu.hw.cloud.server.service.MessageSourceHandler;
import hu.hw.cloud.server.service.RoleService;
import hu.hw.cloud.shared.cnst.Permissions;

/**
 * @author CR
 *
 */
//@Service
public class RoleServiceImpl extends CrudServiceImpl<Role, RoleRepository> implements RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUserServiceImpl.class);

	private static final String CODE = "Code";
	private static final String TITLE = "Title";
	private static final String DESCRIPTION = "Description";

	private final AccountRepository accountRepository;

	private final RoleRepository roleRepository;

	private final MessageSourceHandler messageSourceHandler;

	//@Autowired
	public RoleServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository,
			MessageSourceHandler messageSourceHandler) {
		super(roleRepository);
		LOGGER.info("RoleServiceImpl");
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
		this.messageSourceHandler = messageSourceHandler;
	}

	@Override
	public void createDefaultRoles(Account account) {
		Locale locale = new Locale.Builder().setLanguage("hu").setRegion("HU").build();

		for (Entry<String, String[]> entry : Permissions.DEFAULT_ROLE_PERMSSIONS.entrySet()) {
			createDefaultRole(entry, account, locale);
		}
	}

	/**
	 * 
	 * @param entry
	 * @param account
	 * @param locale
	 */
	private void createDefaultRole(Entry<String, String[]> entry, Account account, Locale locale) {
		Role role = new Role();
		role.setAccount(account);
		role.setCode(messageSourceHandler.getMessage(entry.getKey() + CODE, locale));
		role.setTitle(messageSourceHandler.getMessage(entry.getKey() + TITLE, locale));
		role.setDescription(messageSourceHandler.getMessage(entry.getKey() + DESCRIPTION, locale));
		role.setPermissions(Arrays.asList(entry.getValue()));

		try {
			roleRepository.save(role);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	public List<Role> getAll(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
