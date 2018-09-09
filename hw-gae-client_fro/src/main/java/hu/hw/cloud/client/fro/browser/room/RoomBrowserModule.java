/**
 * 
 */
package hu.hw.cloud.client.fro.browser.room;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.room.RoomEditorModule;

/**
 * @author robi
 *
 */
public class RoomBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new RoomEditorModule());

		bindPresenterWidget(RoomBrowserPresenter.class, RoomBrowserPresenter.MyView.class, RoomBrowserView.class);

		install(new GinFactoryModuleBuilder().build(RoomBrowserFactory.class));
	}
}
