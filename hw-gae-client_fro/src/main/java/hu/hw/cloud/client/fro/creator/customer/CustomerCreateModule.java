/**
 * 
 */
package hu.hw.cloud.client.fro.creator.customer;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class CustomerCreateModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(CustomerCreatePresenter.class, CustomerCreatePresenter.MyView.class, CustomerCreateView.class,
				CustomerCreatePresenter.MyProxy.class);
	}
}
