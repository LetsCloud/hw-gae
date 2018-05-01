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
public interface EditorView<T extends BaseDto> extends View {

	void edit(Boolean isNew, T dto);

}
