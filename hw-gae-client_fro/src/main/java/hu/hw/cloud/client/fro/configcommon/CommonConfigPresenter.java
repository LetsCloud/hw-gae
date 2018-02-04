package hu.hw.cloud.client.fro.configcommon;

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
import hu.hw.cloud.client.core.ui.dtotable.appuser.AppUserTableView;
import hu.hw.cloud.client.core.ui.dtotable.usergroup.UserGroupTableView;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.i18n.FroMessages;
import hu.hw.cloud.shared.cnst.MenuItemType;

public class CommonConfigPresenter extends Presenter<CommonConfigPresenter.MyView, CommonConfigPresenter.MyProxy>
		implements CommonConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(CommonConfigPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<CommonConfigUiHandlers> {
		void setTable(Widget w);

		void refreshX();
	}

	private enum ViewPage {
		APP_USER, USER_GROUP
	}

	private ViewPage activePage;

	@ProxyCodeSplit
	@NameToken(FroNameTokens.COMMON_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<CommonConfigPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_TABLE = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final AppUserTableView appUserTable;
	private final UserGroupTableView userGroupTable;
	private final FroMessages i18n;

	@Inject
	CommonConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy, AppUserTableView usersTable,
			UserGroupTableView userGroupsTable, FroMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "CommonConfigPresenter()");

		this.appUserTable = usersTable;
		this.userGroupTable = userGroupsTable;
		this.i18n = i18n;

		getView().setUiHandlers(this);

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.log(Level.INFO, "CommonConfigPresenter.onBind()");
		activePage = ViewPage.APP_USER;
//		getView().setTable(appUserTable);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "CommonConfigPresenter.onReveal()");
		SetPageTitleEvent.fire(i18n.mainMenuItemCommonConfig(), "", MenuItemType.MENU_ITEM, this);
		// setInSlot(SLOT_CONTENT, new UsersTable(usersDelegate));
		refreshActivaPage();
	}

	private void refreshActivaPage() {
		switch (activePage) {
		case APP_USER:
			break;
		case USER_GROUP:
			break;
		default:
			break;
		}
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createItem() {
		switch (activePage) {
		case APP_USER:
			createUser();
			break;
		case USER_GROUP:
			createUserGroup();
			break;
		default:
			createUser();
			break;
		}
	}

	private void createUser() {
//		AppUserEditPresenter editor = appUserEditorFactory.createUserEditPresenter();
//		setInSlot(SLOT_EDITOR, editor);
//		editor.create();
	}

	private void createUserGroup() {
//		UserGroupEditorPresenter editor = userGroupEditorFactory.createUserGroupEditorPresenter();
//		setInSlot(SLOT_EDITOR, editor);
//		editor.create();
	}

	@Override
	public void showAppUserTable() {
		activePage = ViewPage.APP_USER;
//		getView().setTable(appUserTable);
	}

	@Override
	public void showUserGroupTable() {
		activePage = ViewPage.USER_GROUP;
//		getView().setTable(userGroupTable);
	}

}
