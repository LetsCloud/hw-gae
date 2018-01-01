/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.client.core.pwa.AppServiceWorkerManager;

/**
 * @author robi
 *
 */
public interface NotificationsUiHandlers extends UiHandlers {

	AppServiceWorkerManager getServiceWorkerManager();
}
