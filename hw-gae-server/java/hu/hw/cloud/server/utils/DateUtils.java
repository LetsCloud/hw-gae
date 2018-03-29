/**
 * 
 */
package hu.hw.cloud.server.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.service.impl.RoomServiceImpl;

/**
 * @author CR
 *
 */
public class DateUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class.getName());

	public static Date getDate(int year, int month, int dayOfMonth) {
		return getDate(year, month, dayOfMonth, 0, 0, 0);
	}

	public static Date getDate(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {
		Calendar calendar = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second); 
		return calendar.getTime();
	}

	public static Date addDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}

	public static Date addMonths(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, days);
		return c.getTime();
	}

	public static Date addYears(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, days);
		return c.getTime();
	}

}
