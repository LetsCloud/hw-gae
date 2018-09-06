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
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.fro.FroNameTokens;
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

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.PROFILE_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ProfileConfigPresenter> {
	}

	@Inject
	ProfileConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			ProfileGroupBrowserFactory profileGroupFactory, OrganizationBrowserFactory customerFactory, FroMessages i18n,
			CoreMessages i18nCore) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("ProfileConfigPresenter()");

		setCaption(i18n.mainMenuItemProfileConfig());

		addContent(i18nCore.profileGroupBrowserTitle(), profileGroupFactory.createProfileGroupBrowser());
		addContent(i18nCore.customerBrowserTitle(), customerFactory.createCustomerBrowser());

		getView().setUiHandlers(this);
	}

}
