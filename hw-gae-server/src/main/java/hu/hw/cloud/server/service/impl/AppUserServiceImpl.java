package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import hu.hw.cloud.server.entity.VerificationToken;
import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.entity.common.FcmToken;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.AppUserRepository;
import hu.hw.cloud.server.security.LoggedInChecker;
import hu.hw.cloud.server.service.AppUserService;
import hu.hw.cloud.shared.dto.RegisterDto;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.exception.EntityValidationException;
import hu.hw.cloud.shared.exception.UniqueIndexConflictException;

public class AppUserServiceImpl extends CrudServiceImpl<AppUser, AppUserDto, AppUserRepository>
		implements AppUserService {
	private static final Logger LOGGER = Logger.getLogger(AppUserServiceImpl.class.getName());

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
		// LOGGER.info("getCurrentUser()");
		return loggedInChecker.getLoggedInUser();
	}

	@Override
	public Boolean isCurrentUserLoggedIn() {
		LOGGER.info("isCurrentUserLoggedIn()");
		return loggedInChecker.getLoggedInUser() != null;
	}

	@Override
	public void createVerificationToken(AppUser user, String token) throws Throwable {
		VerificationToken myToken = new VerificationToken(token);
		List<VerificationToken> tokens = new ArrayList<VerificationToken>();
		tokens.add(myToken);
		user.setVerificationTokens(tokens);
		appUserRepository.save(user);
	}

	@Override
	protected AppUser createEntity(AppUserDto dto) {
		// LOGGER.info("createEntity->dto=" + dto);
		return new AppUser(dto);
	}

	@Override
	protected AppUser updateEntity(AppUser entity, AppUserDto dto) {
		// LOGGER.info("updateEntity");
		entity.update(dto);
		return entity;
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
	public AppUser createAdminUser(RegisterDto registerDto)
			throws EntityValidationException, UniqueIndexConflictException {
		// LOGGER.info("createAdminUser->registerDto.getAccountId()=" +
		// registerDto.getAccountId());
		Account account = accountRepository.findById(registerDto.getAccountId());

		AppUser appUser = new AppUser(registerDto);
		appUser.setAccount(account);
		appUser.setAdmin(true);
		// LOGGER.info("createAdminUser->before appUserRepository.save()");
		appUser = appUserRepository.save(appUser);
		// LOGGER.info("createAdminUser->after appUserRepository.save()");
		return appUser;
	}

	@Override
	protected AppUser updateEntity(AppUser oldEntity, AppUser newEntity) {
		// TODO Auto-generated method stub
		return null;
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
		List<FcmToken> tokens = currentUser.getFcmTokens();

		if (FcmToken.getToken(tokens, iidToken) == null) {
			tokens.add(new FcmToken(iidToken, userAgent));
			currentUser.setFcmTokens(tokens);
			update(currentUser);
		}
	}
}
