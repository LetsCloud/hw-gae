/**
 * 
 */
package hu.hw.cloud.client.firebase.messaging;

import com.google.gwt.core.client.JavaScriptObject;

import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import hu.hw.cloud.client.core.promise.xgwt.Fn;
import hu.hw.cloud.client.firebase.messaging.js.Fnx;
import hu.hw.cloud.client.firebase.messaging.js.Messaging;

/**
 * @author robi
 *
 */
public interface HasMessagingFeatures {
	Messaging getMessaging();

	void getToken(Fn.Arg<String> callback);
	void onTokenRefresh(Fn.Arg<String> callback);
	void onMessage(Fnx.Arg callback);
	void requestPermission(Fn.NoArg callback);
	void useServiceWorker(ServiceWorkerRegistration r);
}
