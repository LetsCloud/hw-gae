/**
 * 
 */
package hu.hw.cloud.client.kip.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.kip.app.KipAppModule;
import hu.hw.cloud.client.kip.assignments.AssignmentsModule;
import hu.hw.cloud.client.kip.atendants.AtendantsModule;
import hu.hw.cloud.client.kip.chat.ChatRoomModule;
import hu.hw.cloud.client.kip.chat.creator.ChatCreatorModule;
import hu.hw.cloud.client.kip.chat.editor.ChatEditorModule;
import hu.hw.cloud.client.kip.chat.list.ChatListModule;
import hu.hw.cloud.client.kip.gfilter.display.GfilterDisplayModule;
import hu.hw.cloud.client.kip.roomstatus.DesktopRoomStatusModule;
import hu.hw.cloud.client.kip.roomstatus.filter.RoomStatusFilterModule;
import hu.hw.cloud.client.kip.roomstatus.list.RoomStatusListModule;
import hu.hw.cloud.client.core.gin.CoreModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(ResourceLoader.class).asEagerSingleton();

		install(new KipAppModule());
		install(new GfilterDisplayModule());
		install(new AtendantsModule());
		install(new AssignmentsModule());
		install(new DesktopRoomStatusModule());
		install(new RoomStatusListModule());
		install(new RoomStatusFilterModule());
		install(new ChatRoomModule());
		install(new ChatListModule());
		install(new ChatCreatorModule());
		install(new ChatEditorModule());
		/*
		 * install(new UserListModule()); install(new RoleListModule()); install(new
		 * HotelListModule());
		 */
	}
}
