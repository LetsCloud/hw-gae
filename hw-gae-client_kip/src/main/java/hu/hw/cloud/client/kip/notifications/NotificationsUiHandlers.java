/**
 * 
 */
package hu.hw.cloud.client.kip.notifications;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.client.core.pwa.AppServiceWorkerManager;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;

/**
 * @author robi
 *
 */
public interface NotificationsUiHandlers extends UiHandlers {

	AppServiceWorkerManager getServiceWorkerManager();

	MessagingManager getMessagingManager();
	
	void createNotification();
	
	void subToServer(String iidToken);
}
