/**
 * 
 */
package hu.hw.cloud.client.fro.filter.roomtype;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialChip;

import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.filter.AbstractFilterView;
import hu.hw.cloud.shared.cnst.InventoryType;

/**
 * @author robi
 *
 */
public class RoomTypeFilterView extends AbstractFilterView implements RoomTypeFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomTypeFilterView.class.getName());

	private MaterialChip allItemsChip;
	private MaterialCheckBox onlyActiveCheckBox;

	private MaterialChip inventoryTypeChip;
	private MaterialComboBox<InventoryType> inventoryTypeCombo;

	@Inject
	RoomTypeFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		setI18n(i18nCore);
		initInventoryTypeFilter(cnstCore.inventoryTypeMap(), i18nCore);
	}

	@Override
	protected void initView() {
		super.initView();

		allItemsChip = new MaterialChip();

		onlyActiveCheckBox = new MaterialCheckBox();
		onlyActiveCheckBox.setValue(true);
		onlyActiveCheckBox.addValueChangeHandler(e -> {
			setOnlyActiveChip(e.getValue());
			getUiHandlers().filterChange();
		});
		collapsibleBody.add(onlyActiveCheckBox);

		setOnlyActiveChip(onlyActiveCheckBox.getValue());
	}

	private void initInventoryTypeFilter(Map<String, String> i18nSalesTypes, CoreMessages i18nCore) {
		inventoryTypeChip = new MaterialChip();

		inventoryTypeCombo = new MaterialComboBox<InventoryType>();
		inventoryTypeCombo.setAllowClear(true);
		collapsibleBody.add(inventoryTypeCombo);

		inventoryTypeCombo.setLabel(i18nCore.roomTypeFilterInventoryTypeLabel());
		inventoryTypeCombo.setPlaceholder(i18nCore.roomTypeFilterInventoryTypePlaceholder());

		inventoryTypeCombo.addSelectionHandler(e -> {
			String inventoryTypeText = null;
			if (e.getSelectedValues().get(0) != null)
				inventoryTypeText = i18nSalesTypes.get(e.getSelectedValues().get(0).toString());
			setInventoryTypeChip(inventoryTypeText);
			getUiHandlers().filterChange();
		});
		inventoryTypeCombo.addRemoveItemHandler(e -> {
			setInventoryTypeChip(null);
			getUiHandlers().filterChange();
		});

		Arrays.asList(InventoryType.values())
				.forEach(st -> inventoryTypeCombo.addItem(i18nSalesTypes.get(st.toString()), st));
	}

	private void setInventoryTypeChip(String type) {
		if (inventoryTypeChip.isAttached()) {
			if (type == null) {
				collapsibleHeader.remove(inventoryTypeChip);
				return;
			}
			inventoryTypeChip.setText(type);
		} else {
			if (type != null) {
				inventoryTypeChip.setText(type);
				collapsibleHeader.add(inventoryTypeChip);
			}
		}
	}

	private void setI18n(CoreMessages i18nCore) {
		allItemsChip.setText(i18nCore.roomTypesTableOnlyActive());
		onlyActiveCheckBox.setText(i18nCore.roomTypesTableOnlyActive());
	}

	@Override
	public Boolean isOnlyActive() {
		return onlyActiveCheckBox.getValue();
	}

	private void setOnlyActiveChip(Boolean onlyActive) {
		if (onlyActive) {
			collapsibleHeader.add(allItemsChip);
		} else {
			collapsibleHeader.remove(allItemsChip);
		}
	}

	@Override
	public void reset() {
		logger.info("RoomTypeFilterView().reset()");
		onlyActiveCheckBox.setValue(true);
		setOnlyActiveChip(onlyActiveCheckBox.getValue());

		inventoryTypeCombo.setSingleValue(null);
		setInventoryTypeChip(null);
	}

	@Override
	public InventoryType getSelectedInventoryType() {
		return inventoryTypeCombo.getSingleValue();
	}
}
