/**
 * 
 */
package hu.hw.cloud.client.fro.edit;

import hu.hw.cloud.client.fro.edit.appuser.AppUserEditPresenter;
import hu.hw.cloud.client.fro.edit.usergroup.UserGroupEditorPresenter;

/**
 * @author robi
 *
 */
public interface DtoEditorFactory {

	AppUserEditPresenter createAppUserEditor();

	UserGroupEditorPresenter createUserGroupEditor();

}
