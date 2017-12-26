/**
 * 
 */
package hu.hw.cloud.shared.dto.hotel;

import java.util.List;

import hu.hw.cloud.shared.cnst.SalesType;

/**
 * @author CR
 *
 */
@SuppressWarnings("serial")
public class RoomTypeDto extends HotelChildDto {

	private String code;

	private String name;

	private String description;

	private SalesType salesType;

	private List<String> componentIds;

	private Boolean active;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SalesType getSalesType() {
		return salesType;
	}

	public void setSalesType(SalesType salesType) {
		this.salesType = salesType;
	}

	public List<String> getComponentIds() {
		return componentIds;
	}

	public void setComponentIds(List<String> componentIds) {
		this.componentIds = componentIds;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		String ret = "RoomTypeDto:{" + super.toString() + ", code=" + code + ", name=" + name + ", description="
				+ description + "}";
		return ret;
	}

}
