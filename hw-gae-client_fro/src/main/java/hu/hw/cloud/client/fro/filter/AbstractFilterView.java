/**
 * 
 */
package hu.hw.cloud.client.fro.filter;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialChip;
import gwt.material.design.client.ui.MaterialCollapsibleHeader;
import gwt.material.design.client.ui.MaterialColumn;

import hu.hw.cloud.client.core.i18n.CoreMessages;

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

	@UiField
	protected MaterialCollapsibleHeader collapsibleHeader;

	@UiField
	protected MaterialColumn col1, col2;

	private MaterialChip onlyActiveChip;

	@UiField
	MaterialCheckBox onlyActiveCheckBox;

	public AbstractFilterView(CoreMessages i18nCore) {
		logger.info("AbstractFilterView()");

		initWidget(uiBinder.createAndBindUi(this));

		initView(i18nCore);
	}

	protected void initView(CoreMessages i18nCore) {
		initOnlyActiveFilter(i18nCore);
	}

	private void initOnlyActiveFilter(CoreMessages i18nCore) {
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