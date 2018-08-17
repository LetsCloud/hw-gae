/**
 * 
 */
package hu.hw.cloud.client.fro.resnew;

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
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public class ResNewPresenter extends Presenter<ResNewPresenter.MyView, ResNewPresenter.MyProxy>
		implements ResNewUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(ResNewPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ResNewUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(FroNameTokens.CREATE_RESERVATION)
	// @UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ResNewPresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	@Inject
	ResNewPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "ResNewPresenter()");

		getView().setUiHandlers(this);
		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	public void onBind() {
		super.onBind();
		logger.log(Level.INFO, "onBind()");
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.log(Level.INFO, "onReveal()");
		SetPageTitleEvent.fire("Create new reservation", "X", MenuItemType.MENU_ITEM, this);
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		// TODO Auto-generated method stub

	}
}
