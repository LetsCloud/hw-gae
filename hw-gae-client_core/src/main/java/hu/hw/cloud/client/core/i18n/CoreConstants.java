/**
 * 
 */
package hu.hw.cloud.client.core.i18n;

import java.util.Map;

import com.google.gwt.i18n.client.Constants;

/**
 * @author CR
 *
 */
public interface CoreConstants extends Constants {

	Map<String, String> communicationModeMap();

	Map<String, String> addressTypeMap();

	Map<String, String> inventoryTypeMap();

	Map<String, String> roomStatusMap();

	Map<String, String> erroCodeMap();
}
