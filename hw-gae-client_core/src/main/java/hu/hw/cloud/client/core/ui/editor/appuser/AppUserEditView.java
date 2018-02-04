/**
 * 
 */
package hu.hw.cloud.client.core.ui.editor.appuser;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

/**
 * @author CR
 *
 */
public class AppUserEditView extends ViewWithUiHandlers<AppUserEditUiHandlers>
		implements AppUserEditPresenter.MyView, Editor<AppUserDto> {

	interface Binder extends UiBinder<Widget, AppUserEditView> {
	}

	interface Driver extends SimpleBeanEditorDriver<AppUserDto, AppUserEditView> {
	}

	@UiField
	MaterialModal modal;

	@UiField
	@Ignore
	MaterialTitle title;

	@UiField
	MaterialTextBox username;

	@UiField
	MaterialTextBox emailAddress;

	@UiField
	MaterialComboBox<UserGroupDto> userGroupDtos;

	private final Driver driver;
	private final CoreMessages i18n;

	@Inject
	AppUserEditView(Binder uiBinder, Driver driver, EventBus eventBus, CoreMessages i18n) {
		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		this.i18n = i18n;
		userGroupDtos.setMultiple(true);
		userGroupDtos.setPlaceholder("Válassz csoportot");
		driver.initialize(this);
	}

	@Override
	public void open(Boolean isNew, AppUserDto userDto) {
		if (isNew) {
			title.setTitle(i18n.userEditorCreateTitle());
		} else {
			title.setTitle(i18n.userEditorModifyTitle());
		}
		title.setDescription("");
		driver.edit(userDto);
		modal.open();
	}

	@Override
	public void setUserGroupData(List<UserGroupDto> data) {
		userGroupDtos.clear();
		for (UserGroupDto dto : data) {
			userGroupDtos.addItem(dto.getName(), dto);
		}
	}

	@Override
	public void close() {
		modal.close();
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent ignored) {
		getUiHandlers().save(driver.flush());
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent ignored) {
		close();
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
		// TODO Auto-generated method stub

	}
}