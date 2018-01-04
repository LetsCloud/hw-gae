/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class PushModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindSingletonPresenterWidget(PushPresenter.class, PushPresenter.MyView.class, PushView.class);

		install(new GinFactoryModuleBuilder().build(PushPresenterFactory.class));
	}

}
