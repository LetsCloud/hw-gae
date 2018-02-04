/**
 * 
 */
package hu.hw.cloud.client.core.pwa;

import java.util.logging.Logger;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;

import gwt.material.design.client.pwa.push.PushNotificationManager;
import gwt.material.design.client.pwa.serviceworker.DefaultServiceWorkerManager;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.jquery.client.api.Functions;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;
import hu.hw.cloud.shared.FcmService;

/**
 * @author CR
 *
 */
public class AppServiceWorkerManager extends DefaultServiceWorkerManager {
	private static Logger logger = Logger.getLogger(AppServiceWorkerManager.class.getName());

	private final EventBus eventBus;
	private final MessagingManager fcmManager;
	private final RestDispatch dispatch;
	private final FcmService fcmService;
	private String endpoint, auth, key;

	public AppServiceWorkerManager(String resource, EventBus eventBus, MessagingManager fcmManager,
			RestDispatch dispatch, FcmService fcmService) {
		super(resource);
		this.eventBus = eventBus;
		this.fcmManager = fcmManager;
		this.dispatch = dispatch;
		this.fcmService = fcmService;
		// Polling Interval should be every 1 minute
		/* setPollingInterval(1000); */
	}

	@Override
	public void onRegistered(ServiceWorkerRegistration registration) {
		fcmManager.useServiceWorker(registration);

		/*
		pushNotificationManager = new PushNotificationManager(registration);
		pushNotificationManager.load(param1 -> {
			if (param1 == null) {
				MaterialToast.fireToast("Not subscribed Push Notifications");
			} else {
				MaterialToast.fireToast("Subscribed to Push Notifications");
			}
		});
		*/
	}

	@Override
	protected void onServerFailing() {
		super.onServerFailing();
		String newURL = Window.Location.createUrlBuilder().setHash("NameTokens.MAINTENANCE").buildString();
		Window.Location.replace(newURL);
	}

	@Override
	public void onActivated() {
		super.onActivated();
		logger.info("AppServiceWorkerManager.onActivated()");
	}

	@Override
	protected void onOffline() {
		super.onOffline();
		logger.info("AppServiceWorkerManager.onOffline()");
		eventBus.fireEvent(new NetworkStatusEvent(false));
	}

	@Override
	protected void onOnline() {
		super.onOnline();
		logger.info("AppServiceWorkerManager.onOnline()");
		eventBus.fireEvent(new NetworkStatusEvent(true));
	}

	/*
	 * FCM 
	 */

	/**
	 * 
	 * @param callback
	 */
	public void requestFcbPermission(Functions.Func callback) {
		fcmManager.requestPermission(callback);
	}

	/**
	 * 
	 * @param callback
	 */
	public void getFcbToken(Functions.Func1<String> callback) {
		fcmManager.getToken(callback);
	}

	/**
	 * 
	 * @param iidToken
	 */
	public void fcmSubscribe(String iidToken) {
		dispatch.execute(fcmService.subscribe(iidToken, getUserAgent()), new AsyncCallback<Void>() {

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

	/**
	 * 
	 * @return
	 */
	public static native String getUserAgent() /*-{
												return $wnd.navigator.userAgent.toLowerCase();
												}-*/;
}
