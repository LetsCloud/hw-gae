/**
 * 
 */
package hu.hw.cloud.server.cubegen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
public class TimeGrid {
	private static final Logger LOGGER = LoggerFactory.getLogger(TimeGrid.class.getName());

	private Random random;
	private List<TimeCell> cells;

	public TimeGrid(Date start, int numOfBookingPeriod, int daysOfBookingPeriod) {
		cells = new ArrayList<TimeCell>();
		for (int i = 0; i < numOfBookingPeriod; i++) {
//			LOGGER.info("TimeGrid()->for(i)->" + i + ", start=" + start);
			cells.add(new TimeCell(start, 10));
			start = DateUtils.addDays(start, daysOfBookingPeriod);
		}
		random = new Random((long) cells.size());
	}

	public TimeCell getRandomCell() {
//		LOGGER.info("getRandomCell()");

		int index = random.nextInt(cells.size());
		TimeCell cell = cells.get(index);

		int startIndex = index;
		// Ha már elfogytak a hetet megkeressük a következőt
		while (cell.getCount() == 0) {
//			LOGGER.info("getRandomCell()->while()->index=" + index);
			index++;

			if (index == startIndex) {
//				LOGGER.info("getRandomCell()->while()->(index == startIndex)->index=" + index);
				return null;
			}

			if (index >= cells.size()) {
//				LOGGER.info("getRandomCell()->while()->(index >= cells.size())->index=" + index);
				index = 0;
			}

			cell = cells.get(index);
		}

		if (cell.getCount() == 0) {
//			LOGGER.info("getRandomCell()->while()->if (cell.getCount() == 0)->index=" + index);
			return null;
		}

		cell.decreaseCount();
		return cell;
	}
}
