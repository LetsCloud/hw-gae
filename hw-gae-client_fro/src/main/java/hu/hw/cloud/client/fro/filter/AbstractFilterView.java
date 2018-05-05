/**
 * 
 */
package hu.hw.cloud.client.fro.filter;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialColumn;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public abstract class AbstractFilterView extends ViewWithUiHandlers<AbstractFilterUiHandlers>
		implements AbstractFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(AbstractFilterView.class.getName());

	interface Binder extends UiBinder<Widget, AbstractFilterView> {
	}

	private static Binder uiBinder = GWT.create(Binder.class);

	private final CoreMessages i18nCore;

	@UiField
	protected MaterialCollapsibleHeader collapsibleHeader;

	@UiField
	protected MaterialColumn col1, col2;

	@UiField
	MaterialChip hotelChip;

	@UiField
	MaterialComboBox<HotelDto> hotelComboBox;

//	@UiField
//	protected MaterialCollapsibleBody collapsibleBody;

	private MaterialChip onlyActiveChip;
	@UiField
	MaterialCheckBox onlyActiveCheckBox;

	public AbstractFilterView(CoreMessages i18nCore) {
		logger.info("AbstractFilterView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.i18nCore = i18nCore;

		initView();
	}

	protected void initView() {
		initHotelFilter();
		initOnlyActiveFilter();
	}

	private void initHotelFilter() {
		hotelComboBox.setLabel(i18nCore.roomTypesTableHotelsLabel());
		hotelComboBox.setPlaceholder(i18nCore.roomTypesTableHotelsPlaceholder());

		hotelComboBox.addSelectionHandler(e -> {
			setHotelChip(e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
	}

	private void setHotelChip(HotelDto hoteDto) {
		hotelChip.setText(hoteDto.getName());
	}
	
	@Override
	public void setHotelData(List<HotelDto> hotelData) {
		for (HotelDto hd : hotelData) {
			hotelComboBox.addItem(hd.getName(), hd);
		}
	}

	@Override
	public void setSelectedHotel(HotelDto hotelDto) {
		Integer index = hotelComboBox.getValueIndex(hotelDto);
		hotelComboBox.setSelectedIndex(index);
		setHotelChip(hotelDto);
	}

	@Override
	public HotelDto getSelectedHotel() {
		return hotelComboBox.getSelectedValue().get(0);
	}
	
	private void initOnlyActiveFilter() {
		onlyActiveChip = new MaterialChip();
		onlyActiveChip.setText(i18nCore.roomTypesTableOnlyActive());

		onlyActiveCheckBox.setValue(true);
		onlyActiveCheckBox.addValueChangeHandler(e -> {
			setOnlyActiveChip(e.getValue());
			getUiHandlers().filterChange();
		});

		setOnlyActiveChip(onlyActiveCheckBox.getValue());
	}

	private void setOnlyActiveChip(Boolean onlyActive) {
		if (onlyActive) {
			collapsibleHeader.add(onlyActiveChip);
		} else {
			collapsibleHeader.remove(onlyActiveChip);
		}
	}

	@Override
	public Boolean isOnlyActive() {
		return onlyActiveCheckBox.getValue();
	}
}