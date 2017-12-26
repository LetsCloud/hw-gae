/**
 * 
 */
package hu.hw.cloud.client.inf.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.inf.ana.perf1.Perf1Module;
import hu.hw.cloud.client.inf.analytics.AnalyticsModule;
import hu.hw.cloud.client.inf.app.InfAppModule;
import hu.hw.cloud.client.inf.dashboard.DashboardModule;
import hu.hw.cloud.client.inf.dashboard.widget.DataWidgetModule;
import hu.hw.cloud.client.inf.gps.GpsState;
import hu.hw.cloud.client.inf.gps.display.GpsDisplayModule;
import hu.hw.cloud.client.core.gin.CoreModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(GpsState.class).asEagerSingleton();
		bind(ResourceLoader.class).asEagerSingleton();
		
		install(new InfAppModule());
		install(new GpsDisplayModule());
		install(new DashboardModule());
		install(new DataWidgetModule());
		install(new AnalyticsModule());
		install(new Perf1Module());
	}
}
