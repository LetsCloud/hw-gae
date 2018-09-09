/**
 * 
 */
package hu.hw.cloud.client.fro.config.contact;

import static hu.hw.cloud.shared.api.ApiParameters.WEBSAFEKEY;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
import hu.hw.cloud.client.fro.editor.AbstractDisplayPresenterWidget;
import hu.hw.cloud.client.fro.editor.profile.contact.ContactEditorFactory;
import hu.hw.cloud.client.fro.i18n.FroMessages;

/**
 * @author robi
 *
 */
public class ContactConfigPresenter
		extends AbstractConfigPresenter<ContactConfigPresenter.MyView, ContactConfigPresenter.MyProxy>
		implements ContactConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(ContactConfigPresenter.class.getName());

	interface MyView extends AbstractConfigPresenter.MyView {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.CONTACT_DISPLAY)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ContactConfigPresenter> {
	}

	private String webSafeKey;

	@Inject
	ContactConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy, ContactEditorFactory contactEditorFactory,
			FroMessages i18n, CoreMessages i18nCore) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("ContactConfigPresenter()");

		setCaption(i18nCore.contactDisplayTitle());
		setDescription(i18nCore.contactDisplayDescription());
		
		addContent(i18nCore.contactEditorDescription(), contactEditorFactory.createContactEditor());

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		webSafeKey = request.getParameter(WEBSAFEKEY, null);
		showContent(0);
		firstReveal = false;
	}

	@Override
	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		((AbstractDisplayPresenterWidget<?>) widget).setReadOnly(true);
		((AbstractDisplayPresenterWidget<?>) widget).setWebSafeKey(webSafeKey);
		return widget;
	}

}
