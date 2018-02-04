/**
 * 
 */
package hu.hw.cloud.client.firebase.messaging;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;

import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.jquery.client.api.Event;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jquery.client.api.Functions.Func;

import hu.hw.cloud.client.firebase.Firebase;
import hu.hw.cloud.client.firebase.messaging.js.Messaging;

/**
 * @author robi
 *
 */
public class MessagingManager implements HasMessagingFeatures {
	private static Logger logger = Logger.getLogger(MessagingManager.class.getName());

	private Firebase firebase;

	public MessagingManager(Firebase firebase) {
		this.firebase = firebase;

		firebase.messaging().onMessage((payload) -> {
			logger.log(Level.INFO, "firebase.messaging().onMessage()->payload=" + JsonUtils.stringify(payload, null));
		});
	}

	@Override
	public void getToken(Functions.Func1<String> callback) {
		logger.log(Level.INFO, "MessagingManager.getToken()");
		getMessaging().getToken().then(object -> {
			logger.log(Level.INFO, "getMessaging().getToken().then(object=" + object);
			String token = (String) object;
			callback.call(token);
			return true;
		});
	}

	@Override
	public Messaging getMessaging() {
		if (firebase != null) {
			return firebase.messaging();
		} else {
			GWT.log("Firebase is not yet registered", new IllegalStateException());
		}
		return null;
	}

	@Override
	public void useServiceWorker(ServiceWorkerRegistration r) {
		firebase.messaging().useServiceWorker(r);
	}

	@Override
	public void requestPermission(Func callback) {
		logger.log(Level.INFO, "MessagingManager.requestPermission()");
		getMessaging().requestPermission().then(object -> {
			callback.call();
			return true;
		});
	}

	protected void onMessage() {
		logger.log(Level.INFO, "onMessage()");
	}
}
