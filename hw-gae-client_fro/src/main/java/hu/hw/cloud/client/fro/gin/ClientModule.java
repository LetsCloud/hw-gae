/**
 * 
 */
package hu.hw.cloud.client.fro.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.app.FroAppModule;
import hu.hw.cloud.client.fro.config.hotel.HotelConfigModule;
import hu.hw.cloud.client.fro.config.system.SystemConfigModule;
import hu.hw.cloud.client.fro.dashboard.DashboardModule;
import hu.hw.cloud.client.fro.edit.DtoEditorModule;
import hu.hw.cloud.client.fro.editor.appuser.AppUserEditorModule;
import hu.hw.cloud.client.fro.editor.hotel.HotelEditorModule;
import hu.hw.cloud.client.fro.editor.roomtype.RoomTypeEditorModule;
import hu.hw.cloud.client.fro.table.DtoTableModule;
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

		install(new FroAppModule());
		install(new DashboardModule());
		install(new SystemConfigModule());
		install(new HotelConfigModule());

		install(new DtoTableModule());
		install(new DtoEditorModule());

		install(new HotelEditorModule());
		install(new RoomTypeEditorModule());
		install(new AppUserEditorModule());

	}
}
