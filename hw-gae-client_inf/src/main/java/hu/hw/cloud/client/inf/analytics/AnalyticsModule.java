/**
 * 
 */
package hu.hw.cloud.client.inf.analytics;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class AnalyticsModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(AnalyticsPresenter.class, AnalyticsPresenter.MyView.class, AnalyticsView.class,
				AnalyticsPresenter.MyProxy.class);
	}
}
