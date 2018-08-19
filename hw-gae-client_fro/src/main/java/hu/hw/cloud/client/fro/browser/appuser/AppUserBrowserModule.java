/**
 * 
 */
package hu.hw.cloud.client.fro.browser.appuser;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.appuser.AppUserEditorModule;

/**
 * @author robi
 *
 */
public class AppUserBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new AppUserEditorModule());

		bindPresenterWidget(AppUserBrowserPresenter.class, AppUserBrowserPresenter.MyView.class,
				AppUserBrowserView.class);

		install(new GinFactoryModuleBuilder().build(AppUserBrowserFactory.class));
	}
}
