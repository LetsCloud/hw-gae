/**
 * 
 */
package hu.hw.cloud.server.entity.hk;

import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.hotel.HotelChild;

/**
 * @author CR
 *
 */
@Entity
public class CleanType extends HotelChild {

	private String code;

	private String description;

	private Integer time;

	public CleanType() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
}
