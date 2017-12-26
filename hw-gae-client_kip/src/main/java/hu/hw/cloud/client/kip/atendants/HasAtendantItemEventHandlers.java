/**
 * 
 */
package hu.hw.cloud.client.kip.atendants;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author CR
 *
 */
public interface HasAtendantItemEventHandlers extends HasHandlers {
	public HandlerRegistration addAtendantItemEventHandler(AtendantItemEventHandler handler);
}
