/**
 * 
 */
package hu.hw.cloud.client.fro.config.system;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.profile.ProfileEditorModule;

/**
 * @author CR
 *
 */
public class SystemConfigModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new ProfileEditorModule());

		bindPresenter(SystemConfigPresenter.class, SystemConfigPresenter.MyView.class, SystemConfigView.class,
				SystemConfigPresenter.MyProxy.class);
	}
}
