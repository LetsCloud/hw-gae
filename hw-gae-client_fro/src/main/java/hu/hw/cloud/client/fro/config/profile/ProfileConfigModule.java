package hu.hw.cloud.client.fro.config.profile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.browser.organization.OrganizationBrowserModule;
import hu.hw.cloud.client.fro.browser.profilegroup.ProfileGroupBrowserModule;

public class ProfileConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new ProfileGroupBrowserModule());
		install(new OrganizationBrowserModule());

		bindPresenter(ProfileConfigPresenter.class, ProfileConfigPresenter.MyView.class, ProfileConfigView.class,
				ProfileConfigPresenter.MyProxy.class);
	}
}
