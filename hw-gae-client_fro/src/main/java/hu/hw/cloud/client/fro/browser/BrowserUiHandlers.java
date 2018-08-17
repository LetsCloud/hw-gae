/**
 * 
 */
package hu.hw.cloud.client.fro.browser;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public interface BrowserUiHandlers<T extends BaseDto> extends UiHandlers {


	void addNew();

	void edit(T dto);

	void delete(List<T> dtos);

}
