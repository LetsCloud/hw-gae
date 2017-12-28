/**
 * 
 */
package hu.hw.cloud.client.kip.chat;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.security.LoggedInGatekeeper;
import hu.hw.cloud.client.kip.KipNameTokens;
import hu.hw.cloud.client.kip.app.KipAppPresenter;
import hu.hw.cloud.client.kip.assignments.AssignmentsPresenter;
import hu.hw.cloud.client.kip.i18n.KipMessages;
import hu.hw.cloud.shared.cnst.MenuItemType;

/**
 * @author CR
 *
 */
public class ChatPresenter extends Presenter<ChatPresenter.MyView, ChatPresenter.MyProxy> implements ChatUiHandlers {
	private static Logger logger = Logger.getLogger(ChatPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ChatUiHandlers> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_MODAL = new SingleSlot<>();
	public static final Slot<PresenterWidget<?>> SLOT_ASSIGNMENTS = new Slot<>();

	@ProxyStandard
	@NameToken(KipNameTokens.HK_ASSIGNMENTS)
	@UseGatekeeper(LoggedInGatekeeper.class)
	interface MyProxy extends ProxyPlace<ChatPresenter> {
	}

	private final KipMessages i18n;

	@Inject
	ChatPresenter(EventBus eventBus, MyView view, MyProxy proxy, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);
		logger.log(Level.INFO, "ChatPresenter()");

		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		String description = i18n.assignmentsDescription();
		description = i18n.assignmentsTasksAssignedTo("Kiss Piroska");
		SetPageTitleEvent.fire(i18n.assignmentsTitle(), description, MenuItemType.MENU_ITEM, this);
	}
}
