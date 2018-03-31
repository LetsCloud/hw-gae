/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface DtoTableUiHandlers<T extends BaseDto> extends UiHandlers {

	void edit(T dto);

	void delete(T dto);

}
