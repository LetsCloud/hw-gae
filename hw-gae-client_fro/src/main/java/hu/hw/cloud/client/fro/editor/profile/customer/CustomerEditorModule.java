/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile.customer;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class CustomerEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {	
		bindPresenter(CustomerEditorPresenter.class, CustomerEditorPresenter.MyView.class, CustomerEditorView.class,
				CustomerEditorPresenter.MyProxy.class);
	}
}
