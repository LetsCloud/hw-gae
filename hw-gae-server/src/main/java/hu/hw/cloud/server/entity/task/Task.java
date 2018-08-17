/**
 * 
 */
package hu.hw.cloud.server.entity.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import hu.hw.cloud.server.entity.common.AccountChild;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.shared.cnst.TaskType;

/**
 * @author robi
 *
 */
@Entity
public class Task extends AccountChild {

	private TaskType type;

	private String title;

	private Ref<AppUser> reporter;

	private List<Ref<AppUser>> assignies = new ArrayList<Ref<AppUser>>();

	private Date created;

	private Date updated;

	private List<TaskAttr> attributes = new ArrayList<TaskAttr>();

	private String description;

	public Task() {
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

	public AppUser getReporter() {
		return reporter.get();
	}

	public void setReporter(AppUser reporter) {
		this.reporter = Ref.create(reporter);
	}

	public List<AppUser> getAssignies() {
		return AppUser.ref2EntityList(assignies);
	}

	public void setAssignies(List<AppUser> assignies) {
		this.assignies = AppUser.entity2RefList(assignies);
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

	public List<TaskAttr> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<TaskAttr> attributes) {
		this.attributes = attributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
