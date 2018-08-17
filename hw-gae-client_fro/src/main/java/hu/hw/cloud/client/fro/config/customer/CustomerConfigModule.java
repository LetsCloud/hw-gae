package hu.hw.cloud.client.fro.config.customer;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CustomerConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(CustomerConfigPresenter.class, CustomerConfigPresenter.MyView.class, CustomerConfigView.class,
				CustomerConfigPresenter.MyProxy.class);
	}
}
