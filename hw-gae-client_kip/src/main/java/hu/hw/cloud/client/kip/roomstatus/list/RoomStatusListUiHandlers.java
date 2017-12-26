/**
 * 
 */
package hu.hw.cloud.client.kip.roomstatus.list;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public interface RoomStatusListUiHandlers extends UiHandlers {
	
	void editStatus(RoomDto roomDto);

}
