/**
 * 
 */
package hu.hw.cloud.client.fro.browser;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.browser.customer.CustomerBrowserPresenter;
import hu.hw.cloud.client.fro.browser.customer.CustomerBrowserView;
import hu.hw.cloud.client.fro.browser.usergroup.UserGroupTablePresenter;
import hu.hw.cloud.client.fro.browser.usergroup.UserGroupTableView;
import hu.hw.cloud.client.fro.table.appuser.AppUserTablePresenter;
import hu.hw.cloud.client.fro.table.appuser.AppUserTableView;

/**
 * @author robi
 *
 */
public class BrowserModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(UserGroupTablePresenter.class, UserGroupTablePresenter.MyView.class,
				UserGroupTableView.class);

		bindPresenterWidget(AppUserTablePresenter.class, AppUserTablePresenter.MyView.class, AppUserTableView.class);

		bindPresenterWidget(CustomerBrowserPresenter.class, CustomerBrowserPresenter.MyView.class, CustomerBrowserView.class);

		bind(BrowserView.class);

		install(new GinFactoryModuleBuilder().build(BrowserPresenterFactory.class));
	}
}
