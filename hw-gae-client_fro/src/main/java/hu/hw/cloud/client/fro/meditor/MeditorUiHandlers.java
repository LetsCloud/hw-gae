/**
 * 
 */
package hu.hw.cloud.client.fro.meditor;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface MeditorUiHandlers<T extends BaseDto> extends UiHandlers {

	void create();

	void edit(T dto);

	void save(T dto);
	
	void cancel();
}
