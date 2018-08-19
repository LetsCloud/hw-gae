/**
 * 
 */
package hu.hw.cloud.client.fro.browser.customer;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class CustomerBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

//		install(new ProfileGroupEditorModule());

		bindPresenterWidget(CustomerBrowserPresenter.class, CustomerBrowserPresenter.MyView.class,
				CustomerBrowserView.class);

		install(new GinFactoryModuleBuilder().build(CustomerBrowserFactory.class));
	}
}
