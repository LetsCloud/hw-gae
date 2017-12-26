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
import hu.hw.cloud.client.core.users.UsersTable;
import hu.hw.cloud.client.core.users.editor.UserEditPresenter;
import hu.hw.cloud.client.core.users.editor.UserEditPresenterFactory;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.i18n.FroMessages;
import hu.hw.cloud.shared.cnst.MenuItemType;

public class CommonConfigPresenter extends Presenter<CommonConfigPresenter.MyView, CommonConfigPresenter.MyProxy>
		implements CommonConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(CommonConfigPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<CommonConfigUiHandlers> {
		void setContent(Widget w);

		void refreshX();
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.COMMON_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<CommonConfigPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final UsersTable usersTable;
	private final UserEditPresenterFactory userEditPresenterFactory;
	private final FroMessages i18n;

	@Inject
	CommonConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy, UsersTable usersTable,
			UserEditPresenterFactory userEditPresenterFactory, FroMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "CommonConfigPresenter()");

		this.usersTable = usersTable;
		this.userEditPresenterFactory = userEditPresenterFactory;
		this.i18n = i18n;

		getView().setUiHandlers(this);

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.log(Level.INFO, "CommonConfigPresenter.onBind()");
		getView().setContent(usersTable);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "CommonConfigPresenter.onReveal()");
		SetPageTitleEvent.fire(i18n.mainMenuItemCommonConfig(), "", MenuItemType.MENU_ITEM, this);
		// setInSlot(SLOT_CONTENT, new UsersTable(usersDelegate));
		usersTable.refresh();
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createUser() {
		UserEditPresenter userEditPresenter = userEditPresenterFactory.createUserEditPresenter();
		setInSlot(SLOT_EDITOR, userEditPresenter);
		userEditPresenter.create();
	}

}
