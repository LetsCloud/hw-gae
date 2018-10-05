package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.VerificationToken;
import hu.hw.cloud.server.entity.chat.FcmToken;
import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.AppUserRepository;
import hu.hw.cloud.server.security.LoggedInChecker;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

public class AppUserServiceImpl extends CrudServiceImpl<AppUser, AppUserRepository>
		implements AppUserService {
	private static final Logger logger = LoggerFactory.getLogger(AppUserServiceImpl.class.getName());

	private final LoggedInChecker loggedInChecker;
	private final AccountRepository accountRepository;
	private final AppUserRepository appUserRepository;

	AppUserServiceImpl(LoggedInChecker loggedInChecker, AccountRepository accountRepository,
			AppUserRepository appUserRepository) {
		super(appUserRepository);
		this.loggedInChecker = loggedInChecker;
		this.accountRepository = accountRepository;
		this.appUserRepository = appUserRepository;
	}

	@Override
	public AppUser getUserByUsername(String username, Long accountId) {
		Account account = accountRepository.findById(accountId);

		if (account == null)
			return null;

		AppUser user = this.appUserRepository.findByUsername(account, username);
		return user;
	}

	@Override
	public List<String> getPermissions(String username) {
		List<String> ret = new ArrayList<String>();
		ret.add("USER");
		return ret;
	}

	@Override
	public AppUser getCurrentUser() {
		logger.info("getCurrentUser()");
		return loggedInChecker.getLoggedInUser();
	}

	@Override
	public Boolean isCurrentUserLoggedIn() {
		logger.info("isCurrentUserLoggedIn()");
		return loggedInChecker.getLoggedInUser() != null;
	}

	@Override
	public void createVerificationToken(AppUser user, String token) throws Throwable {
		logger.info("createVerificationToken()->user="+user);
		VerificationToken myToken = new VerificationToken(token);
		List<VerificationToken> tokens = new ArrayList<VerificationToken>();
		tokens.add(myToken);
		user.setVerificationTokens(tokens);
		appUserRepository.save(user);
	}

	@Override
	public List<AppUser> getAll(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);

		if (account == null)
			return null;

		return appUserRepository.getByAccount(account);
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findByWebSafeKey(accountWebSafeKey));
		return parents;
	}

	@Override
	public AppUser createAdminUser(RegisterDto registerDto)
			throws EntityValidationException, UniqueIndexConflictException {
		Account account = accountRepository.findById(registerDto.getAccountId());
		logger.info("createAdminUser()->account="+account);

		AppUser appUser = new AppUser(registerDto);
		appUser.setAccount(account);
		appUser.setAdmin(true);
		appUser = appUserRepository.save(appUser);
		logger.info("createAdminUser()->appUser="+appUser);

		return appUser;
	}

	@Override
	public Boolean activate(String token) throws EntityValidationException, UniqueIndexConflictException {
		AppUser user = this.appUserRepository.findByToken(token);
		user.setEnabled(true);
		this.appUserRepository.save(user);
		return true;
	}

	@Override
	public void fcmSubscribe(String iidToken, String userAgent) throws Throwable {
		AppUser currentUser = loggedInChecker.getLoggedInUser();
		currentUser = appUserRepository.findByWebSafeKey(currentUser.getWebSafeKey());
		List<FcmToken> tokens = currentUser.getFcmTokens();

		if (FcmToken.getToken(tokens, iidToken) == null) {
			tokens.add(new FcmToken(iidToken, userAgent));
			currentUser.setFcmTokens(tokens);
			update(currentUser);
		}
	}
}
