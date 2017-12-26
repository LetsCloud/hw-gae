/**
 * 
 */
package hu.hw.cloud.client.fro.configsystem;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
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
import hu.hw.cloud.client.fro.i18n.FroMessages;

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
	MaterialColumn contentPanel;

	@UiField
	MaterialDropDown mobileDropDown;

	@UiField
	MaterialCollection desktopMenu;

	@UiField
	SimplePanel userEditPanel;

	@UiField
	MaterialButton addButton;

	private final FroMessages i18n;

	@Inject
	SystemConfigView(Binder uiBinder, FroMessages i18n) {
		logger.log(Level.INFO, "SystemConfigView()");
		initWidget(uiBinder.createAndBindUi(this));

		this.i18n = i18n;

		bindSlot(SystemConfigPresenter.SLOT_CONTENT, contentPanel);
		bindSlot(SystemConfigPresenter.SLOT_EDITOR, userEditPanel);

		initMobileDropDown();
	}

	private void initMobileDropDown() {
		mobileDropDown.clear();

		MaterialLink setupLink = new MaterialLink(i18n.systemConfigSetup());
		setupLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.log(Level.INFO, "SystemConfigView.onClick()");
				getUiHandlers().showSystem();
			}
		});
		mobileDropDown.add(setupLink);

		MaterialLink usersLink = new MaterialLink(i18n.systemConfigUsers());
		usersLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.log(Level.INFO, "SystemConfigView.onClick()");
				getUiHandlers().showUsers();
			}
		});
		mobileDropDown.add(usersLink);

		MaterialLink rolesLink = new MaterialLink(i18n.systemConfigRoles());
		rolesLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.log(Level.INFO, "SystemConfigView.onClick()");
				getUiHandlers().showRoles();
			}
		});
		mobileDropDown.add(rolesLink);

		desktopMenu.clear();
		MaterialCollectionItem setupItem = desktopMenuItem(i18n.systemConfigSetup());
		setupItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.log(Level.INFO, "SystemConfigView.onClick()");
				getUiHandlers().showSystem();
			}
		});
		desktopMenu.add(setupItem);

		MaterialCollectionItem usersItem = desktopMenuItem(i18n.systemConfigUsers());
		usersItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.log(Level.INFO, "SystemConfigView.onClick()");
				getUiHandlers().showUsers();
			}
		});
		desktopMenu.add(usersItem);

		MaterialCollectionItem rolesItem = desktopMenuItem(i18n.systemConfigRoles());
		rolesItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				logger.log(Level.INFO, "SystemConfigView.onClick()");
				getUiHandlers().showRoles();
			}
		});
		desktopMenu.add(rolesItem);
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

	@Override
	public void setContent(Widget w) {
		contentPanel.add(w);
	}

	@Override
	public void refreshX() {
		// TODO Auto-generated method stub

	}

	@UiHandler("addButton")
	public void onAddClick(ClickEvent event) {
		getUiHandlers().createUser();
	}

	@Override
	public void setDesktopMenu(int index) {
		logger.log(Level.INFO, "SystemConfigView.setDesktopMenu()->index=" + index);
		desktopMenu.setActive(index, true);
	}
}
