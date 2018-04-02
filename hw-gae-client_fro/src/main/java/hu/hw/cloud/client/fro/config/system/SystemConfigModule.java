/**
 * 
 */
package hu.hw.cloud.client.fro.config.system;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class SystemConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(SystemConfigPresenter.class, SystemConfigPresenter.MyView.class, SystemConfigView.class,
				SystemConfigPresenter.MyProxy.class);
	}
}
