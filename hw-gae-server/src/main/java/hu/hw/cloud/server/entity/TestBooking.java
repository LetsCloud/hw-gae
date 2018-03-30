/**
 * 
 */
package hu.hw.cloud.server.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import hu.hw.cloud.server.entity.hotel.HotelChild;
import hu.hw.cloud.server.utils.DateUtils;

/**
 * @author CR
 *
 */
@Entity
public class TestBooking extends HotelChild {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestBooking.class.getName());

	public static Random random = new Random();
	public static List<String> markets = Arrays.asList("BAR", "FIT", "GOV", "WALKIN", "WED", "INC");
	public static List<String> channels = Arrays.asList("Agoda", "Booking.com", "Expedia");
	public static List<String> sourcess = Arrays.asList("BAR", "Córdoba", "La Plata");
	public static List<String> types = Arrays.asList("BAR", "Córdoba", "La Plata");
	public static List<String> countries = Arrays.asList("BAR", "Córdoba", "La Plata");
	public static List<String> vipCodes = Arrays.asList("BAR", "Córdoba", "La Plata");
	public static List<String> customers = Arrays.asList("BAR", "Córdoba", "La Plata");
	public static List<String> rates = Arrays.asList("BAR", "Córdoba", "La Plata");
	public static List<String> roomTypes = Arrays.asList("BAR", "Córdoba", "La Plata");
	public static List<Double> cityTaxes = Arrays.asList(800d, 800d, 800d);
	public static List<Double> grossRoomRevenues = Arrays.asList(25000d, 20000d, 15000d);
	public static List<Double> netRoomRevenues = Arrays.asList(21186d, 16949d, 12712d);
	public static List<Double> grossBreakfastRevenues = Arrays.asList(5000d, 5000d, 5000d);
	public static List<Double> netBreakfastRevenues = Arrays.asList(4237d, 4237d, 4237d);

	public static Comparator<TestBooking> ORDER_BY_BOOKINGDATE = new Comparator<TestBooking>() {
		public int compare(TestBooking one, TestBooking other) {
			return one.getBookingDate().compareTo(other.getBookingDate());
		}
	};

	public static Comparator<TestBooking> ORDER_BY_ARRIVALDATE = new Comparator<TestBooking>() {
		public int compare(TestBooking one, TestBooking other) {
			return one.getArrival().compareTo(other.getArrival());
		}
	};

	@Index
	private Date bookingDate;
	private Date arrival;
	private Date departure;
	private Integer nights;
	private String market;
	private String channel;
	private String source;
	private String type;
	private String country;
	private String vipCode;
	private String customer;
	private String rate;
	private String roomType;
	private Double cityTax;
	private Double grossRoomRevenue;
	private Double netRoomRevenue;
	private Double grossBreakfastRevenue;
	private Double netBreakfastRevenue;

	public TestBooking() {
	}

	public TestBooking(Date bookingDate, Date arrival, Integer nights) {
		this();
		this.bookingDate = bookingDate;
		this.arrival = arrival;
		this.nights = nights;
		this.departure = DateUtils.addDays(arrival, nights);
		this.market = markets.get(random.nextInt(markets.size()));
		this.channel = channels.get(random.nextInt(channels.size()));
		this.source = sourcess.get(random.nextInt(sourcess.size()));
		this.type = types.get(random.nextInt(types.size()));
		this.country = countries.get(random.nextInt(countries.size()));
		this.vipCode = vipCodes.get(random.nextInt(vipCodes.size()));
		this.customer = customers.get(random.nextInt(customers.size()));
		this.rate = rates.get(random.nextInt(rates.size()));
		this.roomType = roomTypes.get(random.nextInt(roomTypes.size()));
		this.cityTax = cityTaxes.get(random.nextInt(cityTaxes.size()));
		this.grossRoomRevenue = grossRoomRevenues.get(random.nextInt(grossRoomRevenues.size()));
		this.netRoomRevenue = netRoomRevenues.get(random.nextInt(netRoomRevenues.size()));
		this.grossBreakfastRevenue = grossBreakfastRevenues.get(random.nextInt(grossBreakfastRevenues.size()));
		this.netBreakfastRevenue = netBreakfastRevenues.get(random.nextInt(netBreakfastRevenues.size()));

		// LOGGER.info("bookingDate=" + bookingDate + ", arrival=" + arrival +
		// ", nights=" + nights + ", departure="
		// + departure);
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public Integer getNights() {
		return nights;
	}

	public void setNights(Integer nights) {
		this.nights = nights;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVipCode() {
		return vipCode;
	}

	public void setVipCode(String vipCode) {
		this.vipCode = vipCode;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Double getCityTax() {
		return cityTax;
	}

	public void setCityTax(Double cityTax) {
		this.cityTax = cityTax;
	}

	public Double getGrossRoomRevenue() {
		return grossRoomRevenue;
	}

	public void setGrossRoomRevenue(Double grossRoomRevenue) {
		this.grossRoomRevenue = grossRoomRevenue;
	}

	public Double getNetRoomRevenue() {
		return netRoomRevenue;
	}

	public void setNetRoomRevenue(Double netRoomRevenue) {
		this.netRoomRevenue = netRoomRevenue;
	}

	public Double getGrossBreakfastRevenue() {
		return grossBreakfastRevenue;
	}

	public void setGrossBreakfastRevenue(Double grossBreakfastRevenue) {
		this.grossBreakfastRevenue = grossBreakfastRevenue;
	}

	public Double getNetBreakfastRevenue() {
		return netBreakfastRevenue;
	}

	public void setNetBreakfastRevenue(Double netBreakfastRevenue) {
		this.netBreakfastRevenue = netBreakfastRevenue;
	}

}
