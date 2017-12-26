package hu.hw.cloud.client.kip.app;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.app.AppView;

public class KipAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bind(KipAppPresenter.class).asEagerSingleton();
		bind(KipAppPresenter.MyProxy.class).asEagerSingleton();
		bind(KipAppPresenter.MyView.class).to(AppView.class);
	}
}
