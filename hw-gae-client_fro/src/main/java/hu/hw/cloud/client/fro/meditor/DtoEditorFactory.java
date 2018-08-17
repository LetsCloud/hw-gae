/**
 * 
 */
package hu.hw.cloud.client.fro.meditor;

import hu.hw.cloud.client.fro.edit.appuser.AppUserEditPresenter;
import hu.hw.cloud.client.fro.meditor.usergroup.UserGroupEditorPresenter;

/**
 * @author robi
 *
 */
public interface DtoEditorFactory {

	AppUserEditPresenter createAppUserEditor();

	UserGroupEditorPresenter createUserGroupEditor();

}
