/**
 * 
 */
package hu.hw.cloud.client.fro.browser.organization;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class OrganizationBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

//		install(new ProfileGroupEditorModule());

		bindPresenterWidget(OrganizationBrowserPresenter.class, OrganizationBrowserPresenter.MyView.class,
				OrganizationBrowserView.class);

		install(new GinFactoryModuleBuilder().build(OrganizationBrowserFactory.class));
	}
}
