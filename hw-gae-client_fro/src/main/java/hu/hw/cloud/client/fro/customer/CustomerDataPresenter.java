/**
 * 
 */
package hu.hw.cloud.client.fro.customer;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenterWidget;
import hu.hw.cloud.client.fro.editor.AbstractEditorView;
import hu.hw.cloud.shared.api.CustomerResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;
import hu.hw.cloud.shared.dto.profile.AddressDto;

/**
 * @author robi
 *
 */
public class CustomerDataPresenter extends AbstractEditorPresenterWidget<OrganizationDto, CustomerDataPresenter.MyView>
		implements CustomerDataUiHandlers {
	private static Logger logger = Logger.getLogger(CustomerDataPresenter.class.getName());

	public interface MyView extends AbstractEditorView<OrganizationDto>, HasUiHandlers<CustomerDataUiHandlers> {
		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<CustomerResource> resourceDelegate;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	@Inject
	CustomerDataPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<CustomerResource> resourceDelegate, CurrentUser currentUser, CoreMessages i18nCore) {
		super(eventBus, placeManager, view);
		logger.info("CustomerDataPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
	}

	@Override
	public void save(OrganizationDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<OrganizationDto>() {
			@Override
			public void onSuccess(OrganizationDto dto) {
				PlaceRequest placeRequest = new Builder().nameToken(FroNameTokens.HOTEL_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	protected void loadData() {
		if (isNew()) {
			create();
		} else {
			edit(filters.get(AbstractBrowserPresenter.PARAM_DTO_KEY));
		}
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<OrganizationDto>() {
			@Override
			public void onSuccess(OrganizationDto dto) {
				SetPageTitleEvent.fire(i18nCore.roomEditorModifyTitle(), dto.getName(), MenuItemType.MENU_ITEM,
						CustomerDataPresenter.this);

				getView().edit(false, dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	protected OrganizationDto createDto() {
		OrganizationDto dto = new OrganizationDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
//		dto.setPostalAddressDtos(new ArrayList<PostalAddressDto>());
		dto.getAddresses().add(new AddressDto());
		return dto;
	}

	@Override
	public void show(String dataId) {
		// TODO Auto-generated method stub

	}
}