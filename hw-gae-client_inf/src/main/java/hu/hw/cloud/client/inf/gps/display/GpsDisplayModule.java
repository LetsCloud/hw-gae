/**
 * 
 */
package hu.hw.cloud.client.inf.gps.display;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.inf.gps.config.GpsConfigModule;

/**
 * @author CR
 *
 */
public class GpsDisplayModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindSingletonPresenterWidget(GpsDisplayPresenter.class, GpsDisplayPresenter.MyView.class, GpsDisplayView.class);

		install(new GpsConfigModule());
	}
}