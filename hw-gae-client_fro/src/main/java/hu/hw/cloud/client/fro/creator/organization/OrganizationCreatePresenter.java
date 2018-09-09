/**
 * 
 */
package hu.hw.cloud.client.fro.creator.organization;

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
import hu.hw.cloud.client.fro.editor.profile.organization.OrganizationEditorFactory;
import hu.hw.cloud.client.fro.editor.profile.organization.OrganizationEditorPresenter;
import hu.hw.cloud.shared.api.OrganizationResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public class OrganizationCreatePresenter
		extends Presenter<OrganizationCreatePresenter.MyView, OrganizationCreatePresenter.MyProxy>
		implements OrganizationCreateUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationCreatePresenter.class.getName());

	interface MyView extends View, HasUiHandlers<OrganizationCreateUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ORGANIZATION_CREATOR)
	interface MyProxy extends ProxyPlace<OrganizationCreatePresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	private final PlaceManager placeManager;

	private final ResourceDelegate<OrganizationResource> resourceDelegate;

	private final OrganizationEditorPresenter editor;

	private final CoreMessages i18n;

	@Inject
	OrganizationCreatePresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<OrganizationResource> resourceDelegate, OrganizationEditorFactory editorFactory,
			CoreMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("CustomerCreatePresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.editor = editorFactory.createOrganizationEditor();
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
		SetPageTitleEvent.fire(i18n.organizationCreatorTitle(), i18n.organizationCreatorDescription(),
				MenuItemType.MENU_ITEM, this);
	}

	@Override
	public void save(OrganizationDto dto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<OrganizationDto>(this) {
			@Override
			public void onSuccess(OrganizationDto dto) {
				PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.SYSTEM_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
//				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}
}