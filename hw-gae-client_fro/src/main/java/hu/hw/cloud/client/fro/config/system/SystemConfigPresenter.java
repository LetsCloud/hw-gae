package hu.hw.cloud.client.fro.config.system;

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
import hu.hw.cloud.client.fro.browser.appuser.AppUserBrowserFactory;
import hu.hw.cloud.client.fro.browser.usergroup.UserGroupBrowserFactory;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;

public class SystemConfigPresenter
		extends AbstractConfigPresenter<SystemConfigPresenter.MyView, SystemConfigPresenter.MyProxy>
		implements SystemConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(SystemConfigPresenter.class.getName());

	private static final String USER_GROUPS = "userGroups";
	public static final String APP_USERS = "appUsers";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.SYSTEM_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<SystemConfigPresenter> {
	}

	@Inject
	SystemConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			UserGroupBrowserFactory userGroupBrowserFactory, AppUserBrowserFactory appUserBrowserFactory,
			CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("SystemConfigPresenter()");

		setCaption(i18n.systemConfigTitle());
		setDescription(i18n.systemConfigDescription());
		setPlaceToken(CoreNameTokens.SYSTEM_CONFIG);

		addContent(i18n.userGroupBrowserTitle(), userGroupBrowserFactory.createUserGroupTablePresenter(), USER_GROUPS);
		addContent(i18n.userBrowserTitle(), appUserBrowserFactory.createAppUserTablePresenter(), APP_USERS);

		getView().setUiHandlers(this);
	}
}
