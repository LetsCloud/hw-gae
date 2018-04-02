/**
 * 
 */
package hu.hw.cloud.client.fro.config;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialCollectionSecondary;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;

/**
 * @author robi
 *
 */
public abstract class AbstractConfigView extends ViewWithUiHandlers<ConfigUiHandlers>
		implements AbstractConfigPresenter.MyView {
	private static Logger logger = Logger.getLogger(AbstractConfigView.class.getName());

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, AbstractConfigView> {
	}

	@UiField
	MaterialColumn tablePanel;

	@UiField
	MaterialDropDown mobileDropDown;

	@UiField
	MaterialCollection desktopMenu;

	@UiField
	MaterialButton addButton;

	public AbstractConfigView() {
		logger.log(Level.INFO, "AbstractConfigView()");
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(AbstractConfigPresenter.SLOT_CONTENT, tablePanel);
	}

	@Override
	public void buildMenu() {
		mobileDropDown.clear();
		desktopMenu.clear();

		for (Map.Entry<Integer, TableStore> entry : getUiHandlers().getTableMap().entrySet()) {
			addMobileMenuItem(entry.getKey(), entry.getValue().getCaption());
			addDesktopMenuItem(entry.getKey(), entry.getValue().getCaption());
		}
	}

	private void addMobileMenuItem(Integer index, String caption) {
		MaterialLink link = new MaterialLink(caption);
		link.addClickHandler(handler -> {
			getUiHandlers().showTable(index);
		});
		mobileDropDown.add(link);
	}

	private void addDesktopMenuItem(Integer index, String caption) {

		MaterialCollectionItem item = desktopMenuItem(caption);
		item.addClickHandler(handler -> {
			getUiHandlers().showTable(index);
		});
		desktopMenu.add(item);
	}

	private MaterialCollectionItem desktopMenuItem(String caption) {
		MaterialLabel label = new MaterialLabel(caption);
		label.setPaddingTop(5);

		MaterialIcon icon = new MaterialIcon(IconType.KEYBOARD_ARROW_RIGHT);
		icon.setIconPosition(IconPosition.RIGHT);

		MaterialCollectionSecondary sec = new MaterialCollectionSecondary();
		sec.setPaddingBottom(10);
		sec.add(icon);

		MaterialCollectionItem item = new MaterialCollectionItem();
		item.setWaves(WavesType.DEFAULT);
		item.setPaddingRight(10);
		item.add(label);
		item.add(sec);

		return item;
	}

	@UiHandler("addButton")
	public void onAddClick(ClickEvent event) {
		getUiHandlers().addItem();
	}

	@Override
	public void setDesktopMenu(Integer index) {
		desktopMenu.setActive(index, true);
	}
}
