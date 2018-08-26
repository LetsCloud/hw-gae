package hu.hw.cloud.client.core.app;

import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.PermanentSlot;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialToast;
import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter.MyView;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.event.SetPageTitleEvent.SetPageTitleHandler;
import hu.hw.cloud.client.core.menu.MenuPresenter;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.shared.AuthService;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

public abstract class AppPresenter<Proxy_ extends Proxy<?>> extends Presenter<MyView, Proxy_>
		implements NavigationHandler, SetPageTitleHandler {
	private static Logger logger = Logger.getLogger(AppPresenter.class.getName());

	public interface MyView extends View {
		void setPageTitle(String title, String description);

		void displayUserName(String userName);
	}

	public static final PermanentSlot<MenuPresenter> SLOT_MENU = new PermanentSlot<>();
	public static final NestedSlot SLOT_MAIN = new NestedSlot();
	public static final SingleSlot<PresenterWidget<?>> SLOT_MODAL = new SingleSlot<>();

	private final PlaceManager placeManager;
	private final RestDispatch dispatch;
	private final AuthService authService;
	private final CurrentUser currentUser;
	private final MenuPresenter menuPresenter;
	private final AppServiceWorkerManager swManager;
	private final String appCode;

	protected AppPresenter(EventBus eventBus, MyView view, Proxy_ proxy, PlaceManager placeManager,
			RestDispatch dispatch, AuthService authService, MenuPresenter menuPresenter, CurrentUser currentUser,
			String appCode, AppServiceWorkerManager swManager) {
		super(eventBus, view, proxy, RevealType.Root);
		logger.info("AppPresenter()");

		this.placeManager = placeManager;
		this.dispatch = dispatch;
		this.authService = authService;
		this.menuPresenter = menuPresenter;
		this.currentUser = currentUser;
		this.swManager = swManager;
		this.appCode = appCode;
	}

	@Override
	protected void onBind() {
		super.onBind();
		String manifest = appCode + "_manifest.json";
		setInSlot(SLOT_MENU, menuPresenter);

		addRegisteredHandler(NavigationEvent.getType(), this);
		addRegisteredHandler(SetPageTitleEvent.TYPE, this);

		Timer t = new Timer() {

			@Override
			public void run() {
				PwaManager.getInstance().setServiceWorker(swManager).setWebManifest(manifest).load();
				configOnFcmMessage();
				swManager.onFcmTokenRefresh(token -> swManager.fcmSubscribe(token));
			}
		};
		t.schedule(500);
	}

	private void configOnFcmMessage() {
		swManager.onFcmMessage(dataMessage -> {
			String action = dataMessage.getData().getAction();
			String href = action.substring(action.indexOf("#"));
			MaterialLink link = new MaterialLink("MEGNYITOM");
			link.setHref(href);
			new MaterialToast(link).toast(
					"ÜZENET:" + dataMessage.getData().getTitle() + "->" + dataMessage.getData().getBody(), 10000);
		});
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		checkCurrentUser();
	}

	private void checkCurrentUser() {
		dispatch.execute(authService.getCurrentUser(), new AsyncCallback<AppUserDto>() {

			@Override
			public void onSuccess(AppUserDto result) {
				if (result == null) {
					currentUser.setLoggedIn(false);
					return;
				}
				currentUser.setAppUserDto(result);
				currentUser.getAppUserDto().getAvailableHotels()
						.sort((HotelDto h1, HotelDto h2) -> h1.getName().compareTo(h2.getName()));
				currentUser.setLoggedIn(true);

				menuPresenter.referesh();

				swManager.requestFcbPermission(() -> swManager.getFcbToken(token -> {
					swManager.fcmSubscribe(token);
				}));
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

	public void logout() {
		dispatch.execute(authService.logout(), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.getLogin()).build();
				currentUser.setLoggedIn(false);
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	public void onNavigation(NavigationEvent navigationEvent) {
		Window.scrollTo(0, 0);
	}

	@Override
	public void onSetPageTitle(SetPageTitleEvent event) {
		getView().setPageTitle(event.getTitle(), event.getDescription());
		menuPresenter.adjustMenuItems(event.getMenuItemType());
	}

	public MenuPresenter getMenuPresenter() {
		return menuPresenter;
	}

	public AppServiceWorkerManager getServiceWorkerManager() {
		return swManager;
	}
}
