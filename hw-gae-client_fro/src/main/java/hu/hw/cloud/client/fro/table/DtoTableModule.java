/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.table.appuser.AppUserTablePresenter;
import hu.hw.cloud.client.fro.table.appuser.AppUserTableView;
import hu.hw.cloud.client.fro.table.hotel.HotelTablePresenter;
import hu.hw.cloud.client.fro.table.hotel.HotelTableView;
import hu.hw.cloud.client.fro.table.usergroup.UserGroupTablePresenter;
import hu.hw.cloud.client.fro.table.usergroup.UserGroupTableView;

/**
 * @author robi
 *
 */
public class DtoTableModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(UserGroupTablePresenter.class, UserGroupTablePresenter.MyView.class,
				UserGroupTableView.class);
		bindPresenterWidget(HotelTablePresenter.class, HotelTablePresenter.MyView.class,
				HotelTableView.class);
		bindPresenterWidget(AppUserTablePresenter.class, AppUserTablePresenter.MyView.class, AppUserTableView.class);

		install(new GinFactoryModuleBuilder().build(DtoTablePresenterFactory.class));
	}
}
