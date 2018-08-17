/**
 * 
 */
package hu.hw.cloud.client.fro.browser.marketgroup;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.meditor.marketgroup.MarketGroupEditorModule;

/**
 * @author robi
 *
 */
public class MarketGroupTableModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new MarketGroupEditorModule());

		bindPresenterWidget(MarketGroupTablePresenter.class, MarketGroupTablePresenter.MyView.class,
				MarketGroupTableView.class);
	}
}
