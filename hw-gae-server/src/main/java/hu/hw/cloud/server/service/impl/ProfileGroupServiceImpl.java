/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.profile.ProfileGroup;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.ProfileGroupRepository;
import hu.hw.cloud.server.service.ProfileGroupService;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileGroupServiceImpl extends CrudServiceImpl<ProfileGroup, ProfileGroupDto, ProfileGroupRepository>
		implements ProfileGroupService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileGroupServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public ProfileGroupServiceImpl(ProfileGroupRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("HotelServiceImpl");
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
