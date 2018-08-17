/**
 * 
 */
package hu.hw.cloud.client.fro.config.hotel;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.browser.hotel.HotelTablePresenter;
import hu.hw.cloud.client.fro.browser.hotel.HotelTableView;
import hu.hw.cloud.client.fro.browser.marketgroup.MarketGroupTableModule;
import hu.hw.cloud.client.fro.browser.room.RoomTablePresenter;
import hu.hw.cloud.client.fro.browser.room.RoomTableView;
import hu.hw.cloud.client.fro.browser.roomtype.RoomTypeTablePresenter;
import hu.hw.cloud.client.fro.browser.roomtype.RoomTypeTableView;
import hu.hw.cloud.client.fro.editor.hotel.HotelEditorModule;
import hu.hw.cloud.client.fro.editor.room.RoomEditorModule;
import hu.hw.cloud.client.fro.editor.roomtype.RoomTypeEditorModule;

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
