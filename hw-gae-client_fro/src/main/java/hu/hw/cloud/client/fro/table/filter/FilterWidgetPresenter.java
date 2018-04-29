/**
 * 
 */
package hu.hw.cloud.client.fro.table.filter;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.table.filter.FilterChangeEvent.DataTable;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class FilterWidgetPresenter extends PresenterWidget<FilterWidgetPresenter.MyView>
		implements FilterWidgetUiHandlers {
	private static Logger logger = Logger.getLogger(FilterWidgetPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<FilterWidgetUiHandlers> {

		void setHotelData(List<HotelDto> hotelData);

		void setSelectedHotel(HotelDto hotelDto);

		HotelDto getSelectedHotel();
		
		Boolean isOnlyActive();
	}

	private final HotelDataSource hotelDataSource;

	private final CurrentUser currentUser;

	@Inject
	FilterWidgetPresenter(EventBus eventBus, MyView view, HotelDataSource hotelDataSource, CurrentUser currentUser) {
		super(eventBus, view);

		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("FilterWidgetPresenter().loadData()");
		LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {

			@Override
			public void onSuccess(LoadResult<HotelDto> loadResult) {
				logger.info("FilterWidgetPresenter().loadData().onSuccess()");
				getView().setHotelData(currentUser.getAppUserDto().getAvailableHotelDtos());
				getView().setSelectedHotel(currentUser.getCurrentHotelDto());
				FilterChangeEvent.fire(FilterWidgetPresenter.this, DataTable.ROOM_TYPE);
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
		FilterChangeEvent.fire(FilterWidgetPresenter.this, DataTable.ROOM_TYPE);
	}
	
	public HotelDto getSelectedHotel() {
		return getView().getSelectedHotel();
	}
	
	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}
}