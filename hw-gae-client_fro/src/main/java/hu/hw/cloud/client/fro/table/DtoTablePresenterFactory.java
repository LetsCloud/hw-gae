/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import hu.hw.cloud.client.fro.table.appuser.AppUserTablePresenter;
import hu.hw.cloud.client.fro.table.usergroup.UserGroupTablePresenter;

/**
 * @author robi
 *
 */
public interface DtoTablePresenterFactory {

	AppUserTablePresenter createAppUserTablePresenter();

	UserGroupTablePresenter createUserGroupTablePresenter();

}
