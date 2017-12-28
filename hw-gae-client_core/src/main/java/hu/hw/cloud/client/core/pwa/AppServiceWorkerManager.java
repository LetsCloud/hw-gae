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
import hu.hw.cloud.shared.NotificationService;

/**
 * @author CR
 *
 */
public class AppServiceWorkerManager extends DefaultServiceWorkerManager {

	private final EventBus eventBus;
	private final RestDispatch dispatch;
	private final NotificationService notificationService;
	private String endpoint, auth, key;
	private PushNotificationManager pushNotificationManager;

	public AppServiceWorkerManager(String resource, EventBus eventBus, RestDispatch dispatch,
			NotificationService notificationService) {
		super(resource);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.notificationService = notificationService;
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
		endpoint = subscription.endpoint;
		key = PushCryptoHelper.arrayBufferToBase64(subscription.getKey("p256dh"));
		auth = PushCryptoHelper.arrayBufferToBase64(subscription.getKey("auth"));

		dispatch.execute(notificationService.subscribeUser(endpoint, auth, key), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				MaterialToast.fireToast("Subscribed user to Server Web Push. Ready for receiving push notifications.");
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
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
