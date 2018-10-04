/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.profile.ProfileProperty;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.ProfilePropertyRepository;
import hu.hw.cloud.server.service.ProfilePropertyService;

/**
 * @author robi
 *
 */
public class ProfilePropertyServiceImpl extends CrudServiceImpl<ProfileProperty, ProfilePropertyRepository>
		implements ProfilePropertyService {
	private static final Logger logger = LoggerFactory.getLogger(ProfilePropertyServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public ProfilePropertyServiceImpl(ProfilePropertyRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("ProfilePropertyServiceImpl");
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
