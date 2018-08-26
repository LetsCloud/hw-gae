package hu.hw.cloud.client.fro.editor.profile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.http.client.URL;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.constants.Display;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialRow;
import gwt.material.design.client.ui.MaterialTextBox;

import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.i18n.CountryConstants;
import hu.hw.cloud.client.fro.editor.HasEditorSwitch;
import hu.hw.cloud.client.fro.editor.profile.AddressActionEvent.AddressActiEventHandler;
import hu.hw.cloud.client.fro.ui.MaterialTextAreaAdd;
import hu.hw.cloud.shared.cnst.PostalAddressLabel;
import hu.hw.cloud.shared.dto.profile.AddressDto;

public class AddressEditor extends Composite implements Editor<AddressDto>, HasEditorSwitch {
	private static Logger logger = Logger.getLogger(AddressEditor.class.getName());

	interface MyStyle extends CssResource {
		String collapsed();
	}

	@UiField
	MyStyle style;

	interface Binder extends UiBinder<Widget, AddressEditor> {
	}

	@Ignore
	@UiField
	HTMLPanel panel;

	@Ignore
	@UiField
	MaterialRow fullPanel, details;

	@UiField
	MaterialTextAreaAdd fullAddress;

	@UiField
	MaterialCheckBox primary;

	@UiField
	MaterialTextBox street, postcode, city;

	@Ignore
	@UiField
	MaterialComboBox<String> countryCombo;
	TakesValueEditor<String> country;

	@Ignore
	@UiField
	MaterialComboBox<PostalAddressLabel> labelCombo;
	TakesValueEditor<PostalAddressLabel> label;

	@Ignore
	@UiField
	MaterialIcon detailsIcon, closeDetails, placeIcon, deleteIcon1, deleteIcon;

	private int index;

	private final EventBus eventBus;

	@Inject
	AddressEditor(Binder uiBinder, EventBus eventBus, CoreMessages i18nCore, CoreConstants i18nCoreCnst,
			CountryConstants i18nCountry) {
		logger.info("AddressEditor()");
		initWidget(uiBinder.createAndBindUi(this));

		primary.getElement().getStyle().setMarginTop(20, Unit.PX);

		deleteIcon1.setDisplay(Display.NONE);

		this.eventBus = eventBus;

		initPlaceIcon();
		
		initFullAddress();

		initDetailsIcon();

		initLabelCombo(i18nCoreCnst.addressTypeMap());

		initCountryCombo(i18nCountry.countryMap());

		initCloseDetails();

		initDeleteIcon();

		eventBus.addHandler(AddressActionEvent.TYPE, new AddressActiEventHandler() {

			@Override
			public void onAddressAction(AddressActionEvent event) {
				if (event.getAction().equals(AddressActionEvent.Action.OPEN))
					openDetails(AddressEditor.this.index == event.getIndex());

				if (event.getAction().equals(AddressActionEvent.Action.CLOSE)) {
					if (AddressEditor.this.index == event.getIndex())
						openDetails(false);
				}
			}

		});

		// saveButton.setBackgroundColor(Color.GREY);

	}

	private void initFullAddress() {
	}

	private void initDetailsIcon() {
//		MaterialIcon detailsIcon = fullAddress.getRightIcon();
		detailsIcon.getElement().getStyle().setMarginTop(10, Unit.PX);
		detailsIcon.getElement().getStyle().setPadding(0, Unit.PX);

		detailsIcon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("AddressEditor()->detailsIcon.onClick()");
				eventBus.fireEvent(
						new AddressActionEvent(AddressActionEvent.Action.OPEN, AddressEditor.this.getIndex()));
			}
		});
	}

	private void initPlaceIcon() {

//		placeIcon.setDisplay(Display.NONE);
		placeIcon.getElement().getStyle().setMarginTop(10, Unit.PX);
		placeIcon.getElement().getStyle().setPadding(0, Unit.PX);

		placeIcon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				com.google.gwt.user.client.Window.open(
						"https://www.google.com/maps/search/?api=1&query=" + URL.decode(fullAddress.getValue()), "Hello",
						"menubar=no,location=false,resizable=yes,scrollbars=yes,status=no,dependent=true");
			}
		});
	}

	private void initLabelCombo(Map<String, String> i18nAddressTypes) {
		Arrays.asList(PostalAddressLabel.values())
				.forEach(type -> labelCombo.addItem(i18nAddressTypes.get(type.toString()), type));

		labelCombo.addValueChangeHandler(new ValueChangeHandler<List<PostalAddressLabel>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<PostalAddressLabel>> event) {
				List<PostalAddressLabel> values = event.getValue();
				if ((values != null) && (!values.isEmpty()))
					fullAddress.setLabel(i18nAddressTypes.get(values.get(0).toString()));
			}
		});
		labelCombo.setTabIndex(1);
		label = TakesValueEditor.of(new TakesValue<PostalAddressLabel>() {

			@Override
			public void setValue(PostalAddressLabel value) {
				labelCombo.setSingleValue(value);
			}

			@Override
			public PostalAddressLabel getValue() {
				return labelCombo.getSingleValue();
			}
		});
	}

	private void initCountryCombo(Map<String, String> i18nCountryMap) {
		i18nCountryMap.forEach((k, v) -> {
			countryCombo.addItem(i18nCountryMap.get(k), v);
		});

		country = TakesValueEditor.of(new TakesValue<String>() {

			@Override
			public void setValue(String value) {
				countryCombo.setSingleValue(value);
			}

			@Override
			public String getValue() {
				return countryCombo.getSingleValue();
			}
		});
	}

	private void initCloseDetails() {
		closeDetails.getElement().getStyle().setMarginTop(20, Unit.PX);
		closeDetails.getElement().getStyle().setPadding(0, Unit.PX);

		closeDetails.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("AddressEditor()->detailsIcon.onClick()");
				eventBus.fireEvent(
						new AddressActionEvent(AddressActionEvent.Action.CLOSE, AddressEditor.this.getIndex()));
			}
		});
	}

	private void initDeleteIcon() {
		deleteIcon1.getElement().getStyle().setMarginTop(20, Unit.PX);
		deleteIcon1.getElement().getStyle().setPadding(0, Unit.PX);

		deleteIcon1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("AddressEditor()->deleteIcon.onClick()");
				eventBus.fireEvent(
						new AddressActionEvent(AddressActionEvent.Action.DELETE, AddressEditor.this.getIndex()));
			}
		});

		deleteIcon.getElement().getStyle().setMarginTop(20, Unit.PX);
		deleteIcon.getElement().getStyle().setPadding(0, Unit.PX);

		deleteIcon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("AddressEditor()->deleteIcon.onClick()");
				eventBus.fireEvent(
						new AddressActionEvent(AddressActionEvent.Action.DELETE, AddressEditor.this.getIndex()));
			}
		});
	}

	public void openDetails(Boolean open) {

		if (open) {
			fullPanel.getElement().addClassName(style.collapsed());

			Timer t = new Timer() {
				@Override
				public void run() {
					details.getElement().removeClassName(style.collapsed());
				}
			};
			t.schedule(1000);
		} else {
			createFullAddress();
			details.getElement().addClassName(style.collapsed());

			Timer t = new Timer() {
				@Override
				public void run() {
					fullPanel.getElement().removeClassName(style.collapsed());
				}
			};
			t.schedule(1000);
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setBackgeoundColor() {
		panel.getElement().getStyle().setBackgroundColor("#f5f5f5");
	}

	private void createFullAddress() {
		fullAddress.setValue(
				country.getValue() + ", " + postcode.getValue() + " " + city.getValue() + ", " + street.getValue());
	}

	@Override
	public void toEditable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toReadOnly() {
		// TODO Auto-generated method stub
		
	}
}
