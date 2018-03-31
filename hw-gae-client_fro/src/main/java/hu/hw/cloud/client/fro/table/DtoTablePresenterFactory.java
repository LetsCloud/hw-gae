/**
 * 
 */
package hu.hw.cloud.client.core.ui.dtotable;

import hu.hw.cloud.client.core.ui.dtotable.appuser.AppUserTablePresenter;
import hu.hw.cloud.client.core.ui.dtotable.usergroup.UserGroupTablePresenter;

/**
 * @author robi
 *
 */
public interface DtoTablePresenterFactory {

	AppUserTablePresenter createAppUserTablePresenter();

	UserGroupTablePresenter createUserGroupTablePresenter();

}
