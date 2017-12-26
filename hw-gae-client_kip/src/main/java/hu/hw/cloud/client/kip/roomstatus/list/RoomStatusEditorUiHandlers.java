/**
 * 
 */
package hu.hw.cloud.client.kip.roomstatus.list;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public interface RoomStatusEditorUiHandlers extends UiHandlers {

	void editStatus(RoomDto roomDto);

	void onCancel();
	
	void saveStatus(String roomKey, RoomStatus roomStatus);
}