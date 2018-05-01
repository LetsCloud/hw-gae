/**
 * 
 */
package hu.hw.cloud.client.core.menu;

import com.gwtplatform.mvp.client.UiHandlers;

import hu.hw.cloud.client.core.event.ContentPushEvent.MenuState;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
public interface MenuUiHandlers extends UiHandlers {

	Boolean canReveal(String permissions);

	void setContentPush(MenuState menuState);
	
	void logout();
	
	void referesh();
	
	void setCurrentHotel(HotelDto hotel);
}