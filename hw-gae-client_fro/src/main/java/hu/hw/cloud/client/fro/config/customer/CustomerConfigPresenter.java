/**
 * 
 */
package hu.hw.cloud.client.fro.config.customer;

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

/**
 * @author robi
 *
 */
public class CustomerConfigPresenter
		extends AbstractConfigPresenter<CustomerConfigPresenter.MyView, CustomerConfigPresenter.MyProxy>
		implements CustomerConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(CustomerConfigPresenter.class.getName());

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.CUSTOMER_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<CustomerConfigPresenter> {
	}

	@Inject
	CustomerConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy,
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
