/**
 * 
 */
package hu.hw.cloud.client.fro.configcommon;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.table.appuser.AppUserTableView;
import hu.hw.cloud.client.fro.table.usergroup.UserGroupTableView;

/**
 * @author CR
 *
 */
public class CommonConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(CommonConfigPresenter.class, CommonConfigPresenter.MyView.class, CommonConfigView.class,
				CommonConfigPresenter.MyProxy.class);
		
		bind(AppUserTableView.class);
		bind(UserGroupTableView.class);
	}
}
