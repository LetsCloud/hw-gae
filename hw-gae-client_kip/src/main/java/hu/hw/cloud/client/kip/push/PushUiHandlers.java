/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.rpc.NotificationDTO;

/**
 * @author robi
 *
 */
public interface PushUiHandlers extends UiHandlers {
    void push(NotificationDTO notification);

}
