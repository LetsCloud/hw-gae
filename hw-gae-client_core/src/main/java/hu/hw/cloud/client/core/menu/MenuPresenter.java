/**
 * 
 */
package hu.hw.cloud.client.core.menu;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;

import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.event.ContentPushEvent.MenuState;
import hu.hw.cloud.client.core.event.SetPageTitleEvent.SetPageTitleHandler;
import hu.hw.cloud.client.core.security.AppData;
import hu.hw.cloud.client.core.security.HasPermissionsGatekeeper;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.core.MenuItemDto;

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

		void setHotelName(String hotelName);

		void setBusinessDate(Date businessDate);

		void setMenuItems(List<MenuItemDto> menuItems);

		void inactivateSingleLinks();

		void closeCollapisbles();

		void setAppCode(String appCode);
	}

	private final AppData appData;

	@Inject
	MenuPresenter(EventBus eventBus, MyView view, AppData appData, HasPermissionsGatekeeper menItemGatekeeper) {
		super(eventBus, view);
		logger.log(Level.INFO, "MenuPresenter()");

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
		logger.log(Level.INFO, "MenuPresenter.onReveal()->in");

		if (appData.getAppCode() != null) {
			logger.log(Level.INFO, "MenuPresenter.onReveal()->appData.getAppCode()=" + appData.getAppCode());
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
		logger.log(Level.INFO, "MenuPresenter.onReveal()->out");
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
			getView().closeCollapisbles();
		} else {
			getView().inactivateSingleLinks();
		}

	}
}