/**
 * 
 */
package hu.hw.cloud.client.core.ui.dtotable;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.ui.dtotable.appuser.AppUserTablePresenter;
import hu.hw.cloud.client.core.ui.dtotable.appuser.AppUserTableView;
import hu.hw.cloud.client.core.ui.dtotable.usergroup.UserGroupTablePresenter;
import hu.hw.cloud.client.core.ui.dtotable.usergroup.UserGroupTableView;

/**
 * @author robi
 *
 */
public class DtoTableModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(AppUserTablePresenter.class, AppUserTablePresenter.MyView.class, AppUserTableView.class);
		bindPresenterWidget(UserGroupTablePresenter.class, UserGroupTablePresenter.MyView.class,
				UserGroupTableView.class);

		install(new GinFactoryModuleBuilder().build(DtoTablePresenterFactory.class));
	}
}
