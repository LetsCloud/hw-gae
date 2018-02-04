/**
 * 
 */
package hu.hw.cloud.client.fro.configsystem;

import java.util.Map;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author CR
 *
 */
public interface SystemConfigUiHandlers  extends UiHandlers {

	Map<Integer, TableStore> getTableMap();
	
	void showTable(Integer index);
	
	void addItem();
}
