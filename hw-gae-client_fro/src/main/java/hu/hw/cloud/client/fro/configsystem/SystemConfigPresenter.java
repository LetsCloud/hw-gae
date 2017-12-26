package hu.hw.cloud.client.fro.configsystem;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.core.users.UserEditEvent;
import hu.hw.cloud.client.core.users.UsersTable;
import hu.hw.cloud.client.core.users.editor.UserEditPresenter;
import hu.hw.cloud.client.core.users.editor.UserEditPresenterFactory;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.i18n.FroMessages;
import hu.hw.cloud.shared.cnst.MenuItemType;

public class SystemConfigPresenter extends Presenter<SystemConfigPresenter.MyView, SystemConfigPresenter.MyProxy>
		implements SystemConfigUiHandlers, ContentPushEvent.ContentPushHandler, UserEditEvent.UserEditEventHandler {
	private static Logger logger = Logger.getLogger(SystemConfigPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<SystemConfigUiHandlers> {

		void setDesktopMenu(int index);

		void setContent(Widget w);

		void refreshX();
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.SYSTEM_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<SystemConfigPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final UsersTable usersTable;
	private final UserEditPresenterFactory userEditPresenterFactory;
	private final FroMessages i18n;

	@Inject
	SystemConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy, UsersTable usersTable,
			UserEditPresenterFactory userEditPresenterFactory, FroMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "SystemConfigPresenter()");

		this.usersTable = usersTable;
		this.userEditPresenterFactory = userEditPresenterFactory;
		this.i18n = i18n;

		getView().setUiHandlers(this);
		usersTable.setPresenter(this);

		addRegisteredHandler(ContentPushEvent.TYPE, this);
		addRegisteredHandler(UserEditEvent.TYPE, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.log(Level.INFO, "SystemConfigPresenter.onBind()");

	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "SystemConfigPresenter.onReveal()");
		SetPageTitleEvent.fire(i18n.mainMenuItemCommonConfig(), "", MenuItemType.MENU_ITEM, this);
		// setInSlot(SLOT_CONTENT, new UsersTable(usersDelegate));
		showUsers();
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		logger.log(Level.INFO, "SystemConfigPresenter.onContentPush()");
	}

	@Override
	public void createUser() {
		UserEditPresenter userEditPresenter = userEditPresenterFactory.createUserEditPresenter();
		setInSlot(SLOT_EDITOR, userEditPresenter);
		userEditPresenter.create();
	}

	@Override
	public void showSystem() {
		getView().setDesktopMenu(1);
	}

	@Override
	public void showUsers() {
		logger.log(Level.INFO, "SystemConfigPresenter.setUsers()");
		getView().setDesktopMenu(2);
		getView().setContent(usersTable);
		usersTable.refresh();
	}

	@Override
	public void showRoles() {
		getView().setDesktopMenu(3);
	}

	@Override
	public void onEditUser(UserEditEvent event) {
		logger.log(Level.INFO, "SystemConfigPresenter.onEditUser()");
		createUser();
	}

}
