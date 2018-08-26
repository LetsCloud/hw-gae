/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile.customer;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialTextBox;
import hu.hw.cloud.client.fro.editor.profile.AddressListEditor;
import hu.hw.cloud.client.fro.editor.profile.CommunicationListEditor;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class CustomerEditorView extends ViewWithUiHandlers<CustomerEditorUiHandlers>
		implements CustomerEditorPresenter.MyView, Editor<OrganizationDto> {
	private static Logger logger = Logger.getLogger(CustomerEditorView.class.getName());

	interface Binder extends UiBinder<Widget, CustomerEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<OrganizationDto, CustomerEditorView> {
	}

	private final Driver driver;

	@UiField
	MaterialTextBox code, name;

	@Ignore
	@UiField
	MaterialComboBox<ProfileGroupDto> profileGroupCombo;
	TakesValueEditor<ProfileGroupDto> profileGroup;

	@UiField(provided = true)
	CommunicationListEditor communications;

	@UiField(provided = true)
	AddressListEditor addresses;

	/**
	* 
	*/
	@Inject
	CustomerEditorView(Binder uiBinder, Driver driver, CommunicationListEditor communicationDtos,
			AddressListEditor postalAddressDtos) {
		logger.info("CustomerEditorView()");

		this.communications = communicationDtos;
		this.addresses = postalAddressDtos;

		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		driver.initialize(this);

		initProfileGroupCombo();
	}

	private void initProfileGroupCombo() {

		profileGroup = TakesValueEditor.of(new TakesValue<ProfileGroupDto>() {

			@Override
			public void setValue(ProfileGroupDto value) {
				profileGroupCombo.setSingleValue(value);
			}

			@Override
			public ProfileGroupDto getValue() {
				return profileGroupCombo.getSingleValue();
			}
		});

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

	@Override
	public void displayError(EntityPropertyCode code, String message) {
// TODO Auto-generated method stub

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
// TODO Auto-generated method stub

	}

	@UiHandler("addCommunication")
	public void onAddCommunicationClick(ClickEvent event) {
		communications.addItem();
	}

	@UiHandler("addAddress")
	public void onAddAddressClick(ClickEvent event) {
		addresses.addItem();
	}

	@Override
	public void setProfileGroupData(List<ProfileGroupDto> profileGroupData) {
		profileGroupCombo.clear();
		for (ProfileGroupDto dto : profileGroupData) {
			profileGroupCombo.addItem(dto.getCode() + " - " + dto.getDescription(), dto);
		}
	}

	@Override
	public void toEditable() {
		code.setReadOnly(false);
		name.setReadOnly(false);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toReadOnly() {
		code.setReadOnly(true);
		name.setReadOnly(true);
	}
}
