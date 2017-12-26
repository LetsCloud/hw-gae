/**
 * 
 */
package hu.hw.cloud.client.inf.dashboard.widget;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.name.Names;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.inf.dashboard.widget.view.GeoView;
import hu.hw.cloud.client.inf.dashboard.widget.view.MultiMeasureView;
import hu.hw.cloud.client.inf.dashboard.widget.view.ValueDeltaChart2View;
import hu.hw.cloud.client.inf.dashboard.widget.view.ValueDeltaChartView;
import hu.hw.cloud.client.inf.dashboard.widget.view.ValueDeltaView;
import hu.hw.cloud.client.inf.dashboard.widget.view.ValueChartView;
import hu.hw.cloud.client.inf.dashboard.widget.view.ValueOnlyView;
import hu.hw.cloud.shared.cnst.cube.DataWidgetType.Cnst;

/**
 * @author CR
 *
 */
public class DataWidgetModule extends AbstractPresenterModule {

	@Override
	protected void configure() {

		install(new GinFactoryModuleBuilder()
				.implement(DataWidgetPresenter.DataWidgetView.class, Names.named(Cnst.VALUE_ONLY), ValueOnlyView.class)
				.implement(DataWidgetPresenter.DataWidgetView.class, Names.named(Cnst.VALUE_DELTA),
						ValueDeltaView.class)
				.implement(DataWidgetPresenter.DataWidgetView.class, Names.named(Cnst.VALUE_CHART),
						ValueChartView.class)
				.implement(DataWidgetPresenter.DataWidgetView.class, Names.named(Cnst.VALUE_DELTA_CHART),
						ValueDeltaChartView.class)
				.implement(DataWidgetPresenter.DataWidgetView.class, Names.named(Cnst.VALUE_DELTA_CHART2),
						ValueDeltaChart2View.class)
				.implement(DataWidgetPresenter.DataWidgetView.class, Names.named(Cnst.MULTI_VALUE),
						MultiMeasureView.class)
				.implement(DataWidgetPresenter.DataWidgetView.class, Names.named(Cnst.GEO_CHART),
						GeoView.class)
				.build(DataWidgetViewFactory.class));

		install(new GinFactoryModuleBuilder().build(DataWidgetFactory.class));
	}
}
