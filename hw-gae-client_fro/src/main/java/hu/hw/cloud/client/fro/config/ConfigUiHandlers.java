/**
 * 
 */
package hu.hw.cloud.client.fro.config;

import java.util.Map;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author robi
 *
 */
public interface ConfigUiHandlers  extends UiHandlers {

	Map<Integer, TableStore> getTableMap();
	
	void showTable(Integer index);
	
	void addItem();
}
