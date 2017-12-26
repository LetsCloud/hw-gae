/**
 * 
 */
package hu.hw.cloud.client.kip.roomstatus.list;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.RevealRootPopupContentEvent;

import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.client.kip.roomstatus.event.RoomStatusRefreshEvent;
import hu.hw.cloud.shared.RoomResource;
import hu.hw.cloud.shared.cnst.RoomStatus;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author CR
 *
 */
public class RoomStatusEditorPresenter extends PresenterWidget<RoomStatusEditorPresenter.MyView>
		implements RoomStatusEditorUiHandlers {
	private static final Logger LOGGER = Logger.getLogger(RoomStatusEditorPresenter.class.getName());

	public interface MyView extends PopupView, HasUiHandlers<RoomStatusEditorUiHandlers> {
		void display(RoomDto roomDto);
	}

	private final ResourceDelegate<RoomResource> roomResourceDelegate;

	@Inject
	RoomStatusEditorPresenter(EventBus eventBus, MyView view, ResourceDelegate<RoomResource> roomResourceDelegate) {
		super(eventBus, view);
		this.roomResourceDelegate = roomResourceDelegate;
		getView().setUiHandlers(this);
	}

	@Override
	public void editStatus(RoomDto roomDto) {
		getView().display(roomDto);
		RevealRootPopupContentEvent.fire(this, this);
	}

	@Override
	public void onCancel() {
		getView().hide();
	}

	@Override
	public void saveStatus(String roomKey, RoomStatus roomStatus) {
		LOGGER.log(Level.INFO, "save()");
		changeStatus(roomKey, roomStatus);
	}

	private void changeStatus(String roomKey, RoomStatus roomStatus) {
		roomResourceDelegate.withCallback(new ErrorHandlerAsyncCallback<RoomDto>(this) {
			@Override
			public void onSuccess(RoomDto roomDto) {
				/*
				 * DisplayMessageEvent.fire(EditManufacturerPresenter.this, new
				 * Message(messages.manufacturerSaved(), MessageStyle.SUCCESS));
				 * ManufacturerAddedEvent.fire(EditManufacturerPresenter.this,
				 * savedManufacturer);
				 */
				RoomStatusRefreshEvent.fire(RoomStatusEditorPresenter.this, roomDto);
				getView().hide();
			}
		}).changeRoomStatus(roomKey, roomStatus);
	}
}