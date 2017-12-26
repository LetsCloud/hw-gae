package hu.hw.cloud.client.inf.app;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.app.AppView;

public class InfAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bind(InfAppPresenter.class).asEagerSingleton();
		bind(InfAppPresenter.MyProxy.class).asEagerSingleton();
		bind(InfAppPresenter.MyView.class).to(AppView.class);
	}
}
