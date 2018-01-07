/**
 * 
 */
package hu.hw.cloud.client.firebase.messaging.js;

import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerRegistration;
import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jquery.client.api.Promise;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

/**
 * @author robi
 *
 */
@JsType(isNative = true, namespace = "firebase.messaging", name = "Messaging")
public class Messaging {

	public native Promise deleteToken(String token);

	@JsMethod
	public native Promise getToken();

	public native Functions.EventFunc onMessage();

	public native Functions.EventFunc onTokenRefresh();

	@JsMethod
	public native Promise requestPermission();

	public native Functions.EventFunc setBackgroundMessageHandler();
	
	public native void useServiceWorker(ServiceWorkerRegistration r);

}
