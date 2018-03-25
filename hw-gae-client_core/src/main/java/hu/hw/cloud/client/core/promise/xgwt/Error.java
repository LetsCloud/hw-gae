/**
 * 
 */
package hu.hw.cloud.client.core.promise.xgwt;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * @author robi
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public interface Error {
	@JsProperty
	String getCode();

	@JsProperty
	String getMessage();
}
