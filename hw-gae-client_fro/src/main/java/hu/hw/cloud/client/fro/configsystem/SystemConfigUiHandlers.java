/**
 * 
 */
package hu.hw.cloud.client.fro.configsystem;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author CR
 *
 */
public interface SystemConfigUiHandlers  extends UiHandlers {
	
	void showSystem();
	void showUsers();
	void showRoles();
	
	void createUser();

}
