/**
 * 
 */
package hu.hw.cloud.client.core.app;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;

import gwt.material.design.client.pwa.serviceworker.ServiceEvent;
import gwt.material.design.client.pwa.serviceworker.ServiceWorkerManager;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.client.ui.MaterialToast;
import hu.hw.cloud.client.core.promise.xgwt.Fn;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;
import hu.hw.cloud.client.firebase.messaging.js.Fnx;
import hu.hw.cloud.shared.FcmService;

/**
 * @author CR
 *
 */
public class AppServiceWorkerManager extends ServiceWorkerManager {
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
	public boolean onRegistered(ServiceEvent event, ServiceWorkerRegistration registration) {
		boolean result = super.onRegistered(event, registration);
		logger.info("Service Worker is registered");

		if (result)
			fcmManager.useServiceWorker(registration);

		return result;
	}

	/*
	 * FCM
	 */

	/**
	 * 
	 * @param callback
	 */
	public void requestFcbPermission(Fn.NoArg callback) {
		logger.info("AppServiceWorkerManager.requestFcbPermission()");
		fcmManager.requestPermission(callback);
	}

	/**
	 * 
	 * @param callback
	 */
	public void getFcbToken(Fn.Arg<String> callback) {
		logger.info("AppServiceWorkerManager.getFcbToken()");
		fcmManager.getToken(callback);
	}

	/**
	 * 
	 * @param callback
	 */
	public void onFcmTokenRefresh(Fn.Arg<String> callback) {
		logger.info("AppServiceWorkerManager.onFcmTokenRefresh()");
		fcmManager.onTokenRefresh(callback);
	}

	/**
	 * 
	 * @param callback
	 */
	public void onFcmMessage(Fnx.Arg callback) {
		logger.info("AppServiceWorkerManager.onFcmMessage()");
		fcmManager.onMessage(callback);
	}

	/**
	 * 
	 * @param iidToken
	 */
	public void fcmSubscribe(String iidToken) {
		logger.info("AppServiceWorkerManager.fcmSubscribe()");
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
