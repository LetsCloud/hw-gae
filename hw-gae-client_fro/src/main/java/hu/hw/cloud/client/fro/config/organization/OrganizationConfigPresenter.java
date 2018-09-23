/**
 * 
 */
package hu.hw.cloud.client.fro.config.organization;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.fro.browser.organization.OrganizationBrowserFactory;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
import hu.hw.cloud.client.fro.editor.AbstractDisplayPresenterWidget;
import hu.hw.cloud.client.fro.editor.profile.organization.OrganizationEditorFactory;
import hu.hw.cloud.client.fro.i18n.FroMessages;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;;

/**
 * @author robi
 *
 */
public class OrganizationConfigPresenter
		extends AbstractConfigPresenter<OrganizationConfigPresenter.MyView, OrganizationConfigPresenter.MyProxy>
		implements OrganizationConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(OrganizationConfigPresenter.class.getName());

	private static final String GENERAL_DATA = "generalData";

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ORGANIZATION_DISPLAY)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<OrganizationConfigPresenter> {
	}

	private String webSafeKey;

	@Inject
	OrganizationConfigPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			OrganizationEditorFactory organizationEditorFactory, OrganizationBrowserFactory customerFactory,
			FroMessages i18n, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("OrganizationConfigPresenter()");

		setCaption(i18nCore.organizationConfigTitle());
		setDescription(i18nCore.organizationConfigDescription());
		setPlaceToken(CoreNameTokens.ORGANIZATION_DISPLAY);

		addContent("Base Data", organizationEditorFactory.createOrganizationEditor(), GENERAL_DATA);
//		addContent(i18nCore.customerBrowserTitle(), customerFactory.createCustomerBrowser());

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		webSafeKey = request.getParameter(WEBSAFEKEY, null);
		logger.info("OrganizationConfigPresenter().prepareFromRequest()->webSafeKey=" + webSafeKey);
		showContent(0);
		firstReveal = false;
	}

	@Override
	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		logger.info("OrganizationConfigPresenter().beforeShowContent()");
		((AbstractDisplayPresenterWidget<?>) widget).setReadOnly(true);
		((AbstractDisplayPresenterWidget<?>) widget).setWebSafeKey(webSafeKey);
		return widget;
	}

}
