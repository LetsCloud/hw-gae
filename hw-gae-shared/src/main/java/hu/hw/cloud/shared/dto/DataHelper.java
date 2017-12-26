/**
 * 
 */
package hu.hw.cloud.shared.dto;

import java.util.ArrayList;
import java.util.List;

import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
public class DataHelper {
	/**
	 * Get all Users for GContacts Pattern
	 * 
	 * @return
	 */
	public static List<AppUserDto> getAllUsers() {
		List<AppUserDto> list = new ArrayList<>();
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/stevedesigner/128.jpg", true,
				"Luis Hoppe", "luis@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/yassiryahya/128.jpg", true,
				"Irwin Mueller", "irwin@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/lebinoclard/128.jpg", true,
				"Levin Card", "levin@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/lmjabreu/128.jpg", false,
				"Dr. Cassie Keeling", "cassie@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/ariil/128.jpg", false,
				"Dr. Madelynn Schamberger", "madelyn@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/devankoshal/128.jpg", false,
				"Dominique Schmidt", "dom@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/karthipanraj/128.jpg", false,
				"Rowland Heller", "rowland@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/GavicoInd/128.jpg", false,
				"Quincy Schimmel", "quincy@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/roybarberuk/128.jpg", false,
				"Tierra VonRueden", "tierra@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/kimcool/128.jpg", false,
				"Travis Larson", "travis@mail.com"));
		list.add(new AppUserDto("https://s3.amazonaws.com/uifaces/faces/twitter/tonymillion/128.jpg", false,
				"Clint Heller", "clint@mail.com"));
		return list;
	}

}
