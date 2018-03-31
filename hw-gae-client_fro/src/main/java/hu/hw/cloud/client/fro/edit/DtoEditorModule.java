/**
 * 
 */
package hu.hw.cloud.client.fro.edit;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.edit.appuser.AppUserEditPresenter;
import hu.hw.cloud.client.fro.edit.appuser.AppUserEditView;
import hu.hw.cloud.client.fro.edit.usergroup.UserGroupEditorPresenter;
import hu.hw.cloud.client.fro.edit.usergroup.UserGroupEditorView;

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
