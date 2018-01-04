/**
 * 
 */
package hu.hw.cloud.client.kip.notifications;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.client.core.pwa.AppServiceWorkerManager;

/**
 * @author robi
 *
 */
public interface NotificationsUiHandlers extends UiHandlers {

	AppServiceWorkerManager getServiceWorkerManager();
	
	void createNotification();
}
