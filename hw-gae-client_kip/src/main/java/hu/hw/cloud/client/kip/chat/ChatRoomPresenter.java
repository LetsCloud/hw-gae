/**
 * 
 */
package hu.hw.cloud.client.kip.chat;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.ui.MaterialToast;
import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppServiceWorkerManager;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;
import hu.hw.cloud.client.kip.app.KipAppPresenter;
import hu.hw.cloud.client.kip.chat.creator.ChatCreatorFactory;
import hu.hw.cloud.client.kip.chat.creator.ChatCreatorPresenter;
import hu.hw.cloud.client.kip.chat.list.ChatListFactory;
import hu.hw.cloud.client.kip.chat.list.ChatListPresenter;
import hu.hw.cloud.client.kip.i18n.KipMessages;
import hu.hw.cloud.client.kip.push.PushPresenter;
import hu.hw.cloud.client.kip.push.PushPresenterFactory;
import hu.hw.cloud.shared.FcmService;
import hu.hw.cloud.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public class ChatRoomPresenter extends Presenter<ChatRoomPresenter.MyView, ChatRoomPresenter.MyProxy>
		implements ChatRoomUiHandlers {
	private static Logger logger = Logger.getLogger(ChatRoomPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ChatRoomUiHandlers> {
	}

	public static final SingleSlot<PresenterWidget<?>> LIST_SLOT = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> CREATOR_SLOT = new SingleSlot<>();

	PwaManager manager = PwaManager.getInstance();

	private final PushPresenter pushPresenter;
	private final ChatListPresenter chatListPresenter;
	private final ChatCreatorPresenter chatCreatorPresenter;
	private final RestDispatch dispatcher;
	private final FcmService fcmService;
	private final MessagingManager messagingManager;
	private final KipMessages i18n;

	@ProxyStandard
	@NameToken(CoreNameTokens.HOME)
	interface MyProxy extends ProxyPlace<ChatRoomPresenter> {
	}

	@Inject
	ChatRoomPresenter(EventBus eventBus, MyView view, MyProxy proxy, PushPresenterFactory pushPresenterFactory,
			ChatListFactory chatListFactory, ChatCreatorFactory chatCreatorFactory, RestDispatch dispatcher,
			FcmService fcmService, MessagingManager messagingManager, KipMessages i18n) {
		super(eventBus, view, proxy, KipAppPresenter.SLOT_MAIN);

		this.pushPresenter = pushPresenterFactory.createPushPresenter();
		this.chatListPresenter = chatListFactory.createChatListPresenter();
		this.chatCreatorPresenter = chatCreatorFactory.createChatCreatorPresenter();
		this.fcmService = fcmService;
		this.dispatcher = dispatcher;
		this.messagingManager = messagingManager;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		logger.log(Level.INFO, "NotificationsPresenter.onBind()");

		setInSlot(LIST_SLOT, chatListPresenter);
		setInSlot(CREATOR_SLOT, chatCreatorPresenter);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		String param = request.getParameter("id", null);
		logger.log(Level.INFO, "prepareFromRequest()->param=" + param);
		chatListPresenter.loadData(param);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.chatRoomTitle(), i18n.chatRoomDescription(), MenuItemType.MENU_ITEM, this);
	}

	@Override
	public AppServiceWorkerManager getServiceWorkerManager() {

		if (manager.getServiceWorkerManager() instanceof AppServiceWorkerManager) {
			return (AppServiceWorkerManager) manager.getServiceWorkerManager();
		}
		GWT.log("Push Notification Manager is not yet registered");

		return null;
	}

	@Override
	public void createNotification() {
		pushPresenter.open();
	}

	@Override
	public MessagingManager getMessagingManager() {
		return messagingManager;
	}

	@Override
	public void subToServer(String iidToken) {
		dispatcher.execute(fcmService.subscribe(iidToken, getUserAgent()), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void response) {
				MaterialToast.fireToast("Sussecfull subscription!");
			}

			@Override
			public void onFailure(Throwable throwable) {
				MaterialToast.fireToast(throwable.getMessage());
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public static native String getUserAgent() /*-{
		return $wnd.navigator.userAgent.toLowerCase();
	}-*/;

	@Override
	public void createChat(MaterialWidget source) {
		chatCreatorPresenter.open(source);
	}

}
