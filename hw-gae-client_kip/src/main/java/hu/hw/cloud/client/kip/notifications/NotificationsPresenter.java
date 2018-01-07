/**
 * 
 */
package hu.hw.cloud.client.kip.notifications;

import java.util.logging.Level;
import java.util.logging.Logger;

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
import hu.hw.cloud.client.firebase.Config;
import hu.hw.cloud.client.firebase.Firebase;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;
import hu.hw.cloud.client.kip.KipNameTokens;
import hu.hw.cloud.client.kip.app.KipAppPresenter;
import hu.hw.cloud.client.kip.push.PushPresenter;
import hu.hw.cloud.client.kip.push.PushPresenterFactory;
import hu.hw.cloud.shared.FcmService;

/**
 * @author robi
 *
 */
public class NotificationsPresenter extends Presenter<NotificationsPresenter.MyView, NotificationsPresenter.MyProxy>
		implements NetworkStatusEvent.NetworkStatusHandler, NotificationsUiHandlers {
	private static Logger logger = Logger.getLogger(NotificationsPresenter.class.getName());

	interface MyView extends View, HasNetworkStatus, HasUiHandlers<NotificationsUiHandlers> {
		void resetUI();
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_MODAL = new SingleSlot<>();

	PwaManager manager = PwaManager.getInstance();

	private final PushPresenter pushPresenter;
	private final RestDispatch dispatcher;
	private final FcmService fcmService;
	private MessagingManager messagingManager;

	@ProxyStandard
	@NameToken(KipNameTokens.NOTIFICATIONS)
	interface MyProxy extends ProxyPlace<NotificationsPresenter> {
	}

	@Inject
	NotificationsPresenter(EventBus eventBus, MyView view, MyProxy proxy, PushPresenterFactory pushPresenterFactory,
			RestDispatch dispatcher, FcmService fcmService) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);

		this.pushPresenter = pushPresenterFactory.createPushPresenter();
		this.fcmService = fcmService;
		this.dispatcher = dispatcher;

		addRegisteredHandler(NetworkStatusEvent.TYPE, this);
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		setInSlot(SLOT_MODAL, pushPresenter);

		logger.log(Level.INFO, "NotificationsPresenter.onReveal()");

		Config config = new Config();
		config.setApiKey("AIzaSyDeeu6_zljBv-yq93OIT54ZUEdkZKZCmz8");
		config.setAuthDomain("hw-cloud1.firebaseapp.com");
		config.setDatabaseURL("https://hw-cloud1.firebaseio.com");
		config.setProjectId("hw-cloud1");
		config.setStorageBucket("hw-cloud1.appspot.com");
		config.setMessagingSenderId("489469080035");
		Firebase firebase = Firebase.initializeApp(config);
		logger.log(Level.INFO, "NotificationsPresenter.onBind().firebase.getName()" + firebase.getName());

		messagingManager = new MessagingManager(firebase);
		logger.log(Level.INFO, "NotificationsPresenter.onReveal().getMessagingManager()");

		// messagingManager.useServiceWorker(getServiceWorkerManager().getServiceWorkerRegistration());
		// logger.log(Level.INFO, "NotificationsPresenter.onReveal().useServiceWorker");

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
	}

	@Override
	public MessagingManager getMessagingManager() {
		return messagingManager;
	}

	@Override
	public void subToServer(String iidToken) {
		dispatcher.execute(fcmService.subscribe(iidToken), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void response) {
				MaterialToast.fireToast("Sussecfull subscription!");
			}

			@Override
			public void onFailure(Throwable throwable) {
				MaterialToast.fireToast(throwable.getMessage());
			}
		});
	}

}
