/**
 * 
 */
package hu.hw.cloud.client.core.ui.editor;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.core.ui.editor.appuser.AppUserEditPresenter;
import hu.hw.cloud.client.core.ui.editor.appuser.AppUserEditView;
import hu.hw.cloud.client.core.ui.editor.usergroup.UserGroupEditorPresenter;
import hu.hw.cloud.client.core.ui.editor.usergroup.UserGroupEditorView;

/**
 * @author robi
 *
 */
public class DtoEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenterWidget(AppUserEditPresenter.class, AppUserEditPresenter.MyView.class, AppUserEditView.class);

		bindPresenterWidget(UserGroupEditorPresenter.class, UserGroupEditorPresenter.MyView.class,
				UserGroupEditorView.class);

		install(new GinFactoryModuleBuilder().build(DtoEditorFactory.class));
	}
}
