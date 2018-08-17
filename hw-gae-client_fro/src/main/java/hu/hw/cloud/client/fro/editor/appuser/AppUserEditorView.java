/**
 * 
 */
package hu.hw.cloud.client.fro.editor.appuser;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.combobox.events.SelectItemEvent;
import gwt.material.design.addins.client.combobox.events.SelectItemEvent.SelectComboHandler;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialTextBox;

import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class AppUserEditorView extends ViewWithUiHandlers<AppUserEditorUiHandlers>
		implements AppUserEditorPresenter.MyView, Editor<AppUserDto> {
	private static Logger logger = Logger.getLogger(AppUserEditorView.class.getName());

	interface Binder extends UiBinder<Widget, AppUserEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<AppUserDto, AppUserEditorView> {
	}

	private final Driver driver;
	
	@UiField
	@Ignore
	MaterialImage image;

	@UiField
	MaterialTextBox code, name, title, username, emailAddress;

	@UiField
	MaterialCheckBox enabled, admin;

	@UiField
	MaterialComboBox<UserGroupDto> userGroupDtos;

	@UiField
	MaterialComboBox<HotelDto> availableHotelDtos;

	@UiField
	@Ignore
	MaterialComboBox<HotelDto> defaultHotelDtox;
	TakesValueEditor<HotelDto> defaultHotelDto;

	String picture;
	
	@Inject
	AppUserEditorView(Binder uiBinder, Driver driver, EventBus eventBus) {
		logger.info("AppUserEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		defaultHotelDto = TakesValueEditor.of(new TakesValue<HotelDto>() {

			@Override
			public void setValue(HotelDto value) {
				defaultHotelDtox.setSingleValue(value);
			}

			@Override
			public HotelDto getValue() {
				return defaultHotelDtox.getSingleValue();
			}
		});

		this.driver = driver;
		driver.initialize(this);

		availableHotelDtos.addSelectionHandler(new SelectComboHandler<HotelDto>() {

			@Override
			public void onSelectItem(SelectItemEvent<HotelDto> event) {
				setDefHotelCombo(event.getSelectedValues());
			}
		});
	}

	@Override
	public void edit(Boolean isNew, AppUserDto dto) {
		logger.info("AppUserEditorView().edit()->dto=" + dto);

		if (dto.getPicture() != null) {
			setImageUrl(dto.getPicture());
		} else {
			setImageUrl(GWT.getHostPageBaseURL() + "image/user_plus.jpeg");
		}

		setDefHotelCombo(dto.getAvailableHotelDtos());

		driver.edit(dto);
	}

	@Override
	public void setUserGroupData(List<UserGroupDto> data) {
		userGroupDtos.clear();
		for (UserGroupDto dto : data) {
			userGroupDtos.addItem(dto.getName(), dto);
		}
	}

	@Override
	public void setHotelData(List<HotelDto> data) {
		availableHotelDtos.clear();
		for (HotelDto dto : data) {
			availableHotelDtos.addItem(dto.getName(), dto);
		}
	}

	private void setDefHotelCombo(List<HotelDto> dtos) {
		defaultHotelDtox.clear();
		for (HotelDto hd : dtos) {
			defaultHotelDtox.addItem(hd.getName(), hd);
		}
	}

	@UiHandler("saveButton")
	void onSaveClick(ClickEvent event) {
		AppUserDto dto = driver.flush();
		dto.setPicture(picture);
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
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

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}