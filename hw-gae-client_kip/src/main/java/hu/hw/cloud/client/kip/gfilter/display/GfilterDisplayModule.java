/**
 * 
 */
package hu.hw.cloud.client.kip.gfilter.display;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.kip.gfilter.config.GfilterConfigModule;

/**
 * @author CR
 *
 */
public class GfilterDisplayModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindSingletonPresenterWidget(GfilterDisplayPresenter.class, GfilterDisplayPresenter.MyView.class,
				GfilterDisplayView.class);

		install(new GfilterConfigModule());
	}
}