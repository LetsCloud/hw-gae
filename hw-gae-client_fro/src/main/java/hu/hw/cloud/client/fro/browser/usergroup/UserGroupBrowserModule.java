/**
 * 
 */
package hu.hw.cloud.client.fro.browser.usergroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.meditor.usergroup.UserGroupEditorModule;

/**
 * @author robi
 *
 */
public class UserGroupBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new UserGroupEditorModule());

		bindPresenterWidget(UserGroupBrowserPresenter.class, UserGroupBrowserPresenter.MyView.class,
				UserGroupBrowserView.class);

		install(new GinFactoryModuleBuilder().build(UserGroupBrowserFactory.class));
	}
}
