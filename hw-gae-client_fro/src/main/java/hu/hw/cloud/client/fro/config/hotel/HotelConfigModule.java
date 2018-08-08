/**
 * 
 */
package hu.hw.cloud.client.fro.config.hotel;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.hotel.HotelEditorModule;
import hu.hw.cloud.client.fro.editor.room.RoomEditorModule;
import hu.hw.cloud.client.fro.editor.roomtype.RoomTypeEditorModule;
import hu.hw.cloud.client.fro.table.hotel.HotelTablePresenter;
import hu.hw.cloud.client.fro.table.hotel.HotelTableView;
import hu.hw.cloud.client.fro.table.marketgroup.MarketGroupTableModule;
import hu.hw.cloud.client.fro.table.room.RoomTablePresenter;
import hu.hw.cloud.client.fro.table.room.RoomTableView;
import hu.hw.cloud.client.fro.table.roomtype.RoomTypeTablePresenter;
import hu.hw.cloud.client.fro.table.roomtype.RoomTypeTableView;

/**
 * @author robi
 *
 */
public class HotelConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new MarketGroupTableModule());

		install(new HotelEditorModule());
		install(new RoomTypeEditorModule());
		install(new RoomEditorModule());

		bindPresenterWidget(HotelTablePresenter.class, HotelTablePresenter.MyView.class,
				HotelTableView.class);
		
		bindPresenterWidget(RoomTypeTablePresenter.class, RoomTypeTablePresenter.MyView.class,
				RoomTypeTableView.class);
		
		bindPresenterWidget(RoomTablePresenter.class, RoomTablePresenter.MyView.class,
				RoomTableView.class);
		
		bindPresenter(HotelConfigPresenter.class, HotelConfigPresenter.MyView.class, HotelConfigView.class,
				HotelConfigPresenter.MyProxy.class);
	}
}
