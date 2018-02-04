/**
 * 
 */
package hu.hw.cloud.client.core.ui.dtotable.appuser;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface AppUserTableUiHandlers extends UiHandlers {

	void editItem(AppUserDto dto);

	void deleteItem(AppUserDto dto);

}
