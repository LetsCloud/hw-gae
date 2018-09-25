/**
 * 
 */
package hu.hw.cloud.client.fro.filter.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialTextBox;
import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.filter.AbstractFilterView;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileFilterView extends AbstractFilterView implements ProfileFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(ProfileFilterView.class.getName());

	private MaterialChip codeChip, nameChip, profileGroupChip;
	private MaterialTextBox codeTextBox, nameTextBox;
	private MaterialComboBox<ProfileGroupDto> profileGroupCombo;

	@Inject
	ProfileFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		logger.info("ProfileFilterView()");
	}

	@Override
	protected void initView() {
		super.initView();

		initCodeFilter();
		initNameFilter();
		initProfileGroupFilter();
	}

	private void initCodeFilter() {
		codeChip = new MaterialChip();

		codeTextBox = new MaterialTextBox();
		codeTextBox.setLabel(i18nCore.profileFilterCodeLabel());
		codeTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (Strings.isNullOrEmpty(event.getValue()))
					setCodeChip("");
				else
					setCodeChip(i18nCore.profileFilterCode() + event.getValue());
				getUiHandlers().filterChange();
			}
		});
		col1.add(codeTextBox);
	}

	private void setCodeChip(String code) {
		if (codeChip.isAttached()) {
			if ((code == null) || (code.isEmpty())) {
				collapsibleHeader.remove(codeChip);
				return;
			}
			codeChip.setText(code);
		} else {
			if ((code != null) && (!code.isEmpty())) {
				codeChip.setText(code);
				collapsibleHeader.add(codeChip);
			}
		}
	}

	@Override
	public String getCode() {
		return codeTextBox.getValue();
	}

	private void initNameFilter() {
		nameChip = new MaterialChip();

		nameTextBox = new MaterialTextBox();
		nameTextBox.setLabel(i18nCore.profileFilterNameLabel());
		nameTextBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if (Strings.isNullOrEmpty(event.getValue()))
					setNameChip("");
				else
					setNameChip(i18nCore.profileFilterNameLabel() + event.getValue());
				getUiHandlers().filterChange();
			}
		});
		col2.add(nameTextBox);
	}

	private void setNameChip(String name) {
		if (nameChip.isAttached()) {
			if ((name == null) || (name.isEmpty())) {
				collapsibleHeader.remove(nameChip);
				return;
			}
			nameChip.setText(name);
		} else {
			if ((name != null) && (!name.isEmpty())) {
				nameChip.setText(name);
				collapsibleHeader.add(nameChip);
			}
		}
	}

	@Override
	public String getName() {
		return nameTextBox.getValue();
	}

	private void initProfileGroupFilter() {
		profileGroupChip = new MaterialChip();

		profileGroupCombo = new MaterialComboBox<ProfileGroupDto>();
		profileGroupCombo.setMultiple(true);
		profileGroupCombo.setAllowClear(true);
		profileGroupCombo.setAllowBlank(true);
		profileGroupCombo.setCloseOnSelect(false);
		profileGroupCombo.setMarginTop(25);
		profileGroupCombo.setLabel(i18nCore.roomFilterRoomTypesLabel());
		profileGroupCombo.setPlaceholder(i18nCore.roomFilterRoomTypesPlaceholder());
		profileGroupCombo.addSelectionHandler(e -> {
			String roomTypesText = null;
			for (ProfileGroupDto roomType : e.getSelectedValues()) {
				if (roomTypesText == null) {
					roomTypesText = roomType.getCode();
				} else {
					roomTypesText = roomTypesText + ", " + roomType.getCode();
				}
			}
			setProfileGroupChip(roomTypesText);
			getUiHandlers().filterChange();
		});
		profileGroupCombo.addRemoveItemHandler(e -> {
			setProfileGroupChip(null);
			getUiHandlers().filterChange();
		});
		col1.add(profileGroupCombo);
	}

	private void setProfileGroupChip(String profileGroup) {
		if (profileGroupChip.isAttached()) {
			if ((profileGroup == null) || (profileGroup.isEmpty())) {
				collapsibleHeader.remove(profileGroupChip);
				return;
			}
			profileGroupChip.setText(profileGroup);
		} else {
			if ((profileGroup != null) && (!profileGroup.isEmpty())) {
				profileGroupChip.setText(profileGroup);
				collapsibleHeader.add(profileGroupChip);
			}
		}
	}

	@Override
	public void setProfileGroupData(List<ProfileGroupDto> profileGroupData) {
		logger.info("ProfileFilterView().setProfileGroupData()");
		profileGroupCombo.clear();
		for (ProfileGroupDto dto : profileGroupData) {
			logger.info("ProfileFilterView().setProfileGroupData()->dto.getCode()=" + dto.getCode());
			profileGroupCombo.addItem(dto.getCode() + "-" + dto.getDescription(), dto);
		}
	}

	@Override
	public List<String> getSelectedProfileGroupKeys() {
		List<String> result = new ArrayList<String>();
		for (ProfileGroupDto dto : profileGroupCombo.getSelectedValues())
			result.add(dto.getWebSafeKey());
		return result;
	}

	@Override
	public void reset() {
		logger.info("ProfileFilterView().reset()");
	}
}
