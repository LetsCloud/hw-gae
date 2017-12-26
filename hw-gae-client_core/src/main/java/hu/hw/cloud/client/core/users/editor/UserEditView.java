/**
 * 
 */
package hu.hw.cloud.client.core.users.editor;

import java.util.logging.Level;
import java.util.logging.Logger;

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
import hu.hw.cloud.shared.dto.common.AppUserDto;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

/**
 * @author CR
 *
 */
public class UserEditView extends ViewWithUiHandlers<UserEditUiHandlers>
		implements UserEditPresenter.MyView, Editor<AppUserDto> {
	private static Logger logger = Logger.getLogger(UserEditView.class.getName());

	interface Binder extends UiBinder<Widget, UserEditView> {
	}

	interface Driver extends SimpleBeanEditorDriver<AppUserDto, UserEditView> {
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

	private final Driver driver;
	private final CoreMessages i18n;

	@Inject
	UserEditView(Binder uiBinder, Driver driver, EventBus eventBus, CoreMessages i18n) {
		logger.log(Level.INFO, "UserEditView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.driver = driver;
		this.i18n = i18n;

		driver.initialize(this);
	}

	@Override
	public void startCreate(AppUserDto userDto) {
		title.setTitle(i18n.userEditorCreateTitle());
		startEdit(userDto);
	}

	@Override
	public void startModify(AppUserDto userDto) {
		title.setTitle(i18n.userEditorModifyTitle());
		startEdit(userDto);
	}

	private void startEdit(AppUserDto userDto) {
		driver.edit(userDto);
		modal.open();
	}

	@UiHandler("saveButton")
	void onSaveClicked(ClickEvent ignored) {
		getUiHandlers().onSave(driver.flush());
		modal.close();
	}

	@UiHandler("cancelButton")
	void onCancelClicked(ClickEvent ignored) {
		getUiHandlers().onCancel();
		modal.close();
	}
}