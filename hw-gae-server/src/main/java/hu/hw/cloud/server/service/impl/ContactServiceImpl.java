/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.profile.Contact;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.ContactRepository;
import hu.hw.cloud.server.service.ContactService;

/**
 * @author robi
 *
 */
public class ContactServiceImpl extends CrudServiceImpl<Contact, ContactRepository>
		implements ContactService {
	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public ContactServiceImpl(ContactRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("ContactServiceImpl()");
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
