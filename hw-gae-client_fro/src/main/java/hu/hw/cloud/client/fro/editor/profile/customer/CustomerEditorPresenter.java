/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile.customer;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenter;
import hu.hw.cloud.client.fro.editor.EditorView;
import hu.hw.cloud.shared.api.CustomerResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.CustomerDto;
import hu.hw.cloud.shared.dto.profile.PostalAddressDto;

/**
 * @author robi
 *
 */
public final class CustomerEditorPresenter
		extends AbstractEditorPresenter<CustomerDto, CustomerEditorPresenter.MyView, CustomerEditorPresenter.MyProxy>
		implements CustomerEditorUiHandlers {
	private static Logger logger = Logger.getLogger(CustomerEditorPresenter.class.getName());

	interface MyView extends EditorView<CustomerDto>, HasUiHandlers<CustomerEditorUiHandlers> {
		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.CUSTOMER_EDITOR)
	interface MyProxy extends ProxyPlace<CustomerEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<CustomerResource> resourceDelegate;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	@Inject
	CustomerEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<CustomerResource> resourceDelegate, CurrentUser currentUser, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("RoomTypeEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		if (isNew()) {
			create();
		} else {
			edit(filters.get(AbstractBrowserPresenter.PARAM_DTO_KEY));
		}
	}

	@Override
	protected CustomerDto createDto() {
		CustomerDto dto = new CustomerDto();
		dto.setAccountDto(currentUser.getAppUserDto().getAccountDto());
//		dto.setPostalAddressDtos(new ArrayList<PostalAddressDto>());
		dto.getPostalAddressDtos().add(new PostalAddressDto());
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<CustomerDto>() {
			@Override
			public void onSuccess(CustomerDto dto) {
				SetPageTitleEvent.fire(i18nCore.roomEditorModifyTitle(), dto.getName(), MenuItemType.MENU_ITEM,
						CustomerEditorPresenter.this);

				getView().edit(false, dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	public void save(CustomerDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<CustomerDto>() {
			@Override
			public void onSuccess(CustomerDto dto) {
				PlaceRequest placeRequest = new Builder().nameToken(FroNameTokens.HOTEL_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}
}