/**
 * 
 */
package hu.hw.cloud.client.fro.filter.hotelchild;

import java.util.List;
import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.datasource.HotelDataSource2;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.AbstractFilterPresenter;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;
import hu.hw.cloud.client.fro.filter.FilterChangeEvent;
import hu.hw.cloud.client.fro.filter.FilterChangeEvent.DataTable;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.dto.hotel.HotelDtor;

/**
 * @author robi
 *
 */
public abstract class AbstractHotelChildFilterPresenter<V extends AbstractHotelChildFilterPresenter.MyView>
		extends AbstractFilterPresenter<V> {
	private static Logger logger = Logger.getLogger(AbstractHotelChildFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setHotelData(List<HotelDtor> hotelData);

		void setSelectedHotel(HotelDtor hotelDto);

		HotelDtor getSelectedHotel();
	}

	protected final HotelDataSource2 hotelDataSource;

	public AbstractHotelChildFilterPresenter(EventBus eventBus, V view, CurrentUser currentUser,
			HotelDataSource2 hotelDataSource) {
		super(eventBus, view, currentUser);
		logger.info("HotelChildFilterPresenter()");
		this.hotelDataSource = hotelDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		LoadCallback<HotelDtor> hotelLoadCallback = new LoadCallback<HotelDtor>() {

			@Override
			public void onSuccess(LoadResult<HotelDtor> loadResult) {
				logger.info("AbstractFilterPresenter().onReveal().onSuccess()");
				getView().setHotelData(currentUser.getAppUserDto().getAvailableHotels());
				getView().setSelectedHotel(currentUser.getCurrentHotel());
				FilterChangeEvent.fire(AbstractHotelChildFilterPresenter.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		};

		hotelDataSource.load(new LoadConfig<HotelDtor>(0, 0, null, null), hotelLoadCallback);
	}
	
	public HotelDtor getSelectedHotel() {
		return getView().getSelectedHotel();
	}
}
