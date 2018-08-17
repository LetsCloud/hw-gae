package hu.hw.cloud.client.fro;

public class FroNameTokens {

	// Reservation
	public static final String CREATE_RESERVATION = "/createReservation";
	public static final String RESERVATION = "/reservation";

	// Configuration
	public static final String CUSTOMER_CONFIG = "/customerConfig";
	public static final String SYSTEM_CONFIG = "/systemConfig";
	public static final String COMMON_CONFIG = "/commonConfig";
	public static final String HOTEL_CONFIG = "/hotelConfig";

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
