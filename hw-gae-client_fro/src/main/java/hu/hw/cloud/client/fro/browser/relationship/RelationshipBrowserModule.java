/**
 * 
 */
package hu.hw.cloud.client.fro.browser.relationship;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.meditor.profilegroup.ProfileGroupEditorModule;
import hu.hw.cloud.client.fro.meditor.relationship.RelationshipEditorModule;

/**
 * @author robi
 *
 */
public class RelationshipBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new RelationshipEditorModule());

		bindPresenterWidget(RelationshipBrowserPresenter.class, RelationshipBrowserPresenter.MyView.class,
				RelationshipBrowserView.class);

		install(new GinFactoryModuleBuilder().build(RelationshipBrowserFactory.class));
	}
}
