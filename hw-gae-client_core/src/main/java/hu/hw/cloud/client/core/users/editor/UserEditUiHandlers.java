/**
 * 
 */
package hu.hw.cloud.client.core.users.editor;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
public interface UserEditUiHandlers extends UiHandlers {

	void onSave(AppUserDto userDto);

	void onCancel();
}