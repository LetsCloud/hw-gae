/**
 * 
 */
package hu.hw.cloud.client.fro.config.system;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.browser.appuser.AppUserBrowserModule;
import hu.hw.cloud.client.fro.browser.usergroup.UserGroupBrowserModule;

/**
 * @author CR
 *
 */
public class SystemConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new UserGroupBrowserModule());
		install(new AppUserBrowserModule());

		bindPresenter(SystemConfigPresenter.class, SystemConfigPresenter.MyView.class, SystemConfigView.class,
				SystemConfigPresenter.MyProxy.class);
	}
}
