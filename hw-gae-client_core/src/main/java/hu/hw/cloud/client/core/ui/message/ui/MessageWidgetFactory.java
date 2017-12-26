/**
 * 
 */
package hu.hw.cloud.client.core.ui.message.ui;

import hu.hw.cloud.client.core.ui.message.Message;

/**
 * @author CR
 *
 */
public interface MessageWidgetFactory {
	MessageWidget createMessage(Message message);
}