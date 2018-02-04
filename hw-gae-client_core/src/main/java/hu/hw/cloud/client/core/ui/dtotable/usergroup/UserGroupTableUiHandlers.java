/**
 * 
 */
package hu.hw.cloud.client.core.ui.dtotable.usergroup;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public interface UserGroupTableUiHandlers extends UiHandlers {
	
	void editItem(UserGroupDto dto);
	
	void deleteItem(UserGroupDto dto);

}
