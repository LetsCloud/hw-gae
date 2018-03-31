/**
 * 
 */
package hu.hw.cloud.client.core.ui.editor;

import hu.hw.cloud.client.core.ui.editor.appuser.AppUserEditPresenter;
import hu.hw.cloud.client.core.ui.editor.usergroup.UserGroupEditorPresenter;

/**
 * @author robi
 *
 */
public interface DtoEditorFactory {

	AppUserEditPresenter createAppUserEditor();

	UserGroupEditorPresenter createUserGroupEditor();

}
