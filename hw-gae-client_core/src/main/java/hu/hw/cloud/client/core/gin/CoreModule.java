/**
 * 
 */
package hu.hw.cloud.client.core.gin;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Singleton;

import com.google.inject.Provides;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.client.proxy.DefaultPlaceManager;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.activate.ActivateModule;
import hu.hw.cloud.client.core.login.LoginModule;
import hu.hw.cloud.client.core.menu.MenuModule;
import hu.hw.cloud.client.core.register.RegisterModule;
import hu.hw.cloud.client.core.security.AppData;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.success.SuccessModule;
import hu.hw.cloud.client.core.unauthorized.UnauthorizedModule;
import hu.hw.cloud.client.firebase.Config;
import hu.hw.cloud.client.firebase.Firebase;
import hu.hw.cloud.client.firebase.messaging.MessagingManager;

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
	}

	@Provides
	@Singleton
	MessagingManager provideMessagingManager() {
		Config config = new Config();
		config.setApiKey("AIzaSyDeeu6_zljBv-yq93OIT54ZUEdkZKZCmz8");
		config.setAuthDomain("hw-cloud1.firebaseapp.com");
		config.setDatabaseURL("https://hw-cloud1.firebaseio.com");
		config.setProjectId("hw-cloud1");
		config.setStorageBucket("hw-cloud1.appspot.com");
		config.setMessagingSenderId("489469080035");
		Firebase firebase = Firebase.initializeApp(config);
		logger.log(Level.INFO, "NotificationsPresenter.onBind().firebase.getName()" + firebase.getName());

		MessagingManager messagingManager = new MessagingManager(firebase);
		logger.log(Level.INFO, "NotificationsPresenter.onReveal().getMessagingManager()");
		// messagingManager.useServiceWorker(getServiceWorkerManager().getServiceWorkerRegistration());
		// logger.log(Level.INFO, "NotificationsPresenter.onReveal().useServiceWorker");

		return messagingManager;
	}
}
