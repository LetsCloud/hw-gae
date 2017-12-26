/**
 * 
 */
package hu.hw.cloud.server.entity.support;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.hotel.RoomAvailability;
import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.repository.RoomRepository;
import hu.hw.cloud.shared.cnst.RoomStatus;

/**
 * @author CR
 *
 */
public class RoomBuilder extends EntityBuilder<Room> {

	@Autowired
	private RoomRepository repo;

	@Override
	void initProduct() {
		product = new Room();
		product.setOccupied(false);
	}

	@Override
	Room assembleProduct() {
		return product;
	}

	@Override
	Room saveProduct(Room entity) {
		if (repo != null) {
			try {
				this.product = repo.save(entity);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Key<Room> key = ObjectifyService.ofy().save().entity(entity).now();
			this.product = ObjectifyService.ofy().load().key(key).now();
		}
		return this.product;
	}

	public RoomBuilder code(String code) {
		this.product.setCode(code);
		return this;
	}

	public RoomBuilder floor(String floor) {
		this.product.setFloor(floor);
		return this;
	}

	public RoomBuilder description(String description) {
		this.product.setDescription(description);
		return this;
	}

	public RoomBuilder hotel(Hotel hotel) {
		this.product.setHotel(hotel);
		return this;
	}

	public RoomBuilder roomAvailabilities(List<RoomAvailability> roomAvailabilities) {
		this.product.setRoomAvailabilities(roomAvailabilities);
		return this;
	}

	public RoomBuilder roomStatus(RoomStatus roomStatus) {
		this.product.setRoomStatus(roomStatus);
		return this;
	}

	public RoomBuilder roomType(RoomType roomType) {
		this.product.setRoomType(roomType);
		return this;
	}
}
