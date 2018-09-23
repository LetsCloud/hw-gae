/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.profile.Profile;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.ProfileRepository;
import hu.hw.cloud.server.service.ProfileService;

/**
 * @author robi
 *
 */
public class ProfileServiceImpl extends CrudServiceImpl<Profile, ProfileRepository> implements ProfileService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public ProfileServiceImpl(ProfileRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("ProfileServiceImpl()");
		this.accountRepository = accountRepository;
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
}
