/**
 * 
 */
package hu.hw.cloud.server.cubegen;

import java.util.Date;

/**
 * @author CR
 *
 */
public class TimeCell {

	private Date startDate;

	private Integer count;

	public TimeCell(Date startDate, Integer count) {
		this.startDate = startDate;
		this.count = count;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer decreaseCount() {
		return count--;
	}
}
