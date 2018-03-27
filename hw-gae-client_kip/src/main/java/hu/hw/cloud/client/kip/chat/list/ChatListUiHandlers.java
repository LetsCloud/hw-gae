/**
 * 
 */
package hu.hw.cloud.client.kip.chat.list;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author robi
 *
 */
public interface ChatListUiHandlers extends UiHandlers {
	void loadData(String chatWebSafeKey);
	
	void onSelectChat(String chatWebSafeKey);
}
