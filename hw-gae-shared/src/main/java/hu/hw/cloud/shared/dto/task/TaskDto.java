/**
 * 
 */
package hu.hw.cloud.shared.dto.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.hw.cloud.shared.cnst.TaskType;
import hu.hw.cloud.shared.dto.common.AccountChildDto;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class TaskDto extends AccountChildDto {

	private TaskType type;

	private String title;

	private AppUserDto reporter;

	private List<AppUserDto> assignies = new ArrayList<AppUserDto>();

	private Date created;

	private Date updated;

	private List<TaskAttrDto> attributes = new ArrayList<TaskAttrDto>();

	private String description;

	public TaskDto() {
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AppUserDto getReporter() {
		return reporter;
	}

	public void setReporter(AppUserDto reporter) {
		this.reporter = reporter;
	}

	public List<AppUserDto> getAssignies() {
		return assignies;
	}

	public void setAssignies(List<AppUserDto> assignies) {
		this.assignies = assignies;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public List<TaskAttrDto> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<TaskAttrDto> attributes) {
		this.attributes = attributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
