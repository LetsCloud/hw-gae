/**
 * 
 */
package hu.hw.cloud.client.firebase.messaging;

import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jquery.client.api.Promise;
import hu.hw.cloud.client.firebase.messaging.js.Messaging;

/**
 * @author robi
 *
 */
public interface HasMessagingFeatures {
	Messaging getMessaging();

	void getToken(Functions.Func1<String> callback);
	void requestPermission(Functions.Func callback);
	void useServiceWorker(ServiceWorkerRegistration r);
}
