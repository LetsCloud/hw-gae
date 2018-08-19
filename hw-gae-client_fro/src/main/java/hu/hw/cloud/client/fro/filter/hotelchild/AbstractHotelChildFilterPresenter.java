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

import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.AbstractFilterPresenter;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;
import hu.hw.cloud.client.fro.filter.FilterChangeEvent;
import hu.hw.cloud.client.fro.filter.FilterChangeEvent.DataTable;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public abstract class AbstractHotelChildFilterPresenter<V extends AbstractHotelChildFilterPresenter.MyView>
		extends AbstractFilterPresenter<V> {
	private static Logger logger = Logger.getLogger(AbstractHotelChildFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		void setHotelData(List<HotelDto> hotelData);

		void setSelectedHotel(HotelDto hotelDto);

		HotelDto getSelectedHotel();
	}

	protected final HotelDataSource hotelDataSource;

	public AbstractHotelChildFilterPresenter(EventBus eventBus, V view, CurrentUser currentUser,
			HotelDataSource hotelDataSource) {
		super(eventBus, view, currentUser);
		logger.info("HotelChildFilterPresenter()");
		this.hotelDataSource = hotelDataSource;
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {

			@Override
			public void onSuccess(LoadResult<HotelDto> loadResult) {
				logger.info("AbstractFilterPresenter().onReveal().onSuccess()");
				getView().setHotelData(currentUser.getAppUserDto().getAvailableHotelDtos());
				getView().setSelectedHotel(currentUser.getCurrentHotelDto());
				FilterChangeEvent.fire(AbstractHotelChildFilterPresenter.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		};

		hotelDataSource.load(new LoadConfig<HotelDto>(0, 0, null, null), hotelLoadCallback);
	}
	
	public HotelDto getSelectedHotel() {
		return getView().getSelectedHotel();
	}
}
