/**
 * 
 */
package hu.hw.cloud.client.fro.edit.appuser;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
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
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialModal;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialTitle;

/**
 * @author CR
 *
 */
public class AppUserEditView extends ViewWithUiHandlers<AppUserEditUiHandlers>
		implements AppUserEditPresenter.MyView, Editor<AppUserDto> {
	private static Logger logger = Logger.getLogger(AppUserEditView.class.getName());

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
	@Ignore
	MaterialImage image;

	@UiField
	MaterialTextBox code, name, username, emailAddress;

	@UiField
	MaterialComboBox<UserGroupDto> userGroupDtos;

	String picture;

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
		if (userDto.getPicture() != null)
			setImageUrl(userDto.getPicture());
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
		AppUserDto userDto = driver.flush();
		userDto.setPicture(picture);
		getUiHandlers().save(userDto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent ignored) {
		close();
	}

	
	@Override
	public void displayError(EntityPropertyCode code, String message) {
		// TODO Auto-generated method stub

	}

	@UiHandler("directorLink")
	public void onDirectorClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/director.png");
	}

	@UiHandler("financeLink")
	public void onFinanceClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/finance2.png");
	}

	@UiHandler("salesLink")
	public void onSalesClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/sales.png");
	}

	@UiHandler("fomLink")
	public void onFomClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/fom.png");
	}

	@UiHandler("recLink")
	public void onRecClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/receptionist.png");
	}

	@UiHandler("fandbLink")
	public void onFandbClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/fandb.png");
	}

	@UiHandler("eHousekeeperLink")
	public void onEhkClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/e_housekeeper.png");
	}

	@UiHandler("housekeeper1Link")
	public void onHk1Click(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/housekeeper1.png");
	}

	@UiHandler("housekeeper2Link")
	public void onHk2Click(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/housekeeper2_2.png");
	}

	@UiHandler("maintenanceLink")
	public void onMaintenanceClick(ClickEvent event) {
		setImageUrl(GWT.getHostPageBaseURL() + "image/maintenance.png");
	}

	private void setImageUrl(String url) {
		picture = url;
		image.setUrl(url);
	}
}