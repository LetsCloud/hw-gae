/**
 * 
 */
package hu.hw.cloud.client.core.pwa;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;

/**
 * @author robi
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class FcmData {

	@JsProperty
	public String icon;

	@JsProperty
	public String title;

	@JsProperty
	public String body;

	@JsProperty
	public String click_action;

	public FcmData() {

	}
}
