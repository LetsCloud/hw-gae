/**
 * 
 */
package hu.hw.cloud.client.core.users.editor;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class UserEditModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(UserEditPresenter.class, UserEditPresenter.MyView.class, UserEditView.class);

		install(new GinFactoryModuleBuilder().build(UserEditPresenterFactory.class));
	}
}
