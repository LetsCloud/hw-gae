/**
 * 
 */
package hu.hw.cloud.client.fro.browser.roomtype;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.roomtype.RoomTypeEditorModule;

/**
 * @author robi
 *
 */
public class RoomTypeBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new RoomTypeEditorModule());
		
		bindPresenterWidget(RoomTypeBrowserPresenter.class, RoomTypeBrowserPresenter.MyView.class,
				RoomTypeBrowserView.class);

		install(new GinFactoryModuleBuilder().build(RoomTypeBrowserFactory.class));
	}
}
