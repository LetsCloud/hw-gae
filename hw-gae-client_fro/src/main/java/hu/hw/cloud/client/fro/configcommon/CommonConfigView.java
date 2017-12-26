package hu.hw.cloud.client.fro.configcommon;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialColumn;

public class CommonConfigView extends ViewWithUiHandlers<CommonConfigUiHandlers>
		implements CommonConfigPresenter.MyView {
	private static Logger logger = Logger.getLogger(CommonConfigView.class.getName());

	interface Binder extends UiBinder<Widget, CommonConfigView> {
	}

	@UiField
	MaterialColumn contentPanel;

	@UiField
	SimplePanel userEditPanel;
	
	@UiField
	MaterialButton addButton;

	@Inject
	CommonConfigView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		logger.log(Level.INFO, "CommonConfigView()");
		bindSlot(CommonConfigPresenter.SLOT_CONTENT, contentPanel);
		bindSlot(CommonConfigPresenter.SLOT_EDITOR, userEditPanel);
	}

	@Override
	public void setContent(Widget w) {
		contentPanel.add(w);
	}

	@Override
	public void refreshX() {
		// TODO Auto-generated method stub

	}

	@UiHandler("addButton")
	public void onAddClick(ClickEvent event) {
		getUiHandlers().createUser();
	}
}
