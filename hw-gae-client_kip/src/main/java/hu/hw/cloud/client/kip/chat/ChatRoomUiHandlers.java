/**
 * 
 */
package hu.hw.cloud.client.kip.chat;

import com.gwtplatform.mvp.client.UiHandlers;

import gwt.material.design.client.base.MaterialWidget;
import hu.hw.cloud.client.core.app.AppServiceWorkerManager;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;

/**
 * @author robi
 *
 */
public interface ChatRoomUiHandlers extends UiHandlers {

	AppServiceWorkerManager getServiceWorkerManager();

	MessagingManager getMessagingManager();
	
	void createChat(MaterialWidget source);
	
	void subToServer(String iidToken);
}
