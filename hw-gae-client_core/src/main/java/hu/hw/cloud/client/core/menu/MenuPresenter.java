/**
 * 
 */
package hu.hw.cloud.client.core.menu;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.event.ContentPushEvent.MenuState;
import hu.hw.cloud.client.core.event.SetPageTitleEvent.SetPageTitleHandler;
import hu.hw.cloud.client.core.security.AppData;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.security.HasPermissionsGatekeeper;
import hu.hw.cloud.shared.AuthService;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.core.MenuItemDto;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author CR
 *
 */
public class MenuPresenter extends PresenterWidget<MenuPresenter.MyView>
		implements MenuUiHandlers, SetPageTitleHandler {
	private static Logger logger = Logger.getLogger(MenuPresenter.class.getName());

	public static final SingleSlot<PresenterWidget<?>> SLOT_NAVBAR = new SingleSlot<>();

	private final HasPermissionsGatekeeper menItemGatekeeper;

	interface MyView extends View, HasUiHandlers<MenuUiHandlers> {
		void checkPermittedWidgets();

		void setPageTitle(String title, String description);

		void setAccountName(String accountName);

		void setUserName(String userName);

		void setUserImageUrl(String url);

		void setHotelName(String hotelName);

		void setPermittedHotels(List<HotelDto> hotels);

		void setBusinessDate(Date businessDate);

		void setMenuItems(List<MenuItemDto> menuItems);

		void inactivateSingleLinks();

		void closeCollapisbles();

		void setAppCode(String appCode);
	}

	private final PlaceManager placeManager;
	private final RestDispatch dispatcher;
	private final AuthService authService;
	private final CurrentUser currentUser;
	private final AppData appData;

	@Inject
	MenuPresenter(EventBus eventBus, MyView view, PlaceManager placeManager, RestDispatch dispatcher,
			AuthService authService, CurrentUser currentUser, AppData appData,
			HasPermissionsGatekeeper menItemGatekeeper) {
		super(eventBus, view);
		logger.info("MenuPresenter()");

		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.authService = authService;
		this.currentUser = currentUser;
		this.appData = appData;
		this.menItemGatekeeper = menItemGatekeeper;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		addRegisteredHandler(SetPageTitleEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		if (appData.getAppCode() != null) {
			getView().setAppCode(appData.getAppCode());
		}
		/*
		 * getView().checkPermittedWidgets();
		 * getView().setAccountName(currentUser.getAppUserDto().getAccountDto().getName(
		 * )); getView().setHotelName(currentUser.getCurrentHotelDto().getName());
		 * getView().setUserName(currentUser.getAppUserDto().getUsername());
		 * getView().setBusinessDate(currentUser.getCurrentHotelDto().getBusinessDate())
		 * ;
		 */
	}

	@Override
	public void onSetPageTitle(SetPageTitleEvent event) {
		getView().setPageTitle(event.getTitle(), event.getDescription());
	}

	@Override
	public Boolean canReveal(String permission) {
		String[] permissions = { permission };
		menItemGatekeeper.withParams(permissions);
		return menItemGatekeeper.canReveal();
	}

	@Override
	public void setContentPush(MenuState menuState) {
		ContentPushEvent.fire(this, menuState);
	}

	public void setNavBarWidget(PresenterWidget<?> widget) {
		setInSlot(SLOT_NAVBAR, widget);

	}

	public void setMenuItems(List<MenuItemDto> menuItems) {
		getView().setMenuItems(menuItems);
	}

	public void adjustMenuItems(MenuItemType triggerItem) {

		if (triggerItem.equals(MenuItemType.MENU_ITEM)) {
			getView().inactivateSingleLinks();
		} else {
			getView().closeCollapisbles();
		}

	}

	@Override
	public void logout() {
		dispatcher.execute(authService.logout(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				currentUser.setLoggedIn(false);
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.LOGIN).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}

	@Override
	public void referesh() {
		getView().setAccountName(currentUser.getAppUserDto().getAccountDto().getName());
		if (currentUser.getCurrentHotelDto()!=null) {
			if (!Strings.isNullOrEmpty(currentUser.getCurrentHotelDto().getName()))
				getView().setHotelName(currentUser.getCurrentHotelDto().getName());
		}
		getView().setPermittedHotels(currentUser.getAppUserDto().getAvailableHotelDtos());
		getView().setUserImageUrl(currentUser.getAppUserDto().getPicture());
		getView().setUserName(currentUser.getAppUserDto().getName());
	}

	@Override
	public void setCurrentHotel(HotelDto hotel) {
		currentUser.setCurrentHotelDto(hotel);
		getView().setHotelName(hotel.getName());
	}
}