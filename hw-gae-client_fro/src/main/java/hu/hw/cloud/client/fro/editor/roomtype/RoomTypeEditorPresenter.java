/**
 * 
 */
package hu.hw.cloud.client.fro.editor.roomtype;

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

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenter;
import hu.hw.cloud.client.fro.editor.AbstractEditorView;
import hu.hw.cloud.shared.api.RoomTypeResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomTypeEditorPresenter
		extends AbstractEditorPresenter<RoomTypeDto, RoomTypeEditorPresenter.MyView, RoomTypeEditorPresenter.MyProxy>
		implements RoomTypeEditorUiHandlers {
	private static Logger logger = Logger.getLogger(RoomTypeEditorPresenter.class.getName());

	public interface MyView extends AbstractEditorView<RoomTypeDto>, HasUiHandlers<RoomTypeEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ROOMTYPE_EDITOR)
	interface MyProxy extends ProxyPlace<RoomTypeEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RoomTypeResource> resourceDelegate;
	private final HotelDataSource hotelDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	RoomTypeEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<RoomTypeResource> resourceDelegate, HotelDataSource hotelDataSource,CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("RoomTypeEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		if (isNew()) {
			hotelDataSource.setWebSafeKey(filters.get(AbstractBrowserPresenter.PARAM_HOTEL_KEY));
			LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {
				@Override
				public void onSuccess(LoadResult<HotelDto> loadResult) {
					SetPageTitleEvent.fire(i18n.roomTypeEditorCreateTitle(), loadResult.getData().get(0).getName(), MenuItemType.MENU_ITEM,
							RoomTypeEditorPresenter.this);
					create();
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
				}
			};
			hotelDataSource.get(new LoadConfig<HotelDto>(0, 0, null, null), hotelLoadCallback);
		} else {
			edit(filters.get(AbstractBrowserPresenter.PARAM_DTO_KEY));
		}
	}

	@Override
	protected RoomTypeDto createDto() {
		logger.info("RoomTypeEditorPresenter().createDto()");
		RoomTypeDto dto = new RoomTypeDto();
		dto.setHotel(currentUser.getAppUserDto().getDefaultHotel());
		logger.info("RoomTypeEditorPresenter().createDto()->dto=" + dto);
		return dto;
	}

	private void edit(String webSafeKey) {
		logger.info("RoomTypeEditorPresenter().edit()->webSafeKey=" + webSafeKey);
		resourceDelegate.withCallback(new AsyncCallback<RoomTypeDto>() {
			@Override
			public void onSuccess(RoomTypeDto dto) {
				SetPageTitleEvent.fire(i18n.roomTypeEditorModifyTitle(), dto.getHotel().getName(),
						MenuItemType.MENU_ITEM, RoomTypeEditorPresenter.this);

				getView().edit(false, dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).get(webSafeKey);
	}

	@Override
	public void save(RoomTypeDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<RoomTypeDto>() {
			@Override
			public void onSuccess(RoomTypeDto dto) {
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