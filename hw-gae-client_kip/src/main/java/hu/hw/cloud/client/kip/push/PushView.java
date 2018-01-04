/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import hu.hw.cloud.shared.rpc.NotificationDTO;

/**
 * @author robi
 *
 */
public class PushView extends ViewWithUiHandlers<PushUiHandlers> implements PushPresenter.MyView {
	private static Logger logger = Logger.getLogger(PushView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, PushView> {
	}

	@UiField
	MaterialModal modal;

	@UiField
	MaterialTextBox title, image;

	@UiField
	MaterialTextArea description;

	@Inject
	PushView(Binder binder) {
		logger.log(Level.INFO, "PushView()");
		initWidget(binder.createAndBindUi(this));
	}

	@Override
	public void initView() {
	}

	@UiHandler("okButton")
	void btnClose(ClickEvent e) {
		modal.close();
	}

	@UiHandler("cancelButton")
	void btnSave(ClickEvent e) {
		modal.close();
	}

	@Override
	public void openModal() {
		modal.open();
	}

	@UiHandler("notifyAllUser")
	void notifyAllUser(ClickEvent e) {
		getUiHandlers().push(new NotificationDTO(title.getValue(), description.getValue(), image.getValue()));
	}

}