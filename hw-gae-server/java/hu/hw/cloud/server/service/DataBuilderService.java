/**
 * 
 */
package hu.hw.cloud.server.service;

import java.util.Date;

import hu.hw.cloud.server.entity.common.Account;
import hu.hw.cloud.server.entity.common.Currency;
import hu.hw.cloud.server.entity.hotel.Hotel;
import hu.hw.cloud.server.entity.hotel.Room;
import hu.hw.cloud.server.entity.hotel.RoomType;
import hu.hw.cloud.server.entity.profile.Guest;

/**
 * @author CR
 *
 */
public interface DataBuilderService {
	
	void buildTestData(String accountWebSafeKey);
	
	Date getBusinessDate();
	Account getAccount();
	Currency getCurrencyHuf();
	Currency getCurrencyEur();
	
	Guest getGuest101();
	Guest getGuest102();
	Guest getGuest111();
	Guest getGuest112();
	Guest getGuest121();
	Guest getGuest122();
	Guest getGuest131();
	Guest getGuest132();
	
	Hotel getHotelA();
	
	RoomType getRoomTypeSingleA();
	RoomType getRoomTypeDoubleA();
	RoomType getRoomTypeDouble2xA();
	RoomType getRoomTypeTwinA();
	RoomType getRoomTypeInterconnectingA();
	RoomType getRoomTypeSuiteA();
	RoomType getRoomTypeKingA();
	RoomType getRoomTypeQueenA();
	
	Room getRoom10A();
	Room getRoom11A();
	Room getRoom12A();
	Room getRoom13A();
	Room getRoom14A();
	Room getRoom15A();
	Room getRoom16A();
	Room getRoom17A();
	Room getRoom18A();
	Room getRoom19A();
}
