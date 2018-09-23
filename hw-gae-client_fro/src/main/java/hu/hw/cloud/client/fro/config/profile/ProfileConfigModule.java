package hu.hw.cloud.client.fro.config.profile;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.browser.contact.ContactBrowserModule;
import hu.hw.cloud.client.fro.browser.organization.OrganizationBrowserModule;
import hu.hw.cloud.client.fro.browser.profilegroup.ProfileGroupBrowserModule;
import hu.hw.cloud.client.fro.browser.relationship.RelationshipBrowserModule;

public class ProfileConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new ProfileGroupBrowserModule());
		install(new OrganizationBrowserModule());
		install(new ContactBrowserModule());
		install(new RelationshipBrowserModule());

		bindPresenter(ProfileConfigPresenter.class, ProfileConfigPresenter.MyView.class, ProfileConfigView.class,
				ProfileConfigPresenter.MyProxy.class);
	}
}
