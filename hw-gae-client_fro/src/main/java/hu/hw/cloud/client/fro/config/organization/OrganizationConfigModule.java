/**
 * 
 */
package hu.hw.cloud.client.fro.config.organization;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class OrganizationConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		bindPresenter(OrganizationConfigPresenter.class, OrganizationConfigPresenter.MyView.class,
				OrganizationConfigView.class, OrganizationConfigPresenter.MyProxy.class);
	}
}
