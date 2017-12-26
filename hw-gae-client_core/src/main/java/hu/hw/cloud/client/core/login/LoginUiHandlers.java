package hu.hw.cloud.client.core.login;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LoginUiHandlers extends UiHandlers {
	void login(String accountId, String username, String password, Boolean remeberMe);
}
