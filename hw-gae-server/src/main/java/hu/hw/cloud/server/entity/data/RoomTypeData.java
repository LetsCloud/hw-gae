/**
 * 
 */
package hu.hw.cloud.server.entity.data;

import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.RoomTypeRepository;
import hu.hw.cloud.server.service.DataBuilderService;
import hu.hw.cloud.shared.cnst.InventoryType;

/**
 * @author CR
 *
 */
public class RoomTypeData extends BaseData<RoomType> {

	private RoomType roomTypeSingleA;
	private RoomType roomTypeDoubleA;
	private RoomType roomTypeDouble2xA;
	private RoomType roomTypeTwinA;
	private RoomType roomTypeInterconnectingA;
	private RoomType roomTypeSuiteA;
	private RoomType roomTypeKingA;
	private RoomType roomTypeQueenA;

	public RoomTypeData(DataBuilderService dbs, RoomTypeRepository repo) {
		super(dbs, repo);
	}

	public void build() {
		getRoomTypeSingleA();
		getRoomTypeDoubleA();
		getRoomTypeDouble2xA();
		getRoomTypeTwinA();
		getRoomTypeInterconnectingA();
		getRoomTypeSuiteA();
		getRoomTypeKingA();
		getRoomTypeQueenA();
	}

	public RoomType getRoomTypeSingleA() {
		if (roomTypeSingleA != null)
			return roomTypeSingleA;

		roomTypeSingleA = new RoomType();
		roomTypeSingleA.setHotel(dbs.getHotelA());
		roomTypeSingleA.setCode("SGL");
		roomTypeSingleA.setName("Single room");
		roomTypeSingleA.setDescription("A room which has single bed facility.");
		roomTypeSingleA.setInventoryType(InventoryType.PHYS);
		roomTypeSingleA.setActive(true);

		roomTypeSingleA = save(roomTypeSingleA);
		return roomTypeSingleA;
	}

	public RoomType getRoomTypeDoubleA() {
		if (roomTypeDoubleA != null)
			return roomTypeDoubleA;

		roomTypeDoubleA = new RoomType();
		roomTypeDoubleA.setHotel(dbs.getHotelA());
		roomTypeDoubleA.setCode("DBL");
		roomTypeDoubleA.setName("Double room");
		roomTypeDoubleA.setDescription("A room which has double bed facility.");
		roomTypeDoubleA.setInventoryType(InventoryType.PHYS);
		roomTypeDoubleA.setActive(true);
		roomTypeDoubleA = save(roomTypeDoubleA);
		return roomTypeDoubleA;
	}

	public RoomType getRoomTypeDouble2xA() {
		if (roomTypeDouble2xA != null)
			return roomTypeDouble2xA;

		roomTypeDouble2xA = new RoomType();
		roomTypeDouble2xA.setHotel(dbs.getHotelA());
		roomTypeDouble2xA.setCode("DBL2x");
		roomTypeDouble2xA.setName("Double double room");
		roomTypeDouble2xA.setDescription("Double double room");
		roomTypeDouble2xA.setInventoryType(InventoryType.PHYS);
		roomTypeDouble2xA.setActive(true);
		roomTypeDouble2xA = save(roomTypeDouble2xA);
		return roomTypeDouble2xA;
	}

	public RoomType getRoomTypeTwinA() {
		if (roomTypeTwinA != null)
			return roomTypeTwinA;

		roomTypeTwinA = new RoomType();
		roomTypeTwinA.setHotel(dbs.getHotelA());
		roomTypeTwinA.setCode("TWN");
		roomTypeTwinA.setName("Twin room");
		roomTypeTwinA.setDescription("A room which has two single bed separated by a center table.");
		roomTypeTwinA.setInventoryType(InventoryType.PHYS);
		roomTypeTwinA.setActive(true);
		roomTypeTwinA = save(roomTypeTwinA);
		return roomTypeTwinA;
	}

	public RoomType getRoomTypeInterconnectingA() {
		if (roomTypeInterconnectingA != null)
			return roomTypeInterconnectingA;

		roomTypeInterconnectingA = new RoomType();
		roomTypeInterconnectingA.setHotel(dbs.getHotelA());
		roomTypeInterconnectingA.setCode("ICO");
		roomTypeInterconnectingA.setName("Interconnecting rooms");
		roomTypeInterconnectingA.setDescription("Two rooms which shares a common door, mostly used by families.");
		roomTypeInterconnectingA.setInventoryType(InventoryType.COMP);
		roomTypeInterconnectingA.setActive(true);
		roomTypeInterconnectingA = save(roomTypeInterconnectingA);
		return roomTypeInterconnectingA;
	}

	public RoomType getRoomTypeSuiteA() {
		if (roomTypeSuiteA != null)
			return roomTypeSuiteA;

		roomTypeSuiteA = new RoomType();
		roomTypeSuiteA.setHotel(dbs.getHotelA());
		roomTypeSuiteA.setCode("SUITE");
		roomTypeSuiteA.setName("Suite room");
		roomTypeSuiteA.setDescription("A room comprises of two or more bedroom, a living room and a dining area.");
		roomTypeSuiteA.setInventoryType(InventoryType.PHYS);
		roomTypeSuiteA.setActive(true);
		roomTypeSuiteA = save(roomTypeSuiteA);
		return roomTypeSuiteA;
	}

	public RoomType getRoomTypeKingA() {
		if (roomTypeKingA != null)
			return roomTypeKingA;

		roomTypeKingA = new RoomType();
		roomTypeKingA.setHotel(dbs.getHotelA());
		roomTypeKingA.setCode("KING");
		roomTypeKingA.setName("King bedroom");
		roomTypeKingA.setDescription("A room with a king sized bed.");
		roomTypeKingA.setInventoryType(InventoryType.PHYS);
		roomTypeKingA.setActive(true);
		roomTypeKingA = save(roomTypeKingA);
		return roomTypeKingA;
	}

	public RoomType getRoomTypeQueenA() {
		if (roomTypeQueenA != null)
			return roomTypeQueenA;

		roomTypeQueenA = new RoomType();
		roomTypeQueenA.setHotel(dbs.getHotelA());
		roomTypeQueenA.setCode("QUEEN");
		roomTypeQueenA.setName("Queen bedroom");
		roomTypeQueenA.setDescription("A room with a queen sized bed.");
		roomTypeQueenA.setInventoryType(InventoryType.PHYS);
		roomTypeQueenA.setActive(true);
		roomTypeQueenA = save(roomTypeQueenA);
		return roomTypeQueenA;
	}

}
