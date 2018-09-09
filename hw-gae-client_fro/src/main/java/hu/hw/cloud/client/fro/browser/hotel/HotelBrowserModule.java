/**
 * 
 */
package hu.hw.cloud.client.fro.browser.hotel;

import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.editor.hotel.HotelEditorModule;

/**
 * @author robi
 *
 */
public class HotelBrowserModule extends AbstractPresenterModule {
	@Override
	protected void configure() {

		install(new HotelEditorModule());

		bindPresenterWidget(HotelBrowserPresenter.class, HotelBrowserPresenter.MyView.class, HotelBrowserView.class);

		install(new GinFactoryModuleBuilder().build(HotelBrowserFactory.class));
	}
}
