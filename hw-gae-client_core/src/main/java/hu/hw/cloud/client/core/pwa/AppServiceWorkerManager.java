/**
 * 
 */
package hu.hw.cloud.client.core.pwa;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;

import gwt.material.design.client.pwa.push.PushNotificationManager;
import gwt.material.design.client.pwa.push.helper.PushCryptoHelper;
import gwt.material.design.client.pwa.push.js.PushSubscription;
import gwt.material.design.client.pwa.serviceworker.DefaultServiceWorkerManager;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.jquery.client.api.Functions;
import hu.hw.cloud.shared.FcmService;

/**
 * @author CR
 *
 */
public class AppServiceWorkerManager extends DefaultServiceWorkerManager {

	private final EventBus eventBus;
	private final RestDispatch dispatch;
	private final FcmService fcmService;
	private String endpoint, auth, key;
	private PushNotificationManager pushNotificationManager;

	public AppServiceWorkerManager(String resource, EventBus eventBus, RestDispatch dispatch,
			FcmService fcmService) {
		super(resource);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.fcmService = fcmService;
		// Polling Interval should be every 1 minute
		/* setPollingInterval(1000); */
	}

	@Override
	public void onRegistered(ServiceWorkerRegistration registration) {
		pushNotificationManager = new PushNotificationManager(registration);
		pushNotificationManager.load(param1 -> {
			if (param1 == null) {
				MaterialToast.fireToast("Not subscribed Push Notifications");
			} else {
				MaterialToast.fireToast("Subscribed to Push Notifications");
			}
		});
	}

	public void subscribe(Functions.Func callback) {
		pushNotificationManager.subscribe(true,
				"BAvr2GL1EQdLnxgQDVeZSXnsWYNSaBbIkq4DsWQXwnpGrqXoGp_7YK0CiSPvszzPnAj-D49Ne-zKDBRWHHXBL1c",
				subscription -> {
					if (subscription != null) {
						sendSubscriptionToServer(subscription);
						callback.call();
					}
				});
	}

	protected void sendSubscriptionToServer(PushSubscription subscription) {
	}

	public void unsubscribe(Functions.Func callback) {
		pushNotificationManager.unsubscribe(() -> callback.call());
	}

	public boolean isSubscribed() {
		return pushNotificationManager.isSubscribed();
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
	}

	@Override
	protected void onOffline() {
		super.onOffline();
		eventBus.fireEvent(new NetworkStatusEvent(false));
	}

	@Override
	protected void onOnline() {
		super.onOnline();
		eventBus.fireEvent(new NetworkStatusEvent(true));
	}

	public PushNotificationManager getPushNotificationManager() {
		return pushNotificationManager;
	}
}
