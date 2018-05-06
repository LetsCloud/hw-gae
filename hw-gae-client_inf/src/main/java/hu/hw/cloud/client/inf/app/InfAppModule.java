package hu.hw.cloud.client.inf.app;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.app.AppView;

public class InfAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(InfAppPresenter.class, InfAppPresenter.MyView.class, AppView.class,
				InfAppPresenter.MyProxy.class);
	}
}
