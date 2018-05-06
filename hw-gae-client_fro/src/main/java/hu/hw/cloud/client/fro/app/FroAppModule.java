package hu.hw.cloud.client.fro.app;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.app.AppView;

public class FroAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(FroAppPresenter.class, FroAppPresenter.MyView.class, AppView.class,
				FroAppPresenter.MyProxy.class);
	}
}
