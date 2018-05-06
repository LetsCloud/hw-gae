/**
 * 
 */
package hu.hw.cloud.client.core.i18n;

import com.google.gwt.i18n.client.Messages;

/**
 * @author CR
 *
 */
public interface CoreMessages extends Messages {
	/*
	 * Common
	 */
	@DefaultMessage("Yes")
	String comYes();

	@DefaultMessage("No")
	String comNo();

	@DefaultMessage("OK")
	String comOk();

	@DefaultMessage("Save")
	String comSave();

	@DefaultMessage("Cancel")
	String comCancel();

	@DefaultMessage("Required field")
	String comRequiredField();

	/*
	 * Server data exceptions
	 */
	@DefaultMessage("Login")
	String UNIQUE_INDEX_CONFLICT();

	/*
	 * LOGIN
	 */
	@DefaultMessage("Login")
	String loginCaption();

	@DefaultMessage("Account")
	String loginAccount();

	@DefaultMessage("Username")
	String loginUsername();

	@DefaultMessage("password")
	String loginPassword();

	@DefaultMessage("LOG IN")
	String loginSubmit();

	@DefaultMessage("Keep me logged in")
	String loginKeepMe();

	@DefaultMessage("Create an Account")
	String loginCreate();

	@DefaultMessage("Insufficient authentication!")
	String loginInsufficientAuthentication();

	@DefaultMessage("Username not found!")
	String loginUsernameNotFound();

	@DefaultMessage("Bad credentials!")
	String loginErrorBadCredentials();

	@DefaultMessage("The user is disabled!")
	String loginErrorDisabledUser();

	@DefaultMessage("Umknwon login problem!")
	String loginErrorUnknownProblem();

	/*
	 * REGISTER
	 */
	@DefaultMessage("Register")
	String regCaption();

	@DefaultMessage("Account name")
	String regAccountName();

	@DefaultMessage("Street and number")
	String regStreet();

	@DefaultMessage("City")
	String regCity();

	@DefaultMessage("Postcode")
	String regPostcode();

	@DefaultMessage("Country")
	String regCountry();

	@DefaultMessage("Username")
	String regUsername();

	@DefaultMessage("Your email address")
	String regEmail();

	@DefaultMessage("Repeat email")
	String regEmail2();

	@DefaultMessage("Password")
	String regPassword();

	@DefaultMessage("Repeat password")
	String regPassword2();

	@DefaultMessage("Submit")
	String regSubmit();

	/*
	 * SUCCESS
	 */
	@DefaultMessage("Registration Success - Congratulations!")
	String sucCaption();

	@DefaultMessage("You have successfully complited your registration.")
	String sucLine1();

	@DefaultMessage("Your account id: {0}")
	String sucLine2(String id);

	@DefaultMessage("Please check your email as we have sent you all your "
			+ "registration details and activate your account!")
	String sucLine3();

	/*
	 * ACTIVATE
	 */
	@DefaultMessage("Account activation")
	String actCaption();

	@DefaultMessage("Your account is now active!")
	String actLine1();

	@DefaultMessage("Clicking on the link below you can login and start your work:")
	String actLine2();

	/*
	 * SIDE NAV
	 */
	@DefaultMessage("Configuration")
	String mainMenuGroupConfig();

	@DefaultMessage("Hotels")
	String mainMenuItemHotels();

	@DefaultMessage("Users")
	String mainMenuItemUsers();

	@DefaultMessage("Roles")
	String mainMenuItemRoles();

	
	/*
	 * SIDE PROFILE
	 */
	@DefaultMessage("Account setup")
	String accountMenuItemSetup();

	@DefaultMessage("System setup")
	String accountMenuItemSystem();

	@DefaultMessage("User profile")
	String userMenuItemProfile();

	@DefaultMessage("Switch user")
	String userMenuItemSwitch();

	@DefaultMessage("Log Out")
	String userMenuItemLogout();

	
	/*
	 * BREADCRUMBS
	 */
	
	@DefaultMessage("Home")
	String breadcrumbsHome();
	
	@DefaultMessage("Users")
	String breadcrumbsUsersTable();
	
	@DefaultMessage("Editor")
	String breadcrumbsUserEditor();

	
	/*
	 * DASHBOARD
	 */
	@DefaultMessage("Dashboard")
	String pageDashboardTitle();

	/*
	 * USERS TABLE
	 */

	@DefaultMessage("Users")
	String usersTableTitle();

	@DefaultMessage("Username")
	String usersTableUsername();

	@DefaultMessage("Email")
	String usersTableEmail();

	
	/*
	 * USER EDITOR
	 */

	@DefaultMessage("Create User Profile")
	String userEditorCreateTitle();

	@DefaultMessage("Modify User Profile")
	String userEditorModifyTitle();

	@DefaultMessage("Name")
	String userEditorName();

	@DefaultMessage("Title")
	String userEditorTitle();

	@DefaultMessage("Email")
	String userEditorEmail();

	@DefaultMessage("Code")
	String userEditorCode();

	@DefaultMessage("Username")
	String userEditorUsername();

	@DefaultMessage("Enabled")
	String userEditorEnabled();
	
	@DefaultMessage("Admin user")
	String userEditorIsAdmin();
	
	@DefaultMessage("Permitted hotels")
	String userEditorAvailableHotels();
	
	@DefaultMessage("Chhoose a hotel")
	String userEditorChooseHotel();
	
	@DefaultMessage("Deafult hotel")
	String userEditorDefaultHotel();
	
	@DefaultMessage("Chat groups")
	String userEditorChatGroups();
	
	@DefaultMessage("Choose a group")
	String userEditorChooseGroup();
	
	@DefaultMessage("Password")
	String userEditorPassword();

	/*
	 * USER GROUPS TABLE
	 */

	@DefaultMessage("User Groups")
	String userGroupTableTitle();

	@DefaultMessage("Name")
	String userGroupTableName();

	
	/*
	 * USER_GROUP EDITOR
	 */

	@DefaultMessage("Create User Group")
	String userGroupEditorCreateTitle();

	@DefaultMessage("Modify User Group")
	String userGroupEditorModifyTitle();

	@DefaultMessage("Name")
	String userGroupEditorName();

	
	/*
	 * HOTEL TABLE
	 */
	
	@DefaultMessage("Hotels Data")
	String hotelsTableTitle();
	
	@DefaultMessage("Code")
	String hotelsTableCode();

	@DefaultMessage("Name")
	String hotelsTableName();

	
	/*
	 * HOTEL EDITOR
	 */

	@DefaultMessage("Create a Hotel")
	String hotelEditorCreateTitle();

	@DefaultMessage("Modify Hotel Data")
	String hotelEditorModifyTitle();

	@DefaultMessage("Code")
	String hotelEditorCode();

	@DefaultMessage("Name")
	String hotelEditorName();

	
	/*
	 * ROOMTYPES TABLE
	 */
	
	@DefaultMessage("Room Type Data")
	String roomTypesTableTitle();
	
	@DefaultMessage("Choose a hotel")
	String roomTypesTableHotelsPlaceholder();
	
	@DefaultMessage("Selected hotel")
	String roomTypesTableHotelsLabel();
	
	@DefaultMessage("Only active types")
	String roomTypesTableOnlyActive();
	
	@DefaultMessage("Code")
	String roomTypesTableCode();

	@DefaultMessage("Name")
	String roomTypesTableName();

	@DefaultMessage("InventoryType")
	String roomTypesTableInventoryType();

	@DefaultMessage("Number Of Rooms")
	String roomTypesTableNumberOfRooms();

	@DefaultMessage("Active")
	String roomTypesTableActive();

	@DefaultMessage("Inactive")
	String roomTypesTableInactive();

	
	/*
	 * ROOMTYPE FILTER
	 */
	
	@DefaultMessage("Choose inventory type")
	String roomTypeFilterInventoryTypePlaceholder();
	
	@DefaultMessage("Selected inventory type")
	String roomTypeFilterInventoryTypeLabel();

	
	/*
	 * ROOMTYPE EDITOR
	 */

	@DefaultMessage("Create room type")
	String roomTypeEditorCreateTitle();

	@DefaultMessage("Modify room type")
	String roomTypeEditorModifyTitle();

	@DefaultMessage("Active")
	String roomTypeEditorActive();

	@DefaultMessage("Code")
	String roomTypeEditorCode();

	@DefaultMessage("Name")
	String roomTypeEditorName();

	@DefaultMessage("Description")
	String roomTypeEditorDescription();

	@DefaultMessage("Channel manager/Booking engine")
	String roomTypeEditorChmGroup();

	@DefaultMessage("Inventory Type")
	String roomTypeEditorInventoryType();

	@DefaultMessage("Choose inventory type")
	String roomTypeEditorPlaceholderInventoryType();

	@DefaultMessage("Housekeeping")
	String roomTypeEditorHkGroup();

	@DefaultMessage("Number of beds")
	String roomTypeEditorNumOfBeds();

	@DefaultMessage("Number of extra beds")
	String roomTypeEditorNumOfXtrBeds();

	@DefaultMessage("Cleaning factor")
	String roomTypeEditorCleaningFactor();
	
	/*
	 * ROOMS TABLE
	 */
	
	@DefaultMessage("Rooms table")
	String roomsTableTitle();
	
	@DefaultMessage("Code")
	String roomsTableCode();
	
	@DefaultMessage("Floor")
	String roomsTableFloor();

	@DefaultMessage("Type")
	String roomsTableType();

	
	/*
	 * ROOM FILTER
	 */
	
	@DefaultMessage("Selected floor")
	String roomFilterFloorLabel();
	
	@DefaultMessage("Choose floor")
	String roomFilterFloorPlaceholder();
	
	@DefaultMessage("Floor=")
	String roomFilterFloor();
	
	@DefaultMessage("Selected Room Types")
	String roomFilterRoomTypesLabel();
	
	@DefaultMessage("Choose Room Type")
	String roomFilterRoomTypesPlaceholder();

	
	/*
	 * ROOM EDITOR
	 */

	@DefaultMessage("Create room")
	String roomEditorCreateTitle();

	@DefaultMessage("Modify room")
	String roomEditorModifyTitle();

	@DefaultMessage("Code")
	String roomEditorCode();

	@DefaultMessage("Floor")
	String roomTypeEditorFloor();
	
	@DefaultMessage("Choose Room Type")
	String roomEditorChooseRoomtype();

	@DefaultMessage("Room Type")
	String roomEditorRoomtype();
	
	@DefaultMessage("Description")
	String roomEditorDescription();
	
	@DefaultMessage("Availability")
	String roomEditorAvailability();
	
	@DefaultMessage("From Date")
	String roomEditorAvailabilityFromDate();
	
	@DefaultMessage("Add")
	String roomEditorAvailabilityAdd();
	
	@DefaultMessage("Remove")
	String roomEditorAvailabilityRemove();
	
	/*
	 * SEND MESSAGE
	 */

	@DefaultMessage("Type a message")
	String sendMessagePlaceHolder();

	
	/*
	 * CREATE CHAT
	 */

	@DefaultMessage("Create a Chat")
	String createChatHeader();

	@DefaultMessage("Feel free to communicate")
	String createChatSubHeader();

	@DefaultMessage("Invited groups and users")
	String createChatInvited();
	
	@DefaultMessage("Choose group or user")
	String createChatToInvite();
	
	@DefaultMessage("Groups")
	String createChatGroupGroup();

	@DefaultMessage("Users")
	String createChatUserGroup();

}
