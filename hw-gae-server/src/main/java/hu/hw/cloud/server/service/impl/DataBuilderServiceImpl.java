/**
 * 
 */
package hu.hw.cloud.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.AppUser;
import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.entity.data.CurrencyData;
import hu.hw.cloud.server.entity.data.DataContainer;
import hu.hw.cloud.server.entity.data.GuestData;
import hu.hw.cloud.server.entity.data.HotelData;
import hu.hw.cloud.server.entity.data.ReservationData;
import hu.hw.cloud.server.entity.data.RoomData;
import hu.hw.cloud.server.entity.data.RoomTypeData;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.entity.profile.Guest;
import hu.hw.cloud.server.repository.AccountRepository;
import hu.hw.cloud.server.repository.AppUserRepository;
import hu.hw.cloud.server.repository.CurrencyRepository;
import hu.hw.cloud.server.repository.GuestRepository;
import hu.hw.cloud.server.repository.HotelRepository;
import hu.hw.cloud.server.repository.ReservationRepository;
import hu.hw.cloud.server.repository.RoomRepository;
import hu.hw.cloud.server.repository.RoomTypeRepository;
import hu.hw.cloud.server.service.DataBuilderService;

/**
 * @author CR
 *
 */
//@Service
public class DataBuilderServiceImpl extends DataContainer implements DataBuilderService {

	private Account account;

	//@Autowired
	private AccountRepository accountRepository;

	//@Autowired
	private AppUserRepository appUserRepository;

	//@Autowired
	private CurrencyRepository currencyRepository;

	//@Autowired
	private GuestRepository guestRepository;

	//@Autowired
	private HotelRepository hotelRepository;

//	@Autowired
	private RoomTypeRepository roomTypeRepository;

//	@Autowired
	private RoomRepository roomRepository;

//	@Autowired
	private ReservationRepository reservationRepository;

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void setAppUserRepository(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	public void setCurrencyRepository(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	public void setGuestRepository(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	public void setHotelRepository(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	public void setRoomTypeRepository(RoomTypeRepository roomTypeRepository) {
		this.roomTypeRepository = roomTypeRepository;
	}

	public void setRoomRepository(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public void setReservationRepository(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Override
	public void buildTestData(String accountWebSafeKey) {
		account = accountRepository.findByWebSafeKey(accountWebSafeKey);
		prepare();
		build();
		updateAppUsers();
	}

	private void prepare() {
		currencyData = new CurrencyData(this, currencyRepository);
		guestData = new GuestData(this, guestRepository);
		hotelData = new HotelData(this, hotelRepository);
		roomTypeData = new RoomTypeData(this, roomTypeRepository);
		roomData = new RoomData(this, roomRepository);
		reservationData = new ReservationData(this, reservationRepository);
	}

	private void updateAppUsers() {
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels.add(this.getHotelA());

		List<AppUser> users = appUserRepository.getAll(account);
		for (AppUser user : users) {
			try {
				user.setAvailableHotels(hotels);
				user.setDefaultHotel(this.getHotelA());
				appUserRepository.save(user);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Date getBusinessDate() {
		return BUSINESS_DATE;
	}

	@Override
	public Account getAccount() {
		return account;
	}

	@Override
	public Currency getCurrencyHuf() {
		return currencyData.getCurrencyHuf();
	}

	@Override
	public Currency getCurrencyEur() {
		return currencyData.getCurrencyEur();
	}

	@Override
	public Guest getGuest101() {
		return guestData.getG101();
	}

	@Override
	public Guest getGuest102() {
		return guestData.getG102();
	}

	@Override
	public Guest getGuest111() {
		return guestData.getG111();
	}

	@Override
	public Guest getGuest112() {
		return guestData.getG112();
	}

	@Override
	public Guest getGuest121() {
		return guestData.getG121();
	}

	@Override
	public Guest getGuest122() {
		return guestData.getG122();
	}

	@Override
	public Guest getGuest131() {
		return guestData.getG131();
	}

	@Override
	public Guest getGuest132() {
		return guestData.getG132();
	}

	@Override
	public Hotel getHotelA() {
		return hotelData.getHotelA();
	}

	@Override
	public RoomType getRoomTypeSingleA() {
		return roomTypeData.getRoomTypeSingleA();
	}

	@Override
	public RoomType getRoomTypeDoubleA() {
		return roomTypeData.getRoomTypeDoubleA();
	}

	@Override
	public RoomType getRoomTypeDouble2xA() {
		return roomTypeData.getRoomTypeDouble2xA();
	}

	@Override
	public RoomType getRoomTypeTwinA() {
		return roomTypeData.getRoomTypeTwinA();
	}

	@Override
	public RoomType getRoomTypeInterconnectingA() {
		return roomTypeData.getRoomTypeInterconnectingA();
	}

	@Override
	public RoomType getRoomTypeSuiteA() {
		return roomTypeData.getRoomTypeSuiteA();
	}

	@Override
	public RoomType getRoomTypeKingA() {
		return roomTypeData.getRoomTypeKingA();
	}

	@Override
	public RoomType getRoomTypeQueenA() {
		return roomTypeData.getRoomTypeQueenA();
	}

	@Override
	public Room getRoom10A() {
		return roomData.getRoom10A();
	}

	@Override
	public Room getRoom11A() {
		return roomData.getRoom11A();
	}

	@Override
	public Room getRoom12A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom13A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom14A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom15A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom16A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom17A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom18A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room getRoom19A() {
		// TODO Auto-generated method stub
		return null;
	}
}
