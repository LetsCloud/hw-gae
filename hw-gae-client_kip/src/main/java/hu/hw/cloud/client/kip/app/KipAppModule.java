package hu.hw.cloud.client.kip.app;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.app.AppView;

public class KipAppModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(KipAppPresenter.class, KipAppPresenter.MyView.class, AppView.class,
				KipAppPresenter.MyProxy.class);
	}
}
