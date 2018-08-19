/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile.customer;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class CustomerEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenterWidget(CustomerEditorPresenter.class, CustomerEditorPresenter.MyView.class,
				CustomerEditorView.class);

		install(new GinFactoryModuleBuilder().build(CustomerEditorFactory.class));
	}
}
