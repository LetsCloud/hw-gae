/**
 * 
 */
package hu.hw.cloud.client.fro.config.system;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

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
import hu.hw.cloud.client.fro.config.TableStore;

/**
 * @author CR
 *
 */
public class SystemConfigView extends ViewWithUiHandlers<SystemConfigUiHandlers>
		implements SystemConfigPresenter.MyView {
	private static Logger logger = Logger.getLogger(SystemConfigView.class.getName());

	interface Binder extends UiBinder<Widget, SystemConfigView> {
	}

	@UiField
	MaterialColumn tablePanel;

	@UiField
	MaterialDropDown mobileDropDown;

	@UiField
	MaterialCollection desktopMenu;

	@UiField
	MaterialButton addButton;

	@Inject
	SystemConfigView(Binder uiBinder) {
		logger.log(Level.INFO, "SystemConfigView()");
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(SystemConfigPresenter.SLOT_CONTENT, tablePanel);
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