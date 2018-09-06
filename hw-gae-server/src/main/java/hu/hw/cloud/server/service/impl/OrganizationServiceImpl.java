/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.profile.Organization;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.OrganizationRepository;
import hu.hw.cloud.server.service.OrganizationService;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public class OrganizationServiceImpl extends CrudServiceImpl<Organization, OrganizationDto, OrganizationRepository>
		implements OrganizationService {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public OrganizationServiceImpl(OrganizationRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("OrganizationServiceImpl()");
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
