package hu.hw.cloud.client.core.app;

import java.util.logging.Level;
import java.util.logging.Logger;

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

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter.MyView;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.event.SetPageTitleEvent.SetPageTitleHandler;
import hu.hw.cloud.client.core.menu.MenuPresenter;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.shared.AuthService;

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
	private final AuthService authenticationService;
	private final CurrentUser currentUser;
	private final MenuPresenter menuPresenter;

	protected AppPresenter(EventBus eventBus, MyView view, Proxy_ proxy, PlaceManager placeManager, RestDispatch dispatch,
			AuthService authenticationService, MenuPresenter menuPresenter, CurrentUser currentUser) {
		super(eventBus, view, proxy, RevealType.Root);
		logger.info("ApplicationPresenter()");

		this.placeManager = placeManager;
		this.dispatch = dispatch;
		this.authenticationService = authenticationService;
		this.menuPresenter = menuPresenter;
		this.currentUser = currentUser;
	}

	@Override
	protected void onBind() {
		super.onBind();
		logger.log(Level.INFO, "ApplicationPresenter.onBind()");
		setInSlot(SLOT_MENU, menuPresenter);

		addRegisteredHandler(NavigationEvent.getType(), this);
		addRegisteredHandler(SetPageTitleEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	public void logout() {
		dispatch.execute(authenticationService.logout(), new AsyncCallback<Void>() {

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
}
