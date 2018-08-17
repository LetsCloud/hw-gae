/**
 * 
 */
package hu.hw.cloud.client.fro.editor.room;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import hu.hw.cloud.client.core.datasource.RoomTypeDataSource;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenter;
import hu.hw.cloud.client.fro.editor.EditorView;
import hu.hw.cloud.shared.api.RoomResource;
import hu.hw.cloud.shared.cnst.InventoryType;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.hotel.HotelDto;
import hu.hw.cloud.shared.dto.hotel.RoomAvailabilityDto;
import hu.hw.cloud.shared.dto.hotel.RoomDto;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomEditorPresenter
		extends AbstractEditorPresenter<RoomDto, RoomEditorPresenter.MyView, RoomEditorPresenter.MyProxy>
		implements RoomEditorUiHandlers {
	private static Logger logger = Logger.getLogger(RoomEditorPresenter.class.getName());

	public interface MyView extends EditorView<RoomDto>, HasUiHandlers<RoomEditorUiHandlers> {
		void setRoomTypeData(List<RoomTypeDto> roomTypeData);

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.ROOM_EDITOR)
	interface MyProxy extends ProxyPlace<RoomEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RoomResource> resourceDelegate;
	private final RoomTypeDataSource roomTypeDataSource;
	private final HotelDataSource hotelDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18nCore;

	@Inject
	RoomEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<RoomResource> resourceDelegate, RoomTypeDataSource roomTypeDataSource,
			HotelDataSource hotelDataSource, CurrentUser currentUser, CoreMessages i18nCore) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("RoomTypeEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.roomTypeDataSource = roomTypeDataSource;
		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
		this.i18nCore = i18nCore;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		roomTypeDataSource.setOnlyActive(true);
		roomTypeDataSource.setHotelKey(filters.get(AbstractBrowserPresenter.PARAM_HOTEL_KEY));
		hotelDataSource.setWebSafeKey(filters.get(AbstractBrowserPresenter.PARAM_HOTEL_KEY));
		
		LoadCallback<RoomTypeDto> roomTypeLoadCallback = new LoadCallback<RoomTypeDto>() {
			@Override
			public void onSuccess(LoadResult<RoomTypeDto> loadResult) {
				List<RoomTypeDto> filteredResult = loadResult.getData().stream()
						.filter(roomType -> roomType.getInventoryType().equals(InventoryType.PHYS))
						.collect(Collectors.toList());
				getView().setRoomTypeData(filteredResult);
				if (isNew()) {
					LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {
						@Override
						public void onSuccess(LoadResult<HotelDto> loadResult) {
							SetPageTitleEvent.fire(i18nCore.roomEditorCreateTitle(),
									loadResult.getData().get(0).getName(), MenuItemType.MENU_ITEM,
									RoomEditorPresenter.this);
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
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		roomTypeDataSource.load(new LoadConfig<RoomTypeDto>(0, 0, null, null), roomTypeLoadCallback);
	}

	@Override
	protected RoomDto createDto() {
		RoomDto dto = new RoomDto();
		dto.setHotelDto(currentUser.getAppUserDto().getDefaultHotelDto());
		RoomAvailabilityDto ra = new RoomAvailabilityDto(true, new Date());
		dto.addRoomAvailabilityDto(ra);
		return dto;
	}

	private void edit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<RoomDto>() {
			@Override
			public void onSuccess(RoomDto dto) {
				List<RoomAvailabilityDto> availabilities = dto.getRoomAvailabilityDtos();
				if (availabilities != null)
					availabilities.sort(
							(RoomAvailabilityDto o1, RoomAvailabilityDto o2) -> o1.getDate().compareTo(o2.getDate()));

				SetPageTitleEvent.fire(i18nCore.roomEditorModifyTitle(), dto.getHotelDto().getName(),
						MenuItemType.MENU_ITEM, RoomEditorPresenter.this);

				getView().edit(false, dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).get(webSafeKey);
	}

	@Override
	public void save(RoomDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<RoomDto>() {
			@Override
			public void onSuccess(RoomDto dto) {
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