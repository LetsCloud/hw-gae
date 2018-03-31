/**
 * 
 */
package hu.hw.cloud.client.fro.table.appuser;

import hu.hw.cloud.client.fro.table.DtoTableUiHandlers;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface AppUserTableUiHandlers extends DtoTableUiHandlers<AppUserDto> {

	void inviteItem(AppUserDto dto);

	void clearFcmTokens(AppUserDto dto);
}
