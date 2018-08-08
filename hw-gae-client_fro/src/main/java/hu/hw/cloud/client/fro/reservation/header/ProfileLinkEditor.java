/**
 * 
 */
package hu.hw.cloud.client.fro.reservation.header;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.editor.room.DeleteEvent;
import hu.hw.cloud.client.fro.editor.room.DeleteEvent.DeleteEventHandler;
import hu.hw.cloud.shared.dto.reservation.ProfileLinkDto;

/**
 * @author robi
 *
 */
public class ProfileLinkEditor extends Composite implements Editor<ProfileLinkDto> {

	interface Binder extends UiBinder<Widget, ProfileLinkEditor> {
	}

	/**
	 */
	@Inject
	ProfileLinkEditor(Binder uiBinder, CoreMessages i18nCore) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public final HandlerRegistration addDeleteHandler(DeleteEventHandler handler) {
		return addHandler(handler, DeleteEvent.TYPE);
	}

}
