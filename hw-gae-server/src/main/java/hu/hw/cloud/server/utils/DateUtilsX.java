/**
 * 
 */
package hu.hw.cloud.server.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 * @author CR
 *
 */
public class DateUtilsX {

	public static int daysBetween(Date date1, Date date2) {
		DateTime dateTime1 = new DateTime(date1);
		DateTime dateTime2 = new DateTime(date2);
		return Days.daysBetween(dateTime2, dateTime1).getDays();
	}
}
