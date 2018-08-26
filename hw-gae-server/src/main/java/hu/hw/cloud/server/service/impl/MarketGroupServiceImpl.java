/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.MarketGroup;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.MarketGroupRepository;
import hu.hw.cloud.server.service.MarketGroupService;
import hu.hw.cloud.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class MarketGroupServiceImpl extends HotelChildServiceImpl<MarketGroup, MarketGroupDto, MarketGroupRepository>
		implements MarketGroupService {
	private static final Logger logger = LoggerFactory.getLogger(MarketGroupServiceImpl.class.getName());

	public MarketGroupServiceImpl(MarketGroupRepository roomTypeRepository, AccountRepository accountRepository,
			HotelRepository hotelRepository) {
		super(roomTypeRepository, accountRepository, hotelRepository);
		logger.info("RoomTypeServiceImpl");
	}
}
