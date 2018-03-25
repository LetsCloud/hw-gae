/**
 * 
 */
package hu.hw.cloud.client.core.pwa.js;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author robi
 *
 */
public class MessageEvent extends JavaScriptObject {

	protected MessageEvent() {

	}

	public native final String getData()/*-{
										return this.data;
										}-*/;

	public native final String getOrigin()/*-{
											return this.origin;
											}-*/;

}