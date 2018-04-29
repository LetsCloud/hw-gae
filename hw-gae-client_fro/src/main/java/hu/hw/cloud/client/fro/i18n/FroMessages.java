/**
 * 
 */
package hu.hw.cloud.client.fro.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface FroMessages extends Messages {

	/*
	 * MAIN MENU
	 */

	@DefaultMessage("Dashboard")
	String mainMenuItemDashboard();

	
	@DefaultMessage("Reception")
	String mainMenuGroupReception();

	@DefaultMessage("Reservation")
	String mainMenuItemReservation();

	@DefaultMessage("Roomplan")
	String mainMenuItemRoomplan();

	
	@DefaultMessage("Cashier")
	String mainMenuGroupCashier();

	@DefaultMessage("Billing")
	String mainMenuItemBilling();

	
	@DefaultMessage("Configuration")
	String mainMenuGroupConfiguration();

	@DefaultMessage("Security config")
	String mainMenuItemSystemConfig();

	@DefaultMessage("Common config")
	String mainMenuItemCommonConfig();

	@DefaultMessage("Hotel config")
	String mainMenuItemHotelConfig();
	
	/*
	 * SYSTEM CONFIG 
	 */

	@DefaultMessage("System setup")
	String systemConfigSetup();

	@DefaultMessage("User groups")
	String systemConfigUserGroup();

	@DefaultMessage("Users")
	String systemConfigAppUser();

	@DefaultMessage("Choose data type")
	String systemConfigMobileSelect();

	@DefaultMessage("Roles")
	String systemConfigRoles();
	
	
	/*
	 * HOTEL CONFIG 
	 */

	@DefaultMessage("Hotels")
	String hotelConfigHotels();

	@DefaultMessage("Roomtypes")
	String hotelConfigRoomTypes();

	@DefaultMessage("Rooms")
	String hotelConfigRooms();
}
