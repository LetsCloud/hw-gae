/**
 * 
 */
package hu.hw.cloud.client.fro.editor.roomtype;

import java.util.Arrays;
import java.util.Map;
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
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialFloatBox;
import gwt.material.design.client.ui.MaterialIntegerBox;
import gwt.material.design.client.ui.MaterialTextBox;

import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.shared.cnst.SalesType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomTypeEditorView extends ViewWithUiHandlers<RoomTypeEditorUiHandlers>
		implements RoomTypeEditorPresenter.MyView, Editor<RoomTypeDto> {
	private static Logger logger = Logger.getLogger(RoomTypeEditorView.class.getName());

	interface Binder extends UiBinder<Widget, RoomTypeEditorView> {
	}

	interface Driver extends SimpleBeanEditorDriver<RoomTypeDto, RoomTypeEditorView> {
	}

	private final Driver driver;

	// private final CoreConstants i18nCoreCnst;

	@UiField
	MaterialTextBox code, name, description;

	@UiField
	MaterialIntegerBox beds, xtrBeds;

	@UiField
	MaterialFloatBox cleaningFactor;
	
	@UiField
	MaterialCheckBox active;

	@UiField
	@Ignore
	MaterialComboBox<SalesType> salesTypeX;
	TakesValueEditor<SalesType> salesType;

	@UiField
	MaterialButton saveButton;

	/**
	* 
	*/
	@Inject
	RoomTypeEditorView(Binder uiBinder, Driver driver, CoreConstants i18nCoreCnst) {
		logger.info("RoomTypeEditorView()");

		initWidget(uiBinder.createAndBindUi(this));

		salesType = TakesValueEditor.of(new TakesValue<SalesType>() {

			@Override
			public void setValue(SalesType value) {
				salesTypeX.setSingleValue(value);
			}

			@Override
			public SalesType getValue() {
				return salesTypeX.getSingleValue();
			}
		});

		// saveButton.setBackgroundColor(Color.GREY);

		initSalesTypeCombo(i18nCoreCnst.salesTypeMap());

		this.driver = driver;
		driver.initialize(this);
	}

	private void initSalesTypeCombo(Map<String, String> i18nSalesTypes) {
		Arrays.asList(SalesType.values()).forEach(st -> salesTypeX.addItem(i18nSalesTypes.get(st.toString()), st));
	}

	@Override
	public void edit(Boolean isNew, RoomTypeDto dto) {
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
		RoomTypeDto dto = driver.flush();
		logger.info("RoomTypeEditorView().onSaveClick()-" + dto);
		getUiHandlers().save(dto);
	}

	@UiHandler("cancelButton")
	void onCancelClick(ClickEvent event) {
		getUiHandlers().cancel();
	}
}
