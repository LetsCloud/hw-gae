/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.server.entity.BaseEntity;
import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.HotelChildRepository;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.service.HotelChildService;

/**
 * @author robi
 *
 */
public abstract class HotelChildServiceImpl<T extends BaseEntity, R extends HotelChildRepository<T>>
		extends CrudServiceImpl<T, R> implements HotelChildService<T> {

	private final AccountRepository accountRepository;
	private final HotelRepository hotelRepository;

	public HotelChildServiceImpl(R repository, AccountRepository accountRepository, HotelRepository hotelRepository) {
		super(repository);
		this.accountRepository = accountRepository;
		this.hotelRepository = hotelRepository;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		Account account = accountRepository.findById(accountId);
		return getHotels(account);
	}

	@Override
	protected List<Object> getParents(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);
		return getHotels(account);
	}

	private List<Object> getHotels(Account account) {
		List<Object> parents = new ArrayList<Object>();
		List<Hotel> hotels = hotelRepository.getAll(account);
		for (Hotel hotel : hotels)
			parents.add(hotel);
		return parents;
	}

}
