/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.shared.dto.NotificationDto;

/**
 * @author robi
 *
 */
public interface PushUiHandlers extends UiHandlers {
    void push(NotificationDto notification);

}
