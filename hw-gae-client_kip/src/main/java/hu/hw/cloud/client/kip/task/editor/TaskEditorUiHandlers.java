/**
 * 
 */
package hu.hw.cloud.client.kip.task.editor;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.task.TaskDto;

/**
 * @author robi
 *
 */
public interface TaskEditorUiHandlers extends UiHandlers {

	void create();

	void edit(TaskDto dto);

	void save(TaskDto dto);

}
