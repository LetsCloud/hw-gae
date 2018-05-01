/**
 * 
 */
package hu.hw.cloud.client.fro.table.appuser;

import java.util.List;

import hu.hw.cloud.client.fro.table.DtoTableUiHandlers;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public interface AppUserTableUiHandlers extends DtoTableUiHandlers<AppUserDto> {

	void inviteItem(List<AppUserDto> dtos);

	void clearFcmTokens(List<AppUserDto> dtos);
}
