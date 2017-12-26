package hu.hw.cloud.client.fro.app;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.app.AppView;

public class FroAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bind(FroAppPresenter.class).asEagerSingleton();
		bind(FroAppPresenter.MyProxy.class).asEagerSingleton();
		bind(FroAppPresenter.MyView.class).to(AppView.class);
	}
}
