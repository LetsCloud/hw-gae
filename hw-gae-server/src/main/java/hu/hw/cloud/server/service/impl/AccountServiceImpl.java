/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.logging.Logger;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.service.AccountService;
import hu.hw.cloud.server.service.RoleService;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.server.service.DataBuilderService;
import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

/**
 * @author CR
 *
 */
public class AccountServiceImpl implements AccountService {
	private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class.getName());

	private AccountRepository accountRepository;

	private AppUserService appUserService;

	private RoleService roleService;

	private DataBuilderService dataBuilderService;

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void setAppUserService(AppUserService userService) {
		this.appUserService = userService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setDataBuilderService(DataBuilderService dataBuilderService) {
		this.dataBuilderService = dataBuilderService;
	}

	@Override
	public Boolean sameAccountIds(String webSafeKey, Long id) {
		return accountRepository.getWebSafeKeyById(id).equals(webSafeKey);
	}

	@Override
	public AppUser register(RegisterDto registerDto) throws EntityValidationException, UniqueIndexConflictException {
		LOGGER.info("register->accountName=" + registerDto.getAccountName());

		Account account = accountRepository.save(new Account(registerDto));
		if (account == null) {
			LOGGER.info("account == null");
			throw new UniqueIndexConflictException("Null Account entity", null);
		}
		LOGGER.info("setAccountId()");
		registerDto.setAccountId(account.getId());

//		LOGGER.info("createDefaultRoles");
//		roleService.createDefaultRoles(account);

		LOGGER.info("register->before createAdminUser()");
		AppUser appUser = appUserService.createAdminUser(registerDto);

		LOGGER.info("register->after createAdminUser()");
//		dataBuilderService.buildTestData(account.getWebSafeKey());
		return appUser;
	}

}
