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
	@DefaultMessage("OK")
	String comOk();

	@DefaultMessage("Save")
	String comSave();
	
	@DefaultMessage("Cancel")
	String comCancel();

	@DefaultMessage("Required field")
	String comRequiredField();

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

	@DefaultMessage("Create User")
	String userEditorCreateTitle();

	@DefaultMessage("Modify User")
	String userEditorModifyTitle();

	@DefaultMessage("Email")
	String userEditorEmail();

	@DefaultMessage("Username")
	String userEditorUsername();

	@DefaultMessage("Password")
	String userEditorPassword();

}
