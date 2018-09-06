/**
 * 
 */
package hu.hw.cloud.client.fro.editor;

import com.gwtplatform.mvp.client.View;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface AbstractEditorView<T extends BaseDto> extends View {
	
	void show(T dto);
	
	void edit(T dto);

	void close();

}
