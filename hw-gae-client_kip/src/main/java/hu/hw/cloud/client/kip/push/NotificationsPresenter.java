/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.pwa.PwaManager;
import hu.hw.cloud.client.core.pwa.AppServiceWorkerManager;
import hu.hw.cloud.client.core.pwa.HasNetworkStatus;
import hu.hw.cloud.client.core.pwa.NetworkStatusEvent;
import hu.hw.cloud.client.kip.KipNameTokens;
import hu.hw.cloud.client.kip.app.KipAppPresenter;

/**
 * @author robi
 *
 */
public class NotificationsPresenter extends Presenter<NotificationsPresenter.MyView, NotificationsPresenter.MyProxy>
		implements NetworkStatusEvent.NetworkStatusHandler, NotificationsUiHandlers {

	interface MyView extends View, HasNetworkStatus, HasUiHandlers<NotificationsUiHandlers> {
	}

	PwaManager manager = PwaManager.getInstance();

	@ProxyStandard
	@NameToken(KipNameTokens.NOTIFICATIONS)
	interface MyProxy extends ProxyPlace<NotificationsPresenter> {
	}

	@Inject
	NotificationsPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		addRegisteredHandler(NetworkStatusEvent.TYPE, this);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	public void onNetworkStatus(NetworkStatusEvent event) {
		getView().updateUi(event.isOnline());
	}

	@Override
	public AppServiceWorkerManager getServiceWorkerManager() {
		if (manager.getServiceWorkerManager() instanceof AppServiceWorkerManager) {
			return (AppServiceWorkerManager) manager.getServiceWorkerManager();
		}
		GWT.log("Push Notification Manager is not yet registered");
		return null;
	}
}
