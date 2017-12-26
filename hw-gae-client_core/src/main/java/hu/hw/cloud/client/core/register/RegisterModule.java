/**
 * 
 */
package hu.hw.cloud.client.core.register;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class RegisterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(RegisterPresenter.class, RegisterPresenter.MyView.class, RegisterView.class,
				RegisterPresenter.MyProxy.class);
	}
}
