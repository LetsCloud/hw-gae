/**
 * 
 */
package hu.hw.cloud.client.fro.app;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;

import gwt.material.design.client.constants.IconType;
import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.menu.MenuPresenter;
import hu.hw.cloud.client.core.security.AppData;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.i18n.FroMessages;
import hu.hw.cloud.shared.AuthService;
import hu.hw.cloud.shared.NotificationService;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.core.MenuItemDto;

/**
 * @author CR
 *
 */
public class FroAppPresenter extends AppPresenter<FroAppPresenter.MyProxy> {

	private final FroMessages i18n;

	@ProxyStandard
	interface MyProxy extends Proxy<FroAppPresenter> {
	}

	@Inject
	FroAppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, FroMessages i18n,
			RestDispatch dispatch, AuthService authenticationService, NotificationService notificationService,
			CurrentUser currentUser, MenuPresenter menuPresenter, AppData appData) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, notificationService, menuPresenter,
				currentUser, appData);

		this.i18n = i18n;
	}

	@Override
	protected void onBind() {
		super.onBind();
		getMenuPresenter().setMenuItems(createMenuitems());
	}

	private List<MenuItemDto> createMenuitems() {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		MenuItemDto atendantsItem = new MenuItemDto();
		atendantsItem.setIndex(1);
		atendantsItem.setType(MenuItemType.MENU_ITEM);
		atendantsItem.setIcon(IconType.PEOPLE.name());
		atendantsItem.setText(i18n.mainMenuItemDashboard());
		atendantsItem.setNameToken(CoreNameTokens.HOME);
		menuItems.add(atendantsItem);

		// Reservation

		MenuItemDto resSubMenu = new MenuItemDto();
		resSubMenu.setIndex(2);
		resSubMenu.setType(MenuItemType.SUB_MENU);
		resSubMenu.setIcon(IconType.HOTEL.name());
		resSubMenu.setText(i18n.mainMenuGroupReception());
		resSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(resSubMenu);

		MenuItemDto resMenuItem1 = new MenuItemDto();
		resMenuItem1.setIndex(1);
		resMenuItem1.setType(MenuItemType.MENU_ITEM);
		resMenuItem1.setText(i18n.mainMenuItemReservation());
		resMenuItem1.setNameToken(FroNameTokens.COMMON_CONFIG);
		resSubMenu.addItem(resMenuItem1);

		MenuItemDto resMenuItem2 = new MenuItemDto();
		resMenuItem2.setIndex(2);
		resMenuItem2.setType(MenuItemType.MENU_ITEM);
		resMenuItem2.setText(i18n.mainMenuItemRoomplan());
		resMenuItem2.setNameToken(FroNameTokens.COMMON_CONFIG);
		resSubMenu.addItem(resMenuItem2);

		// Cashier

		MenuItemDto cashSubMenu = new MenuItemDto();
		cashSubMenu.setIndex(3);
		cashSubMenu.setType(MenuItemType.SUB_MENU);
		cashSubMenu.setIcon(IconType.EURO_SYMBOL.name());
		cashSubMenu.setText(i18n.mainMenuGroupCashier());
		cashSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(cashSubMenu);

		MenuItemDto cashMenuItem1 = new MenuItemDto();
		cashMenuItem1.setIndex(1);
		cashMenuItem1.setType(MenuItemType.MENU_ITEM);
		cashMenuItem1.setText(i18n.mainMenuItemBilling());
		cashMenuItem1.setNameToken(FroNameTokens.COMMON_CONFIG);
		cashSubMenu.addItem(cashMenuItem1);

		// Configuration

		MenuItemDto configSubMenu = new MenuItemDto();
		configSubMenu.setIndex(4);
		configSubMenu.setType(MenuItemType.SUB_MENU);
		configSubMenu.setIcon(IconType.SETTINGS.name());
		configSubMenu.setText(i18n.mainMenuGroupConfiguration());
		configSubMenu.setItems(new ArrayList<MenuItemDto>());
		menuItems.add(configSubMenu);

		MenuItemDto configMenuItem1 = new MenuItemDto();
		configMenuItem1.setIndex(1);
		configMenuItem1.setType(MenuItemType.MENU_ITEM);
		configMenuItem1.setText(i18n.mainMenuItemSystemConfig());
		configMenuItem1.setNameToken(FroNameTokens.SYSTEM_CONFIG);
		configSubMenu.addItem(configMenuItem1);

		MenuItemDto configMenuItem2 = new MenuItemDto();
		configMenuItem2.setIndex(2);
		configMenuItem2.setType(MenuItemType.MENU_ITEM);
		configMenuItem2.setText(i18n.mainMenuItemCommonConfig());
		configMenuItem2.setNameToken(FroNameTokens.COMMON_CONFIG);
		configSubMenu.addItem(configMenuItem2);

		MenuItemDto configMenuItem3 = new MenuItemDto();
		configMenuItem3.setIndex(3);
		configMenuItem3.setType(MenuItemType.MENU_ITEM);
		configMenuItem3.setText(i18n.mainMenuItemHotelConfig());
		configMenuItem3.setNameToken(FroNameTokens.COMMON_CONFIG);
		configSubMenu.addItem(configMenuItem3);

		return menuItems;
	}
}
