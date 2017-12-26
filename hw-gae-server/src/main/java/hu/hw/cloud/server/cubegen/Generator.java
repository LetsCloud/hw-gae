/**
 * 
 */
package hu.hw.cloud.server.cubegen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.TestBooking;
import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
public class Generator {
	private static final Logger LOGGER = LoggerFactory.getLogger(Generator.class.getName());

	private static final int NUMBER_OF_ROOMS = 30;
	private static final int BOOKING_PERCENT_PER_PERIOD = 10;
	private static final int PREOPENING_MONTHS = 3;
	private static final int NUM_OF_BOOKING_PERIODS = 52;
	private static final int RESERVATION_PERIOD_DAYS = 365;
	private static final int BOOKING_STEP_DAYS = 7;
	private static Random random = new Random();
	private Date openingDate;
	private Date preOpeningDate;
	private TimeGrid grid;

	public Generator() {
		DateTime dt = new DateTime(2016, 10, 31, 0, 0, 0, 0);
		openingDate = dt.toDate();
		preOpeningDate = DateUtils.addMonths(openingDate, -PREOPENING_MONTHS);
		grid = new TimeGrid(openingDate, NUM_OF_BOOKING_PERIODS, BOOKING_STEP_DAYS);
	}

	public List<TestBooking> start() {
		List<TestBooking> bookingCases = new ArrayList<TestBooking>();

		Date bookingDate = preOpeningDate;
		for (int i = 0; i < RESERVATION_PERIOD_DAYS; i++) {
			LOGGER.info("start()->for(i)->" + i + ", bookingDate=" + bookingDate);
			TimeCell cell = grid.getRandomCell();

			int nights = random.nextInt(BOOKING_STEP_DAYS-1)+1;
//			LOGGER.info("start()->for(i)->" + i + ", nights=" + nights);

			Date arrival = DateUtils.addDays(cell.getStartDate(), random.nextInt(BOOKING_STEP_DAYS - nights));
			int numOfBookings = random.nextInt(NUMBER_OF_ROOMS * BOOKING_PERCENT_PER_PERIOD / 100);
//			LOGGER.info("start()->for(i)->" + i + ", numOfBookings=" + numOfBookings);

			bookingCases.addAll(createBookings(bookingDate, arrival, nights, numOfBookings));
			bookingDate = DateUtils.addDays(bookingDate, 1);
		}
		return bookingCases;
	}

	private List<TestBooking> createBookings(Date bookingDate, Date arrival, int nights, int numOfBookings) {
//		LOGGER.info("createBookings()");
		List<TestBooking> bookingCases = new ArrayList<TestBooking>();
		for (int i = 0; i < numOfBookings; i++) {
			LOGGER.info("createBookings()->for(i)=" + i + ", bookingDate=" + bookingDate + ", arrival=" + arrival
					+ ", nights=" + nights);
			bookingCases.add(new TestBooking(bookingDate, arrival, nights));
		}
		return bookingCases;
	}
}
