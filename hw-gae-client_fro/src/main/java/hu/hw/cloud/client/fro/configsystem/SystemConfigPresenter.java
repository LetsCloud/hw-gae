package hu.hw.cloud.client.fro.configsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.i18n.FroMessages;
import hu.hw.cloud.client.fro.table.DtoTablePresenterFactory;
import hu.hw.cloud.shared.cnst.MenuItemType;

public class SystemConfigPresenter extends Presenter<SystemConfigPresenter.MyView, SystemConfigPresenter.MyProxy>
		implements SystemConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(SystemConfigPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<SystemConfigUiHandlers> {

		void buildMenu();
		
		void setDesktopMenu(Integer index);
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.SYSTEM_CONFIG)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<SystemConfigPresenter> {
	}

	private Integer ativeTable;
	private Map<Integer, TableStore> tableMap = new HashMap<Integer, TableStore>();

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	private final FroMessages i18n;

	@Inject
	SystemConfigPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			DtoTablePresenterFactory dtoTablePresenterFactory, FroMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("SystemConfigPresenter()");

		tableMap.put(1,
				new TableStore(i18n.systemConfigUserGroup(), dtoTablePresenterFactory.createUserGroupTablePresenter()));
		tableMap.put(2,
				new TableStore(i18n.systemConfigAppUser(), dtoTablePresenterFactory.createAppUserTablePresenter()));

		this.i18n = i18n;

		getView().setUiHandlers(this);

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().buildMenu();
		showTable(1);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.mainMenuItemCommonConfig(), "", MenuItemType.MENU_ITEM, this);
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		logger.log(Level.INFO, "SystemConfigPresenter.onContentPush()");
	}

	@Override
	public Map<Integer, TableStore> getTableMap() {
		return tableMap;
	}

	@Override
	public void showTable(Integer index) {
		ativeTable = index;
		getView().setDesktopMenu(index);
		setInSlot(SLOT_CONTENT, tableMap.get(index).getTable());
	}

	@Override
	public void addItem() {
		tableMap.get(ativeTable).getTable().create();
	}

}
