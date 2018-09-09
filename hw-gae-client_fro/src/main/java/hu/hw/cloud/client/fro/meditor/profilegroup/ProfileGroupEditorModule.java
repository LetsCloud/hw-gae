/**
 * 
 */
package hu.hw.cloud.client.fro.meditor.profilegroup;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.profile.ProfileEditorModule;

/**
 * @author robi
 *
 */
public class ProfileGroupEditorModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new ProfileEditorModule());

		bindPresenterWidget(ProfileGroupEditorPresenter.class, ProfileGroupEditorPresenter.MyView.class,
				ProfileGroupEditorView.class);

		install(new GinFactoryModuleBuilder().build(ProfileGroupEditorFactory.class));
	}
}
