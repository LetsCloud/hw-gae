/**
 * 
 */
package hu.hw.cloud.client.kip.roomstatus.list;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.ui.table.MaterialList;
import hu.hw.cloud.client.kip.i18n.RoomStatusAbbrConstants;
import hu.hw.cloud.client.kip.roomstatus.event.RoomStatusEditEvent;
import hu.hw.cloud.client.kip.roomstatus.event.RoomStatusEditEvent.RoomStatusEditHandler;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public class RoomStatusListView extends ViewWithUiHandlers<RoomStatusListUiHandlers>
		implements RoomStatusListPresenter.MyView {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusListView.class.getName());

	interface Binder extends UiBinder<Widget, RoomStatusListView> {
	}

	@UiField
	MaterialList materialList;

	private RoomStatusAbbrConstants i18nRoomStatus;
	/**
	 */
	@Inject
	RoomStatusListView(Binder uiBinder, RoomStatusAbbrConstants i18nRoomStatus) {
		LOGGER.log(Level.INFO, "RoomStatusListView()");
		initWidget(uiBinder.createAndBindUi(this));
		this.i18nRoomStatus = i18nRoomStatus;
	}

	@Override
	public void displayRooms(List<RoomDto> roomDtos) {
		LOGGER.log(Level.INFO, "displayRooms()");
		materialList.clear();
		for (RoomDto dto : roomDtos) {
			RoomStatusItem rsi = new RoomStatusItem(dto, i18nRoomStatus);
			rsi.addRoomStatusEditHandler(new RoomStatusEditHandler() {

				@Override
				public void onEdit(RoomStatusEditEvent event) {
					LOGGER.log(Level.INFO, "onEdit()");
					getUiHandlers().editStatus(event.getRoomDto());
				}

			});
			materialList.add(rsi);
		}
	}

	@Override
	public void updateRoom(RoomDto roomDto) {
		for (int i = 0; i < materialList.getWidgetCount(); i++) {
			RoomStatusItem rsi = (RoomStatusItem) materialList.getWidget(i);
			if (rsi.getRoomKey().equals(roomDto.getWebSafeKey())) {
				rsi.updateRoom(roomDto);
				return;
			}
		}
	}

}
