/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.service.HotelService;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
//@Service
public class HotelServiceImpl extends CrudServiceImpl<Hotel, HotelDto, HotelRepository> implements HotelService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	private final HotelRepository hotelRepository;

//	@Autowired
	public HotelServiceImpl(AccountRepository accountRepository, HotelRepository hotelRepository) {
		super(hotelRepository);
		LOGGER.info("HotelServiceImpl");
		this.hotelRepository = hotelRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	protected Hotel createEntity(HotelDto dto) {
		return new Hotel(dto);
	}

	@Override
	protected Hotel updateEntity(Hotel entity, HotelDto dto) {
		entity.update(dto);
		return entity;
	}

	@Override
	protected List<Object> getParents(Long accountId) {
		List<Object> parents = new ArrayList<Object>();
		parents.add(accountRepository.findById(accountId));
		return parents;
	}

	@Override
	public List<Hotel> getAll(String accountWebSafeKey) {
		Account account = accountRepository.findByWebSafeKey(accountWebSafeKey);

		if (account == null)
			return null;

		// TODO Auto-generated method stub
		return hotelRepository.getAll(account);
	}

	@Override
	protected Hotel updateEntity(Hotel oldEntity, Hotel newEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
