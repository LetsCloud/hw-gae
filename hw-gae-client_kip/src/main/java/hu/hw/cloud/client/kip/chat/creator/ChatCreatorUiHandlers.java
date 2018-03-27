/**
 * 
 */
package hu.hw.cloud.client.kip.chat.creator;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface ChatCreatorUiHandlers extends UiHandlers {
	void saveChat(List<AppUserDto> receivers, String message);
}
