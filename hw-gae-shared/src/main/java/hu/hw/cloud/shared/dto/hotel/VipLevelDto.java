/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class VipLevelDto extends HotelChildDto {

	private String code;

	private String description;

	private String color;

	private Integer sequence;

	private Boolean active;

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		String ret = "VipLevelDto:{" + super.toString() + ", code=" + code + ", description=" + description;
		return ret;
	}

}
