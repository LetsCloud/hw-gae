/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.Ref;

import hu.hw.cloud.server.entity.profile.Profile;
import hu.hw.cloud.server.entity.profile.ProfileLink;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.ProfileLinkRepository;
import hu.hw.cloud.server.service.ProfileLinkService;

/**
 * @author robi
 *
 */
public class ProfileLinkServiceImpl extends CrudServiceImpl<ProfileLink, ProfileLinkRepository>
		implements ProfileLinkService {
	private static final Logger logger = LoggerFactory.getLogger(ProfileLinkServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public ProfileLinkServiceImpl(ProfileLinkRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("ProfileLinkServiceImpl");
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

	@Override
	public List<ProfileLink> getByProfile(Profile profile, String field) {
		List<ProfileLink> result = new ArrayList<ProfileLink>();
		result.addAll(getOnePartByProfile(profile.getAccount().getWebSafeKey(), field, Ref.create(profile)));
		return result;
	}

	private List<ProfileLink> getOnePartByProfile(String parentWebSafeKey, String key, Object value) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put(key, value);
		return this.getChildrenByFilters(parentWebSafeKey, filters);
	}
}
