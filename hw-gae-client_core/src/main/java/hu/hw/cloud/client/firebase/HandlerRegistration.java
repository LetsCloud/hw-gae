/**
 * 
 */
package hu.hw.cloud.client.firebase;

/**
 * @author robi
 *
 */
public interface HandlerRegistration {
	<R extends HandlerRegistration> R on();

	<R extends HandlerRegistration> R off();
}
