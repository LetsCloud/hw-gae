/**
 * 
 */
package hu.hw.cloud.client.core.gin;

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

/**
 * @author CR
 *
 */
public class CoreModule extends AbstractPresenterModule {

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
}
