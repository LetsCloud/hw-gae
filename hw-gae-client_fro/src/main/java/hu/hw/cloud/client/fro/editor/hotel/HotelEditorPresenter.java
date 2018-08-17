/**
 * 
 */
package hu.hw.cloud.client.fro.editor.hotel;

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
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenter;
import hu.hw.cloud.client.fro.editor.EditorView;
import hu.hw.cloud.shared.api.HotelResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelEditorPresenter
		extends AbstractEditorPresenter<HotelDto, HotelEditorPresenter.MyView, HotelEditorPresenter.MyProxy>
		implements HotelEditorUiHandlers {
	private static Logger logger = Logger.getLogger(HotelEditorPresenter.class.getName());

	public interface MyView extends EditorView<HotelDto>, HasUiHandlers<HotelEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.HOTEL_EDITOR)
	interface MyProxy extends ProxyPlace<HotelEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<HotelResource> resourceDelegate;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	HotelEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<HotelResource> resourceDelegate, CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("HotelEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		if (isNew()) {
			SetPageTitleEvent.fire(i18n.hotelEditorCreateTitle(), "", MenuItemType.MENU_ITEM, HotelEditorPresenter.this);
			create();
		} else {
			SetPageTitleEvent.fire(i18n.hotelEditorModifyTitle(), "", MenuItemType.MENU_ITEM, HotelEditorPresenter.this);
			edit(filters.get(AbstractBrowserPresenter.PARAM_DTO_KEY));
		}
	}

	@Override
	protected HotelDto createDto() {
		HotelDto dto = new HotelDto();
		dto.setAccountDto(currentUser.getAppUserDto().getAccountDto());
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<HotelDto>() {
			@Override
			public void onSuccess(HotelDto dto) {
				logger.info("AppUserEditorPresenter().edit().onSuccess()->dto=" + dto);
				getView().edit(false, dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	public void save(HotelDto userDto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<HotelDto>(this) {
			@Override
			public void onSuccess(HotelDto userDto) {
				PlaceRequest placeRequest = new Builder().nameToken(FroNameTokens.HOTEL_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(userDto);
	}
}