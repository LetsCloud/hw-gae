/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
public class DataContainer {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataContainer.class.getName());

	public static final Date BUSINESS_DATE = DateUtils.getDate(2017, 0, 8);
	// public static final DateTime BUSINESS_DATE = new DateTime(2017, 1, 8, 0, 0, 0);

	protected AccountData accountData;
	// private AppUserData userData;
	protected CurrencyData currencyData;
	// private VatRateData vatRateData;
	// private ServiceData serviceData;
	// private DiscountReasonData discountReasonData;
	// private CustomerData customerData;
	protected GuestData guestData;

	protected HotelData hotelData;
	protected RoomTypeData roomTypeData;
	protected RoomData roomData;
	// private RatePlanData ratePlanData;
	protected ReservationData reservationData;

	protected void build() {
		LOGGER.info("build()");
		currencyData.build();
		guestData.build();
		hotelData.build();
		roomTypeData.build();
		roomData.build();
		reservationData.build();
	}

}
