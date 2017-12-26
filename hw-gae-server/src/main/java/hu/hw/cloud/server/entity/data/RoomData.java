/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.hotel.RoomAvailability;
import hu.hw.cloud.server.entity.support.RoomBuilder;
import hu.hw.cloud.server.repository.RoomRepository;
import hu.hw.cloud.server.service.DataBuilderService;
import hu.hw.cloud.server.utils.DateUtils;
import hu.hw.cloud.shared.cnst.RoomStatus;

/**
 * @author CR
 *
 */
public class RoomData extends BaseData<Room> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoomData.class.getName());

	private Room room10A;
	private Room room11A;
	private Room room12A;
	private Room room13A;
	private Room room14A;
	private Room room15A;
	private Room room16A;
	private Room room17A;
	private Room room18A;
	private Room room19A;

	private Room room20A;
	private Room room21A;
	private Room room22A;
	private Room room23A;
	private Room room24A;
	private Room room25A;
	private Room room26A;
	private Room room27A;
	private Room room28A;
	private Room room29A;

	private List<RoomAvailability> roomAvailability1 = new ArrayList<RoomAvailability>();
	private List<RoomAvailability> roomAvailability2 = new ArrayList<RoomAvailability>();
	private List<RoomAvailability> roomAvailability3 = new ArrayList<RoomAvailability>();

	public RoomData(DataBuilderService dbs, RoomRepository repo) {
		super(dbs, repo);

		Date date2 = DateUtils.addDays(dbs.getBusinessDate(), 30);
		Date date3 = DateUtils.addDays(dbs.getBusinessDate(), 40);

		roomAvailability1.add(new RoomAvailability(true, dbs.getBusinessDate()));

		roomAvailability2.add(new RoomAvailability(true, dbs.getBusinessDate()));
		roomAvailability2.add(new RoomAvailability(false, date2));
		roomAvailability2.add(new RoomAvailability(true, date3));

		roomAvailability3.add(new RoomAvailability(true, date3));

	}

	public void build() {
		getRoom10A();
		getRoom11A();
		getRoom12A();
		getRoom13A();
		getRoom14A();
		getRoom15A();
		getRoom16A();
		getRoom17A();
		getRoom18A();
		getRoom19A();
		
		for (RoomAvailability ra: getRoom10A().getRoomAvailabilities()) {
			LOGGER.info("date="+ra.getDate());
	
		}
	}

	public Room getRoom10A() {
		if (room10A != null)
			return room10A;
		// @formatter:off
		room10A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("10")
				.floor("1")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room10A;
	}

	public Room getRoom11A() {
		if (room11A != null)
			return room11A;
		// @formatter:off
		room11A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("11")
				.floor("1")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room11A;
	}

	public Room getRoom12A() {
		if (room12A != null)
			return room12A;
		// @formatter:off
		room12A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("12")
				.floor("1")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room12A;
	}

	public Room getRoom13A() {
		if (room13A != null)
			return room13A;
		// @formatter:off
		room13A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("13")
				.floor("1")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room13A;
	}

	public Room getRoom14A() {
		if (room14A != null)
			return room14A;

		// @formatter:off
		room14A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("14")
				.floor("1")
				.description("NS")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeSingleA())
				.build();
		// @formatter:on
		return room14A;
	}

	public Room getRoom15A() {
		if (room15A != null)
			return room15A;

		// @formatter:off
		room15A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("15")
				.floor("1")
				.description("NS")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room15A;
	}

	public Room getRoom16A() {
		if (room16A != null)
			return room16A;

		// @formatter:off
		room16A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("16")
				.floor("1")
				.description("NS")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room16A;
	}

	public Room getRoom17A() {
		if (room17A != null)
			return room17A;

		// @formatter:off
		room17A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("17")
				.floor("1")
				.description("SM")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room17A;
	}

	public Room getRoom18A() {
		if (room18A != null)
			return room18A;

		// @formatter:off
		room18A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("18")
				.floor("1")
				.description("SM")
				.roomAvailabilities(roomAvailability2)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room18A;
	}

	public Room getRoom19A() {
		if (room19A != null)
			return room19A;

		// @formatter:off
		room19A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("19")
				.floor("1")
				.description("NS")
				.roomAvailabilities(roomAvailability3)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeSingleA())
				.build();
		// @formatter:on
		return room19A;
	}

	public Room getRoom20A() {
		if (room20A != null)
			return room20A;
		// @formatter:off
		room20A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("20")
				.floor("2")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room20A;
	}

	public Room getRoom21A() {
		if (room21A != null)
			return room21A;
		// @formatter:off
		room21A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("21")
				.floor("2")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room21A;
	}

	public Room getRoom22A() {
		if (room22A != null)
			return room22A;
		// @formatter:off
		room22A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("22")
				.floor("2")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room22A;
	}

	public Room getRoom23A() {
		if (room23A != null)
			return room23A;
		// @formatter:off
		room23A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("23")
				.floor("2")
				.description("NS,no balcony")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room23A;
	}

	public Room getRoom24A() {
		if (room24A != null)
			return room24A;

		// @formatter:off
		room24A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("24")
				.floor("2")
				.description("NS")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeSingleA())
				.build();
		// @formatter:on
		return room24A;
	}

	public Room getRoom25A() {
		if (room25A != null)
			return room25A;

		// @formatter:off
		room25A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("25")
				.floor("2")
				.description("NS")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room25A;
	}

	public Room getRoom26A() {
		if (room26A != null)
			return room26A;

		// @formatter:off
		room26A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("26")
				.floor("2")
				.description("NS")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room26A;
	}

	public Room getRoom27A() {
		if (room27A != null)
			return room27A;

		// @formatter:off
		room27A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("27")
				.floor("2")
				.description("SM")
				.roomAvailabilities(roomAvailability1)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room27A;
	}

	public Room getRoom28A() {
		if (room28A != null)
			return room28A;

		// @formatter:off
		room28A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("28")
				.floor("2")
				.description("SM")
				.roomAvailabilities(roomAvailability2)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeDoubleA())
				.build();
		// @formatter:on
		return room28A;
	}

	public Room getRoom29A() {
		if (room29A != null)
			return room29A;

		// @formatter:off
		room29A = new RoomBuilder()
				.hotel(dbs.getHotelA())
				.code("29")
				.floor("2")
				.description("NS")
				.roomAvailabilities(roomAvailability3)
				.roomStatus(RoomStatus.CLEAN)
				.roomType(dbs.getRoomTypeSingleA())
				.build();
		// @formatter:on
		return room29A;
	}

}
