package hu.hw.cloud.client.fro;

public class FroNameTokens {

	// Reservation
	public static final String CREATE_RESERVATION = "/createReservation";
	public static final String RESERVATION = "/reservation";

	// Configuration
	public static final String PROFILE_CONFIG = "/profleConfig";
	public static final String SYSTEM_CONFIG = "/systemConfig";
	public static final String COMMON_CONFIG = "/commonConfig";
	public static final String HOTEL_CONFIG = "/hotelConfig";

	public static final String ORGANIZATION_DISPLAY = "/organizationDisplay";

	public static String getProfileConfig() {
		return PROFILE_CONFIG;
	}

	public static String getSystemConfig() {
		return SYSTEM_CONFIG;
	}

	public static String getCommonConfig() {
		return COMMON_CONFIG;
	}

	public static String getHotelConfig() {
		return HOTEL_CONFIG;
	}
}
