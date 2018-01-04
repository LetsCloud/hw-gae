/**
 * 
 */
package hu.hw.cloud.client.kip.notifications;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.ui.MaterialToast;
import hu.hw.cloud.client.core.pwa.AppServiceWorkerManager;
import hu.hw.cloud.client.core.pwa.HasNetworkStatus;
import hu.hw.cloud.client.core.pwa.NetworkStatusEvent;
import hu.hw.cloud.client.kip.KipNameTokens;
import hu.hw.cloud.client.kip.app.KipAppPresenter;
import hu.hw.cloud.client.kip.push.PushPresenter;
import hu.hw.cloud.client.kip.push.PushPresenterFactory;
import hu.hw.cloud.shared.NotificationService;
import hu.hw.cloud.shared.rpc.Message;

/**
 * @author robi
 *
 */
public class NotificationsPresenter extends Presenter<NotificationsPresenter.MyView, NotificationsPresenter.MyProxy>
		implements NetworkStatusEvent.NetworkStatusHandler, NotificationsUiHandlers {

	interface MyView extends View, HasNetworkStatus, HasUiHandlers<NotificationsUiHandlers> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_MODAL = new SingleSlot<>();

	PwaManager manager = PwaManager.getInstance();

	private final RestDispatch dispatcher;
	private final NotificationService service;

	@ProxyStandard
	@NameToken(KipNameTokens.NOTIFICATIONS)
	interface MyProxy extends ProxyPlace<NotificationsPresenter> {
	}

	private final PushPresenter pushPresenter;

	@Inject
	NotificationsPresenter(EventBus eventBus, MyView view, MyProxy proxy, PushPresenterFactory pushPresenterFactory,
			RestDispatch dispatcher, NotificationService service) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		this.pushPresenter = pushPresenterFactory.createPushPresenter();
		this.service = service;
		this.dispatcher = dispatcher;

		addRegisteredHandler(NetworkStatusEvent.TYPE, this);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		setInSlot(SLOT_MODAL, pushPresenter);
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

	@Override
	public void createNotification() {
		pushPresenter.open();
		// checkServer();
	}
	
	private void checkServer() {
		dispatcher.execute(service.getMessage("Test"), new AsyncCallback<Message>() {

			@Override
			public void onSuccess(Message message) {
				MaterialToast.fireToast("Connected to server via RPC : Response (" + message.getMessage() + ")");
			}

			@Override
			public void onFailure(Throwable throwable) {
				MaterialToast.fireToast(throwable.getMessage());
			}
		});
	}
}
