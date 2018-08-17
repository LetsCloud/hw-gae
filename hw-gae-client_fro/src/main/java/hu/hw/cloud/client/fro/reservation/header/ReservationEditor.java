/**
 * 
 */
package hu.hw.cloud.client.fro.reservation.header;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import hu.hw.cloud.client.fro.reservation.header.ProfileLinkEditor.Binder;
import hu.hw.cloud.shared.dto.reservation.ReservationDto;

/**
 * @author robi
 *
 */
public class ReservationEditor extends Composite implements Editor<ReservationDto> {

	interface Binder extends UiBinder<Widget, ReservationEditor> {
	}

	/**
	 */
	@Inject
	ReservationEditor(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
