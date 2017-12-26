/**
 * 
 */
package hu.hw.cloud.client.kip.roomstatus.filter;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.filter.RoomStatusFilterDto;

/**
 * @author CR
 *
 */
public interface RoomStatusFilterUiHandlers extends UiHandlers {
	void doFilter(RoomStatusFilterDto filterDto);
}
