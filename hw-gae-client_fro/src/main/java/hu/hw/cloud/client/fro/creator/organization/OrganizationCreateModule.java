/**
 * 
 */
package hu.hw.cloud.client.fro.creator.organization;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class OrganizationCreateModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(OrganizationCreatePresenter.class, OrganizationCreatePresenter.MyView.class, OrganizationCreateView.class,
				OrganizationCreatePresenter.MyProxy.class);
	}
}
