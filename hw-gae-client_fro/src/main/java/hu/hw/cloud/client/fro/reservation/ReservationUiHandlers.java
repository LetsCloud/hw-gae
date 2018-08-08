/**
 * 
 */
package hu.hw.cloud.client.fro.reservation;

import java.util.Map;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author robi
 *
 */
public interface ReservationUiHandlers extends UiHandlers {

	Map<Integer, AbstractResWidget<?>> getWidgetsMap();
	
	void showTable(Integer index);

}
