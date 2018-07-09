/**
 * 
 */
package hu.hw.cloud.shared.dto.task;

import hu.hw.cloud.shared.cnst.TaskAttrType;
import hu.hw.cloud.shared.dto.Dto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskAttrDto implements Dto {
	
	private TaskAttrType type;
	
	private String value;

	public TaskAttrDto() {}

	public TaskAttrDto(TaskAttrType type, String value) {
		this();
		this.type = type;
		this.value = value;
	}
	
	public TaskAttrType getType() {
		return type;
	}

	public void setType(TaskAttrType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
