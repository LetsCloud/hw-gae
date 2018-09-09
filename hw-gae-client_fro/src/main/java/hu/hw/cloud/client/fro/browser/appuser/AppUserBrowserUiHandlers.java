/**
 * 
 */
package hu.hw.cloud.client.fro.browser.appuser;

import java.util.List;

import hu.hw.cloud.client.fro.browser.AbstractBrowserUiHandlers;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface AppUserBrowserUiHandlers extends AbstractBrowserUiHandlers<AppUserDto> {

	void inviteItem(List<AppUserDto> dtos);

	void clearFcmTokens(List<AppUserDto> dtos);
}
