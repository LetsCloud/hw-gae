/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class NotificationsModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(NotificationsPresenter.class, NotificationsPresenter.MyView.class, NotificationsView.class,
				NotificationsPresenter.MyProxy.class);
	}
}
