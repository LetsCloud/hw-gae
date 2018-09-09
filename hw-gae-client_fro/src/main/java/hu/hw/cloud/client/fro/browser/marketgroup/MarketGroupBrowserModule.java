/**
 * 
 */
package hu.hw.cloud.client.fro.browser.marketgroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.meditor.marketgroup.MarketGroupEditorModule;

/**
 * @author robi
 *
 */
public class MarketGroupBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new MarketGroupEditorModule());

		bindPresenterWidget(MarketGroupBrowserPresenter.class, MarketGroupBrowserPresenter.MyView.class,
				MarketGroupBrowserView.class);

		install(new GinFactoryModuleBuilder().build(MarketGroupBrowserFactory.class));
	}
}
