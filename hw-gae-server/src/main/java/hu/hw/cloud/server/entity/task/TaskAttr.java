/**
 * 
 */
package hu.hw.cloud.server.entity.task;

import hu.hw.cloud.shared.cnst.TaskAttrType;

/**
 * @author robi
 *
 */
public class TaskAttr {
	
	TaskAttrType type;
	
	String value;

	public TaskAttr() {
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
