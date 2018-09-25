/**
 * 
 */
package hu.hw.cloud.client.fro.filter.hotelchild;

import java.util.List;
import java.util.logging.Logger;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;

import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.filter.AbstractFilterView;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public abstract class AbstractHotelChildFilterView extends AbstractFilterView implements AbstractHotelChildFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(AbstractHotelChildFilterView.class.getName());

	private MaterialChip hotelChip;

	private MaterialComboBox<HotelDto> hotelComboBox;

	public AbstractHotelChildFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		logger.info("AbstractHotelChildFilterView()");
	}

	@Override
	protected void initView() {
		super.initView();
		initHotelFilter();
	}

	private void initHotelFilter() {
		hotelChip = new MaterialChip();
		collapsibleHeader.insert(hotelChip, 1);
		
		hotelComboBox = new MaterialComboBox<HotelDto>();
		hotelComboBox.setLabel(i18nCore.roomTypesTableHotelsLabel());
		hotelComboBox.setPlaceholder(i18nCore.roomTypesTableHotelsPlaceholder());

		hotelComboBox.addSelectionHandler(e -> {
			setHotelChip(e.getSelectedValues().get(0));
			getUiHandlers().filterChange();
		});
		col1.add(hotelComboBox);
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

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
