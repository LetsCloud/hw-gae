/**
 * 
 */
package hu.hw.cloud.client.fro.filter;

import java.util.List;
import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.FilterChangeEvent.DataTable;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public abstract class AbstractFilterPresenter<V extends AbstractFilterPresenter.MyView> extends PresenterWidget<V>
		implements AbstractFilterUiHandlers {
	private static Logger logger = Logger.getLogger(AbstractFilterPresenter.class.getName());

	public interface MyView extends View {

		void reset();

		void setHotelData(List<HotelDto> hotelData);

		void setSelectedHotel(HotelDto hotelDto);

		HotelDto getSelectedHotel();
	}

	private final HotelDataSource hotelDataSource;

	private final CurrentUser currentUser;

	private Boolean isHotelReady;
	
	public AbstractFilterPresenter(EventBus eventBus, V view, HotelDataSource hotelDataSource,
			CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("AbstractFilterPresenter()");

		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
	}

	@Override
	public void onReveal() {
		super.onReveal();
		isHotelReady = false;
		logger.info("AbstractFilterPresenter().onReveal()");
		LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {

			@Override
			public void onSuccess(LoadResult<HotelDto> loadResult) {
				logger.info("AbstractFilterPresenter().onReveal().onSuccess()");
				getView().setHotelData(currentUser.getAppUserDto().getAvailableHotelDtos());
				getView().setSelectedHotel(currentUser.getCurrentHotelDto());
				isHotelReady = true;
				FilterChangeEvent.fire(AbstractFilterPresenter.this, DataTable.ROOM_TYPE);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		};

		hotelDataSource.load(new LoadConfig<HotelDto>(0, 0, null, null), hotelLoadCallback);
	}

	@Override
	public void filterChange() {
		logger.info("FilterWidgetPresenter().filterChange()");
		FilterChangeEvent.fire(AbstractFilterPresenter.this, DataTable.ROOM_TYPE);
	}

	public HotelDto getSelectedHotel() {
		return getView().getSelectedHotel();
	}
	
	protected Boolean isReady() {
		return isHotelReady;
	}
}
