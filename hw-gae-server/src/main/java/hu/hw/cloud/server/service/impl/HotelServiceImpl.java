/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.service.HotelService;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
public class HotelServiceImpl extends CrudServiceImpl<Hotel, HotelDto, HotelRepository> implements HotelService {
	private static final Logger logger = LoggerFactory.getLogger(HotelServiceImpl.class.getName());

	private final AccountRepository accountRepository;

	public HotelServiceImpl(HotelRepository repository, AccountRepository accountRepository) {
		super(repository);
		logger.info("HotelServiceImpl");
		this.accountRepository = accountRepository;
	}

	@Override
	protected Hotel createEntity(HotelDto dto) {
		return new Hotel(dto);
	}

	@Override
	protected Hotel updateEntity(Hotel entity, HotelDto dto) {
		entity.updEntityWithDto(dto);
		return entity;
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
	protected Hotel updateEntity(Hotel oldEntity, Hotel newEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
