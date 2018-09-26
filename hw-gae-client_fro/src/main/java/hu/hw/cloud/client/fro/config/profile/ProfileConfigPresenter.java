/**
 * 
 */
package hu.hw.cloud.client.fro.config.profile;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.fro.browser.contact.ContactBrowserFactory;
import hu.hw.cloud.client.fro.browser.organization.OrganizationBrowserFactory;
import hu.hw.cloud.client.fro.browser.profilegroup.ProfileGroupBrowserFactory;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
import hu.hw.cloud.client.fro.i18n.FroMessages;

/**
 * @author robi
 *
 */
public class ProfileConfigPresenter
		extends AbstractConfigPresenter<ProfileConfigPresenter.MyView, ProfileConfigPresenter.MyProxy>
		implements ProfileConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(ProfileConfigPresenter.class.getName());

	private static final String PROFILE_GROUPS = "profileGroups";
	private static final String ORGANIZATIONS = "organizations";
	private static final String CONTACTS = "contacts";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.PROFILE_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ProfileConfigPresenter> {
	}

	@Inject
	ProfileConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ProfileGroupBrowserFactory profileGroupFactory, OrganizationBrowserFactory organizationFactory,
			ContactBrowserFactory contactFactory, FroMessages i18n, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);

		logger.info("ProfileConfigPresenter()");

		setCaption(i18nCore.profileConfigTitle());
		setDescription(i18nCore.profileConfigDescription());
		setPlaceToken(CoreNameTokens.PROFILE_CONFIG);

		addContent(i18nCore.profileGroupBrowserTitle(), profileGroupFactory.createProfileGroupBrowser(),
				PROFILE_GROUPS);
		addContent(i18nCore.organizationBrowserTitle(), organizationFactory.createOrganisationBrowser(), ORGANIZATIONS);
		addContent(i18nCore.contactBrowserTitle(), contactFactory.createContactBrowser(), CONTACTS);

		getView().setUiHandlers(this);
	}

}
