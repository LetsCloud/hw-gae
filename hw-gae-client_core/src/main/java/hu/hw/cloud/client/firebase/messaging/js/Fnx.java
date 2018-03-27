/**
 * 
 */
package hu.hw.cloud.client.firebase.messaging.js;

import com.google.gwt.core.client.JavaScriptObject;

import hu.hw.cloud.client.firebase.model.DataMessage;
import hu.hw.cloud.client.firebase.model.NotificationMessage;
import jsinterop.annotations.JsFunction;

/**
 * @author robi
 *
 */
public class Fnx {

	@FunctionalInterface
	@JsFunction
	public interface Arg {
		void apply(DataMessage val);
	}

	@FunctionalInterface
	@JsFunction
	public interface NoArg {
		void apply();
	}
}
