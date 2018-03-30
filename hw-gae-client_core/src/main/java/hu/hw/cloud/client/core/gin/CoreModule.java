/**
 * 
 */
package hu.hw.cloud.client.core.gin;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;

import com.google.inject.Provides;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.activate.ActivateModule;
import hu.hw.cloud.client.core.app.AppServiceWorkerManager;
import hu.hw.cloud.client.core.login.LoginModule;
import hu.hw.cloud.client.core.menu.MenuModule;
import hu.hw.cloud.client.core.register.RegisterModule;
import hu.hw.cloud.client.core.security.AppData;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.success.SuccessModule;
import hu.hw.cloud.client.core.ui.dtotable.DtoTableModule;
import hu.hw.cloud.client.core.ui.editor.DtoEditorModule;
import hu.hw.cloud.client.core.unauthorized.UnauthorizedModule;
import hu.hw.cloud.client.firebase.Config;
import hu.hw.cloud.client.firebase.Firebase;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;
import hu.hw.cloud.shared.FcmService;

/**
 * @author CR
 *
 */
public class CoreModule extends AbstractPresenterModule {
	private static Logger logger = Logger.getLogger(CoreModule.class.getName());

	@Override
	protected void configure() {
		install(new DefaultModule.Builder().placeManager(DefaultPlaceManager.class)
				.tokenFormatter(RouteTokenFormatter.class).build());

		bind(AppData.class).asEagerSingleton();
		bind(CurrentUser.class).asEagerSingleton();

		bindConstant().annotatedWith(DefaultPlace.class).to(CoreNameTokens.HOME);
		bindConstant().annotatedWith(ErrorPlace.class).to(CoreNameTokens.LOGIN);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(CoreNameTokens.LOGIN);

		bind(ResourceLoader.class).asEagerSingleton();

		install(new UnauthorizedModule());
		install(new ServiceModule());
		install(new MenuModule());
		install(new LoginModule());
		install(new RegisterModule());
		install(new SuccessModule());
		install(new ActivateModule());
		install(new DtoTableModule());
		install(new DtoEditorModule());
	}

	@Provides
	@Singleton
	MessagingManager provideMessagingManager() {
		Config config = new Config();
		config.setApiKey("AIzaSyCldBkLB_W7v7p-CUCW_ZkedBVLoWSuKLU");
		config.setAuthDomain("hw-cloud8.firebaseapp.com");
		config.setDatabaseURL("https://hw-cloud3.firebaseio.com");
		config.setProjectId("hw-cloud8");
		config.setStorageBucket("hw-cloud8.appspot.com");
		config.setMessagingSenderId("103271768970");
		Firebase firebase = Firebase.initializeApp(config);
		logger.log(Level.INFO, "NotificationsPresenter.onBind().firebase.getName()" + firebase.getName());

		MessagingManager messagingManager = new MessagingManager(firebase);
		logger.log(Level.INFO, "NotificationsPresenter.onReveal().getMessagingManager()");

		return messagingManager;
	}

	@Provides
	@Singleton
	AppServiceWorkerManager provideAppServiceWorkerManager(EventBus eventBus, MessagingManager fcmManager,
			RestDispatch dispatch, FcmService fcmService) {

		AppServiceWorkerManager serviceWorkerManager = new AppServiceWorkerManager("service-worker.js", eventBus,
				fcmManager, dispatch, fcmService);

		return serviceWorkerManager;
	}
}
