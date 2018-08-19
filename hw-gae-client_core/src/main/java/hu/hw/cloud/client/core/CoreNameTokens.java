package hu.hw.cloud.client.core;

public class CoreNameTokens {

	public static final String HOME = "/home";
	public static final String LOGIN = "/login";
	public static final String REGISTER = "/register";
	public static final String SUCCESS = "/success";
	public static final String ACTIVATE = "/activate";
	public static final String UNAUTHORIZED = "/unauthorized";

	// Configuration
	public static final String CUSTOMER_CREATOR = "/customerCreator";
	public static final String CUSTOMER_EDITOR = "/customerEditor";
	public static final String HOTEL_EDITOR = "/hotelEditor";
	public static final String ROOMTYPE_EDITOR = "/roomTypeEditor";
	public static final String ROOM_EDITOR = "/roomEditor";
	public static final String USERS = "!users";
	public static final String USER_CONFIG = "/userconfig";
	public static final String USER_EDITOR = "/userEditor";
	public static final String ROLE_CONFIG = "/roleconfig";
	public static final String HOTEL_CONFIG = "/hotelconfig";
	public static final String SYSTEM_CONFIG = "!systemconfig";


	public static String getHome() {
		return HOME;
	}

	public static String getLogin() {
		return LOGIN;
	}

	public static String getRegister() {
		return REGISTER;
	}

	public static String getSuccess() {
		return SUCCESS;
	}

	public static String getActivate() {
		return ACTIVATE;
	}

	// Configuration
	public static String getHotelConfig() {
		return HOTEL_CONFIG;
	}

	public static String getUserConfig() {
		return USER_CONFIG;
	}

	public static String getRoleConfig() {
		return ROLE_CONFIG;
	}

	public static String getSystemConfig() {
		return SYSTEM_CONFIG;
	}
}
