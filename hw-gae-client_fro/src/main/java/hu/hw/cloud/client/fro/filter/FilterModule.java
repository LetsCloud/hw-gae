/**
 * 
 */
package hu.hw.cloud.client.fro.filter;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

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

		bindPresenterWidget(RoomTypeFilterPresenter.class, RoomTypeFilterPresenter.MyView.class,
				RoomTypeFilterView.class);

		install(new GinFactoryModuleBuilder().build(FilterPresenterFactory.class));
	}
}
