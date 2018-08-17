/**
 * 
 */
package hu.hw.cloud.client.fro.reservation;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.reservation.extra.ExtraResModule;
import hu.hw.cloud.client.fro.reservation.guest.GuestResModule;
import hu.hw.cloud.client.fro.reservation.header.MainResModule;
import hu.hw.cloud.client.fro.reservation.room.RoomResModule;

/**
 * @author robi
 *
 */
public class ReservationModule extends AbstractPresenterModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.inject.client.AbstractGinModule#configure()
	 */
	@Override
	protected void configure() {
		install(new MainResModule());
		install(new RoomResModule());
		install(new ExtraResModule());
		install(new GuestResModule());

		bindPresenter(ReservationPresenter.class, ReservationPresenter.MyView.class, ReservationView.class,
				ReservationPresenter.MyProxy.class);

		install(new GinFactoryModuleBuilder().build(ResWidgetPresenterFactory.class));
	}

}
