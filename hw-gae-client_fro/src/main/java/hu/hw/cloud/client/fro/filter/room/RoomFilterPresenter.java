package hu.hw.cloud.client.fro.filter.room;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.datasource.RoomTypeDataSource;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.AbstractFilterPresenter;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomFilterPresenter extends AbstractFilterPresenter<RoomFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RoomFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		void setFloors(List<String> floors);

		String getSelectedFloor();
	}

	private RoomTypeDataSource roomTypeDataSource;

	@Inject
	RoomFilterPresenter(EventBus eventBus, MyView view, HotelDataSource hotelDataSource,
			RoomTypeDataSource roomTypeDataSource, CurrentUser currentUser) {
		super(eventBus, view, hotelDataSource, currentUser);
		logger.info("RoomFilterPresenter()");
		
		this.roomTypeDataSource = roomTypeDataSource;
		
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("RoomFilterPresenter().onReveal()");
		LoadCallback<RoomTypeDto> roomTypeLoadCallback = new LoadCallback<RoomTypeDto>() {

			@Override
			public void onSuccess(LoadResult<RoomTypeDto> loadResult) {
//				FilterChangeEvent.fire(RoomFilterPresenter.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		};

		roomTypeDataSource.load(new LoadConfig<RoomTypeDto>(0, 0, null, null), roomTypeLoadCallback);
	}

	public void setFloors(List<String> floors) {
		getView().setFloors(floors);
	}

	public String getSelectedFloor() {
		return getView().getSelectedFloor();
	}
}
