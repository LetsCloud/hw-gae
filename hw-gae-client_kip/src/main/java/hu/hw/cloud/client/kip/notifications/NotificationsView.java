/**
 * 
 */
package hu.hw.cloud.client.kip.notifications;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.overlay.MaterialOverlay;
import gwt.material.design.client.pwa.manifest.constants.DisplayMode;
import gwt.material.design.client.pwa.manifest.js.AppInstaller;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCard;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialSwitch;
import gwt.material.design.client.ui.MaterialToast;

/**
 * @author robi
 *
 */
public class NotificationsView extends ViewWithUiHandlers<NotificationsUiHandlers>
		implements NotificationsPresenter.MyView {

	interface Binder extends UiBinder<Widget, NotificationsView> {
	}

	@UiField
	MaterialSwitch enablePushNotification;

	@UiField
	MaterialButton btnAdd;

	@UiField
	MaterialCard offlineCard;

	@UiField
	MaterialPanel onlinePanel;

	@UiField
	MaterialButton install;

	@UiField
	MaterialOverlay overlay;

	@UiField
	SimplePanel modalSlot;

	private AppInstaller appInstaller;

	@Inject
	NotificationsView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(NotificationsPresenter.SLOT_MODAL, modalSlot);		
	}

	@Override
	protected void onAttach() {
		super.onAttach();

		appInstaller = new AppInstaller(() -> overlay.open());
		if (appInstaller.isLaunched(DisplayMode.FULLSCREEN)) {
			install.setVisible(false);
		}
	}

	@Override
	public void updateUi(boolean online) {
		btnAdd.setEnabled(online);
		onlinePanel.setVisible(online);
		offlineCard.setVisible(!online);
	}

	@UiHandler("btnAdd")
	void onAdd(ClickEvent e) {
		getUiHandlers().createNotification();
	}

	@UiHandler("install")
	void onInstall(ClickEvent e) {
		appInstaller.prompt();
	}

	@UiHandler("gotIt")
	void onGotIt(ClickEvent e) {
		overlay.close();
	}

	@UiHandler("enablePushNotification")
	void enablePushNotification(ValueChangeEvent<Boolean> event) {
		if (event.getValue()) {
			getUiHandlers().getServiceWorkerManager().subscribe(() -> updateSwitch());
		} else {
			getUiHandlers().getServiceWorkerManager().unsubscribe(() -> updateSwitch());
		}
	}

	protected void updateSwitch() {
		enablePushNotification.setValue(getUiHandlers().getServiceWorkerManager().isSubscribed());
		if (getUiHandlers().getServiceWorkerManager().isSubscribed()) {
			MaterialToast.fireToast("Subscribed to Push Notification");
		} else {
			MaterialToast.fireToast("Unsubscribed to Push Notification");
		}
	}
}
