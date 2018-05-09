package hu.hw.cloud.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.objectify.ObjectifyService;

import hu.hw.cloud.server.entity.chat.Chat;
import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.entity.common.Role;
import hu.hw.cloud.server.entity.common.UserGroup;
import hu.hw.cloud.server.entity.cube.CubeActlPfm;
import hu.hw.cloud.server.entity.cube.CubeBdgtPfm;
import hu.hw.cloud.server.entity.cube.CubeFcstPfm;
import hu.hw.cloud.server.entity.cube.CubeActlCap;
import hu.hw.cloud.server.entity.cube.CubeBdgtCap;
import hu.hw.cloud.server.entity.cube.CubeFcstCap;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.entity.hotel.Service;
import hu.hw.cloud.server.entity.profile.Guest;
import hu.hw.cloud.server.entity.profile.Person;
import hu.hw.cloud.server.entity.profile.Profile;
import hu.hw.cloud.server.entity.reservation.Reservation;
import hu.hw.cloud.server.entity.task.Task;

public class ObjectifyRegistration {
	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectifyRegistration.class);

	static {
		LOGGER.info("ObjectifyService.register");
		ObjectifyService.register(Account.class);
		ObjectifyService.register(AppUser.class);
		ObjectifyService.register(UserGroup.class);
		ObjectifyService.register(Role.class);
		ObjectifyService.register(Currency.class);
		ObjectifyService.register(Service.class);
		ObjectifyService.register(Profile.class);
		ObjectifyService.register(Person.class);
		ObjectifyService.register(Guest.class);
		ObjectifyService.register(Hotel.class);
		ObjectifyService.register(RoomType.class);
		ObjectifyService.register(Room.class);
		ObjectifyService.register(Chat.class);
		ObjectifyService.register(Task.class);
		ObjectifyService.register(Reservation.class);
		ObjectifyService.register(CubeBdgtCap.class);
		ObjectifyService.register(CubeBdgtPfm.class);
		ObjectifyService.register(CubeActlCap.class);
		ObjectifyService.register(CubeActlPfm.class);
		ObjectifyService.register(CubeFcstCap.class);
		ObjectifyService.register(CubeFcstPfm.class);
		// ObjectifyService.register(VatRate.class);
		// ObjectifyService.register(Service.class);
	}

}
