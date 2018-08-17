/**
 * 
 */
package hu.hw.cloud.server.repository.ofy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.MarketGroup;
import hu.hw.cloud.server.repository.MarketGroupRepository;

/**
 * @author robi
 *
 */
public class MarketGroupRepositoryImpl extends HotelChildRepositoryImpl<MarketGroup> implements MarketGroupRepository {
	private static final Logger logger = LoggerFactory.getLogger(MarketGroupRepositoryImpl.class.getName());

	public MarketGroupRepositoryImpl() {
		super(MarketGroup.class);
		logger.info("MarketGroupRepositoryImpl");
	}
}
