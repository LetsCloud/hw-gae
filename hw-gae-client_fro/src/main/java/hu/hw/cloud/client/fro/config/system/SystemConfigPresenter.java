package hu.hw.cloud.client.fro.config.system;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.BrowserPresenterFactory;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
import hu.hw.cloud.client.fro.config.PresenterWidgetStore;
import hu.hw.cloud.client.fro.i18n.FroMessages;

public class SystemConfigPresenter
		extends AbstractConfigPresenter<SystemConfigPresenter.MyView, SystemConfigPresenter.MyProxy>
		implements SystemConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(SystemConfigPresenter.class.getName());

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.SYSTEM_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<SystemConfigPresenter> {
	}

	@Inject
	SystemConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			BrowserPresenterFactory dtoTablePresenterFactory, FroMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("SystemConfigPresenter()");

		setCaption(i18n.mainMenuItemSystemConfig());

		addTable(1,
				new PresenterWidgetStore(i18n.systemConfigUserGroup(), dtoTablePresenterFactory.createUserGroupTablePresenter()));
		addTable(2, new PresenterWidgetStore(i18n.systemConfigAppUser(), dtoTablePresenterFactory.createAppUserTablePresenter()));
		addTable(3,
				new PresenterWidgetStore(i18n.systemConfigCustomer(), dtoTablePresenterFactory.createCustomerTablePresenter()));

		getView().setUiHandlers(this);
	}
}
