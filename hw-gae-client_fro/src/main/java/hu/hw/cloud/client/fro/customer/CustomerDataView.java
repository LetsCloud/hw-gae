/**
 * 
 */
package hu.hw.cloud.client.fro.customer;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialTextBox;
import hu.hw.cloud.client.fro.editor.profile.AddressListEditor;
import hu.hw.cloud.client.fro.editor.profile.CommunicationListEditor;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public class CustomerDataView extends ViewWithUiHandlers<CustomerDataUiHandlers>
		implements CustomerDataPresenter.MyView, Editor<OrganizationDto> {
	private static Logger logger = Logger.getLogger(CustomerDataView.class.getName());

	interface Binder extends UiBinder<Widget, CustomerDataView> {
	}

	interface Driver extends SimpleBeanEditorDriver<OrganizationDto, CustomerDataView> {
	}

	private final Driver driver;

	@UiField
	MaterialTextBox code, name;

	@UiField(provided = true)
	CommunicationListEditor communicationDtos;

	@UiField(provided = true)
	AddressListEditor postalAddressDtos;

	/**
	* 
	*/
	@Inject
	CustomerDataView(Binder uiBinder, Driver driver, CommunicationListEditor communicationDtos,
			AddressListEditor postalAddressDtos) {
		logger.info("CustomerEditorView()");

		this.communicationDtos = communicationDtos;

		this.postalAddressDtos = postalAddressDtos;

		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		driver.initialize(this);
	}

	@Override
	public void edit(Boolean isNew, OrganizationDto dto) {
		driver.edit(dto);

		Timer t = new Timer() {
			@Override
			public void run() {
				code.setFocus(true);
			}
		};
		t.schedule(100);
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		OrganizationDto dto = driver.flush();
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent ignored) {
		getUiHandlers().cancel();
	}

	@Override
	public void close() {
//TODO Auto-generated method stub

	}

	@UiHandler("addCommunication")
	public void onAddCommunicationClick(ClickEvent event) {
		communicationDtos.addItem();
	}

	@UiHandler("addAddress")
	public void onAddAddressClick(ClickEvent event) {
		postalAddressDtos.addItem();
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
		// TODO Auto-generated method stub
		
	}
}
