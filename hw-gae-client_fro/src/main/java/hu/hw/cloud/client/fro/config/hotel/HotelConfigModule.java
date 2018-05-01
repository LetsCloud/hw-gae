/**
 * 
 */
package hu.hw.cloud.client.fro.config.hotel;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class HotelConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(HotelConfigPresenter.class, HotelConfigPresenter.MyView.class, HotelConfigView.class,
				HotelConfigPresenter.MyProxy.class);
	}
}
