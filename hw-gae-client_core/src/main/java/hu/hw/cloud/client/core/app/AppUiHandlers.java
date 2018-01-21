package hu.hw.cloud.client.core.app;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.client.firebase.messaging.MessagingManager;

public interface AppUiHandlers extends UiHandlers {
	void logout();

	MessagingManager getMessagingManager();
}
