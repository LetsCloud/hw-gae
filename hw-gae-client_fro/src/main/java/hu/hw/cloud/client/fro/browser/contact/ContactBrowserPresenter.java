/**
 * 
 */
package hu.hw.cloud.client.fro.browser.contact;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.shared.api.ContactResource;
import hu.hw.cloud.shared.dto.profile.ContactDto;

/**
 * @author robi
 *
 */
public class ContactBrowserPresenter extends AbstractBrowserPresenter<ContactDto, ContactBrowserPresenter.MyView>
		implements ContactBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(ContactBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<ContactBrowserUiHandlers> {
		void setData(List<ContactDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<ContactResource> resourceDelegate;

	@Inject
	ContactBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<ContactResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view, placeManager);
		logger.info("ContactBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<ContactDto>>() {
			@Override
			public void onSuccess(List<ContactDto> result) {
				getView().setData(result);
			}
		}).list();
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.CONTACT_CREATOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.CONTACT_DISPLAY;
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}
}