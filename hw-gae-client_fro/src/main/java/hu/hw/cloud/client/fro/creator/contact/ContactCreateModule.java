/**
 * 
 */
package hu.hw.cloud.client.fro.creator.contact;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class ContactCreateModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ContactCreatePresenter.class, ContactCreatePresenter.MyView.class, ContactCreateView.class,
				ContactCreatePresenter.MyProxy.class);
	}
}
