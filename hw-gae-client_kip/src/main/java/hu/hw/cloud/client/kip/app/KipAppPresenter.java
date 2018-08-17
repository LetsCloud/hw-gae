/**
 * 
 */
package hu.hw.cloud.client.kip.app;

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
import hu.hw.cloud.client.core.app.AppServiceWorkerManager;
import hu.hw.cloud.client.core.menu.MenuPresenter;
import hu.hw.cloud.client.core.security.AppData;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.kip.KipNameTokens;
import hu.hw.cloud.client.kip.gfilter.config.GfilterConfigPresenter;
import hu.hw.cloud.client.kip.gfilter.config.GfilterConfigPresenterFactory;
import hu.hw.cloud.client.kip.gfilter.display.GfilterDisplayPresenter;
import hu.hw.cloud.client.kip.i18n.KipMessages;
import hu.hw.cloud.shared.AuthService;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.cnst.SubSystem;
import hu.hw.cloud.shared.dto.core.MenuItemDto;

/**
 * @author CR
 *
 */
public class KipAppPresenter extends AppPresenter<KipAppPresenter.MyProxy> {
	private final KipMessages i18n;

	@ProxyStandard
	interface MyProxy extends Proxy<KipAppPresenter> {
	}

	private final GfilterDisplayPresenter gfilterDisplayPresenter;
	private GfilterConfigPresenter gfilterConfigPresenter;

	@Inject
	KipAppPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, KipMessages i18n,
			RestDispatch dispatch, AuthService authenticationService, CurrentUser currentUser,
			MenuPresenter menuPresenter, GfilterDisplayPresenter gfilterDisplayPresenter,
			GfilterConfigPresenterFactory gfilterConfigPresenterFactory, AppData appData,
			AppServiceWorkerManager messagingManager) {
		super(eventBus, view, proxy, placeManager, dispatch, authenticationService, menuPresenter, currentUser,
				SubSystem.KIP, messagingManager);

		this.i18n = i18n;
		this.gfilterDisplayPresenter = gfilterDisplayPresenter;
		gfilterConfigPresenter = gfilterConfigPresenterFactory.createGfilterConfigPresenter();
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_MODAL, gfilterConfigPresenter);
		getMenuPresenter().setMenuItems(createMenuitems());
		getMenuPresenter().setNavBarWidget(gfilterDisplayPresenter);
		gfilterDisplayPresenter.setGfilterConfigPresenter(gfilterConfigPresenter);
	}

	private List<MenuItemDto> createMenuitems() {
		List<MenuItemDto> menuItems = new ArrayList<MenuItemDto>();

		// Assignemnts
		MenuItemDto taskMngrItem = new MenuItemDto();
		taskMngrItem.setIndex(1);
		taskMngrItem.setType(MenuItemType.MENU_ITEM);
		taskMngrItem.setIcon(IconType.ACCESSIBILITY.name());
		taskMngrItem.setText("Task Manager");
		taskMngrItem.setNameToken(KipNameTokens.TASK_MNGR);
		menuItems.add(taskMngrItem);

		// Assignemnts
		MenuItemDto chatRoomItem = new MenuItemDto();
		chatRoomItem.setIndex(1);
		chatRoomItem.setType(MenuItemType.MENU_ITEM);
		chatRoomItem.setIcon(IconType.FORUM.name());
		chatRoomItem.setText(i18n.mainMenuItemChatRoom());
		chatRoomItem.setNameToken(KipNameTokens.CHAT_ROOM);
		menuItems.add(chatRoomItem);

		// Attendants
		MenuItemDto atendantsItem = new MenuItemDto();
		atendantsItem.setIndex(1);
		atendantsItem.setType(MenuItemType.MENU_ITEM);
		atendantsItem.setIcon(IconType.PEOPLE.name());
		atendantsItem.setText(i18n.mainMenuItemAtendants());
		atendantsItem.setNameToken(CoreNameTokens.HOME);
		menuItems.add(atendantsItem);

		// Assignemnts
		MenuItemDto assignmentsItem = new MenuItemDto();
		assignmentsItem.setIndex(1);
		assignmentsItem.setType(MenuItemType.MENU_ITEM);
		assignmentsItem.setIcon(IconType.PLAYLIST_PLAY.name());
		assignmentsItem.setText(i18n.mainMenuItemAssignment());
		assignmentsItem.setNameToken(KipNameTokens.HK_ASSIGNMENTS);
		menuItems.add(assignmentsItem);

		// Perfomance

		MenuItemDto perfSubMenu = new MenuItemDto();
		perfSubMenu.setIndex(2);
		perfSubMenu.setType(MenuItemType.SUB_MENU);
		perfSubMenu.setIcon(IconType.AV_TIMER.name());
		perfSubMenu.setText(i18n.mainMenuGroupHousekeeping());
		perfSubMenu.setItems(new ArrayList<MenuItemDto>());

		MenuItemDto perfMenuItem1 = new MenuItemDto();
		perfMenuItem1.setIndex(1);
		perfMenuItem1.setType(MenuItemType.MENU_ITEM);
		perfMenuItem1.setText("Occupancy");
		perfMenuItem1.setNameToken(KipNameTokens.HK_CHANGE_STATUS);
		perfSubMenu.addItem(perfMenuItem1);

		MenuItemDto perfMenuItem2 = new MenuItemDto();
		perfMenuItem2.setIndex(2);
		perfMenuItem2.setType(MenuItemType.MENU_ITEM);
		perfMenuItem2.setText("Revenue");
		perfMenuItem2.setNameToken(KipNameTokens.HK_CHANGE_STATUS);
		perfSubMenu.addItem(perfMenuItem2);

		menuItems.add(perfSubMenu);

		return menuItems;
	}
}
