/**
 * 
 */
package hu.hw.cloud.client.fro.editor;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface AbstractEditorUiHandlers<T extends BaseDto> extends UiHandlers {

	void save(T dto);
	
	void cancel();
}
