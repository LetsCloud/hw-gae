/**
 * 
 */
package hu.hw.cloud.client.fro.filter;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.filter.hotelchild.HotelChildFilterPresenter;
import hu.hw.cloud.client.fro.filter.hotelchild.HotelChildFilterView;
import hu.hw.cloud.client.fro.filter.room.RoomFilterPresenter;
import hu.hw.cloud.client.fro.filter.room.RoomFilterView;
import hu.hw.cloud.client.fro.filter.roomtype.RoomTypeFilterPresenter;
import hu.hw.cloud.client.fro.filter.roomtype.RoomTypeFilterView;
import hu.hw.cloud.client.fro.table.filter.FilterWidgetPresenter;
import hu.hw.cloud.client.fro.table.filter.FilterWidgetView;

/**
 * @author robi
 *
 */
public class FilterModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(FilterWidgetPresenter.class, FilterWidgetPresenter.MyView.class, FilterWidgetView.class);

		bindPresenterWidget(HotelChildFilterPresenter.class, HotelChildFilterPresenter.MyView.class,
				HotelChildFilterView.class);

		bindPresenterWidget(RoomTypeFilterPresenter.class, RoomTypeFilterPresenter.MyView.class,
				RoomTypeFilterView.class);

		bindPresenterWidget(RoomFilterPresenter.class, RoomFilterPresenter.MyView.class,
				RoomFilterView.class);

		install(new GinFactoryModuleBuilder().build(FilterPresenterFactory.class));
	}
}
