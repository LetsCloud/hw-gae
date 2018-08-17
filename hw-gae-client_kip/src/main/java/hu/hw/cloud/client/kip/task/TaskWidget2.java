/**
 * 
 */
package hu.hw.cloud.client.kip.task;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Provider;

import hu.hw.cloud.client.kip.resources.KipGssResources;
import hu.hw.cloud.client.kip.task.TaskActionEvent.TaskAction;
import hu.hw.cloud.client.kip.task.TaskActionEvent.TaskActionEventHandler;
import hu.hw.cloud.client.kip.task.editor.TaskEditorView;
import hu.hw.cloud.client.kip.ui.TaskCollapsibleBody;
import hu.hw.cloud.client.kip.ui.TaskCollapsibleHeader;
import hu.hw.cloud.client.kip.ui.TaskCollapsibleItem;
import hu.hw.cloud.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public class TaskWidget2 extends TaskCollapsibleItem implements TaskActionEventHandler {
	private static Logger logger = Logger.getLogger(TaskWidget2.class.getName());

	private TaskDisplay taskDisplay = new TaskDisplay();

	@Inject
	Provider<TaskEditorView> taskEditorProvider;
	private TaskEditorView taskEditor;
	
	private TaskDto task;

	@Inject
	TaskWidget2(KipGssResources res) {
		super();
		add(new TaskCollapsibleHeader(res));
		add(new TaskCollapsibleBody(res));

	}

	public void setTask(TaskDto task) {
		this.task = task;
		taskEditor = taskEditorProvider.get();
		taskEditor.setTask(task);
		
		taskDisplay = new TaskDisplay(task);
//		taskDisplay.setTask(task);
		
		taskDisplay.getEditLink().addClickHandler(e -> {
			logger.info("TaskWidget2()->openClick");
			this.setActive(true);
			// body.setVisible(true);
		});
		
		header.add(taskDisplay);
		
		taskEditor.getSaveButton().addClickHandler(e -> {
			logger.info("TaskWidget2()->closeClick");
			// fireCollapsibleHandler();
			this.setActive(false);
		});
		body.add(taskEditor);
	}
	
	public void setActive(boolean active) {
		logger.info("TaskWidget2().setActive(" + active + ")");
		super.setActive(active);
		if (active) {
			body.add(taskEditor);
			header.setVisible(false);
		} else {
			clearActive();
		}
	}

	@Override
	protected void clearActive() {
		logger.info("TaskWidget2().clearActive()");
		header.setVisible(true);
		body.setVisible(false);
		body.clear();
	}

	@Override
	public void onTaskActionEvent(TaskActionEvent event) {
		if (event.getTask().equals(this.task)) {
			if (event.getAction().equals(TaskAction.EDIT)) {
				
			}
		}
	}
}
