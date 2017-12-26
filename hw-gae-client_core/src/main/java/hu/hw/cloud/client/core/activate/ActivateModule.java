/**
 * 
 */
package hu.hw.cloud.client.core.activate;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class ActivateModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ActivatePresenter.class, ActivatePresenter.MyView.class, ActivateView.class,
				ActivatePresenter.MyProxy.class);
	}
}
