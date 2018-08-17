/**
 * 
 */
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
import hu.hw.cloud.shared.cnst.CommMode;
import hu.hw.cloud.shared.dto.profile.CommunicationDto;

/**
 * @author robi
 *
 */
public class CommunicationEditor extends Composite implements Editor<CommunicationDto> {
	private static Logger logger = Logger.getLogger(CommunicationEditor.class.getName());

	interface MyStyle extends CssResource {
		String collapsed();
	}

	@UiField
	MyStyle style;

	interface Binder extends UiBinder<Widget, CommunicationEditor> {
	}

	@Ignore
	@UiField
	HTMLPanel panel;

	@Ignore
	@UiField
	MaterialRow fullPanel, detailsPanel;

	@UiField
	MaterialTextBox parameter;

	@UiField
	MaterialCheckBox primary;

	@Ignore
	@UiField
	MaterialComboBox<CommMode> modeCombo;
	TakesValueEditor<CommMode> mode;

	@Ignore
	@UiField
	MaterialIcon detailsIcon, closeDetails, placeIcon, deleteIcon1;

	private int index;

	private final EventBus eventBus;

	@Inject
	CommunicationEditor(Binder uiBinder, EventBus eventBus, CoreMessages i18nCore, CoreConstants i18nCoreCnst,
			CountryConstants i18nCountry) {
		logger.info("CommunicationEditor()");
		initWidget(uiBinder.createAndBindUi(this));

		primary.getElement().getStyle().setMarginTop(20, Unit.PX);

		placeIcon.setDisplay(Display.NONE);

		this.eventBus = eventBus;

		initPlaceIcon();

		initDetailsIcon();

		initLabelCombo(i18nCoreCnst.communicationModeMap());

		initCloseDetails();

		initDeleteIcon();

		eventBus.addHandler(CommunicationActionEvent.TYPE,
				new CommunicationActionEvent.CommunicationActionEventHandler() {

					@Override
					public void onCommunicationAction(CommunicationActionEvent event) {
						if (event.getAction().equals(CommunicationActionEvent.Action.OPEN))
							openDetails(CommunicationEditor.this.index == event.getIndex());

						if (event.getAction().equals(CommunicationActionEvent.Action.CLOSE)) {
							if (CommunicationEditor.this.index == event.getIndex())
								openDetails(false);
						}
					}

				});

		// saveButton.setBackgroundColor(Color.GREY);

	}

	private void initDetailsIcon() {
//		MaterialIcon detailsIcon = fullAddress.getRightIcon();
		detailsIcon.getElement().getStyle().setMarginTop(10, Unit.PX);
		detailsIcon.getElement().getStyle().setPadding(0, Unit.PX);

		detailsIcon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.info("AddressEditor()->detailsIcon.onClick()");
				eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.OPEN,
						CommunicationEditor.this.getIndex()));
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
			}
		});
	}

	private void initLabelCombo(Map<String, String> i18nAddressTypes) {
		Arrays.asList(CommMode.values())
				.forEach(type -> modeCombo.addItem(i18nAddressTypes.get(type.toString()), type));

		modeCombo.addValueChangeHandler(new ValueChangeHandler<List<CommMode>>() {
			@Override
			public void onValueChange(ValueChangeEvent<List<CommMode>> event) {
				List<CommMode> values = event.getValue();
				if ((values != null) && (!values.isEmpty()))
					parameter.setLabel(i18nAddressTypes.get(values.get(0).toString()));
			}
		});
		modeCombo.setTabIndex(1);
		mode = TakesValueEditor.of(new TakesValue<CommMode>() {

			@Override
			public void setValue(CommMode value) {
				modeCombo.setSingleValue(value);
			}

			@Override
			public CommMode getValue() {
				return modeCombo.getSingleValue();
			}
		});
	}

	private void initCloseDetails() {
		closeDetails.getElement().getStyle().setMarginTop(20, Unit.PX);
		closeDetails.getElement().getStyle().setPadding(0, Unit.PX);

		closeDetails.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.CLOSE,
						CommunicationEditor.this.getIndex()));
			}
		});
	}

	private void initDeleteIcon() {
		deleteIcon1.getElement().getStyle().setMarginTop(20, Unit.PX);
		deleteIcon1.getElement().getStyle().setPadding(0, Unit.PX);

		deleteIcon1.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.DELETE,
						CommunicationEditor.this.getIndex()));
			}
		});
	}

	public void openDetails(Boolean open) {

		if (open) {
//			fullPanel.getElement().addClassName(style.collapsed());

			Timer t = new Timer() {
				@Override
				public void run() {
					detailsPanel.getElement().removeClassName(style.collapsed());
				}
			};
			t.schedule(1000);
		} else {
			detailsPanel.getElement().addClassName(style.collapsed());
			/*
			 * Timer t = new Timer() {
			 * 
			 * @Override public void run() {
			 * fullPanel.getElement().removeClassName(style.collapsed()); } };
			 * t.schedule(1000);
			 */
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
}
