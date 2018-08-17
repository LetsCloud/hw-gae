/**
 * 
 */
package hu.hw.cloud.client.fro.editor.room;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialDatePicker;
import gwt.material.design.client.ui.MaterialSwitch;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.editor.room.DeleteEvent.DeleteEventHandler;
import hu.hw.cloud.shared.dto.hotel.RoomAvailabilityDto;

/**
 * @author robi
 *
 */
public class AvailabilityEditor extends Composite implements Editor<RoomAvailabilityDto> {

	interface Binder extends UiBinder<Widget, AvailabilityEditor> {
	}

	@UiField
	MaterialSwitch available;

	@UiField
	MaterialDatePicker date;

	/**
	 */
	@Inject
	AvailabilityEditor(Binder uiBinder, CoreMessages i18nCore) {
		initWidget(uiBinder.createAndBindUi(this));
		available.setOffLabel(i18nCore.comNo());
		available.setOnLabel(i18nCore.comYes());
	}
/*
	private void fireDeleteEvent() {
		fireEvent(new DeleteEvent());
	}
*/
	public final HandlerRegistration addDeleteHandler(DeleteEventHandler handler) {
		return addHandler(handler, DeleteEvent.TYPE);
	}
}
