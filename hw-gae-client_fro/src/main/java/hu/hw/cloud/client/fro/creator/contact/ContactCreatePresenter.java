/**
 * 
 */
package hu.hw.cloud.client.fro.creator.contact;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.client.fro.creator.organization.OrganizationCreatePresenter;
import hu.hw.cloud.client.fro.editor.profile.contact.ContactEditorFactory;
import hu.hw.cloud.client.fro.editor.profile.contact.ContactEditorPresenter;
import hu.hw.cloud.shared.api.ContactResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.profile.ContactDto;

/**
 * @author robi
 *
 */
public class ContactCreatePresenter extends Presenter<ContactCreatePresenter.MyView, ContactCreatePresenter.MyProxy>
		implements ContactCreateUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationCreatePresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ContactCreateUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.CONTACT_CREATOR)
	interface MyProxy extends ProxyPlace<ContactCreatePresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	private final PlaceManager placeManager;

	private final ResourceDelegate<ContactResource> resourceDelegate;

	private final ContactEditorPresenter editor;

	private final CoreMessages i18n;

	@Inject
	ContactCreatePresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<ContactResource> resourceDelegate, ContactEditorFactory editorFactory, CoreMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("ContactCreatePresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.editor = editorFactory.createContactEditor();
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		editor.setReadOnly(false);
		setInSlot(SLOT_CONTENT, editor);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.contactCreatorTitle(), i18n.contactCreatorDescription(), MenuItemType.MENU_ITEM,
				this);
	}

	@Override
	public void save(ContactDto dto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<ContactDto>(this) {
			@Override
			public void onSuccess(ContactDto dto) {
				PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.SYSTEM_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
//		getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	public void cancel() {
// TODO Auto-generated method stub

	}
}