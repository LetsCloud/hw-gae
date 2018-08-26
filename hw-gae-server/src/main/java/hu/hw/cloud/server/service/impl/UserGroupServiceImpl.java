/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.UserGroup;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.UserGroupRepository;
import hu.hw.cloud.server.service.UserGroupService;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupServiceImpl extends CrudServiceImpl<UserGroup, UserGroupDto, UserGroupRepository>
implements UserGroupService {
	private static final Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class.getName());

	private final UserGroupRepository userGroupRepository;
	private final AccountRepository accountRepository;

	UserGroupServiceImpl(UserGroupRepository userGroupRepository, AccountRepository accountRepository) {
		super(userGroupRepository);
		this.userGroupRepository = userGroupRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public List<UserGroup> getAll(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);

		if (account == null)
			return null;

		List<UserGroup> result = userGroupRepository.getByAccount(account);
		for (UserGroup group : result) {
			group.getMembers();
		}
		return result;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
