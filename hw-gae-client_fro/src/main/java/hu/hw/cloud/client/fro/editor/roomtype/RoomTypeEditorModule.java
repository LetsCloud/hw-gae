/**
 * 
 */
package hu.hw.cloud.client.fro.editor.roomtype;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class RoomTypeEditorModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bindPresenter(RoomTypeEditorPresenter.class, RoomTypeEditorPresenter.MyView.class, RoomTypeEditorView.class,
				RoomTypeEditorPresenter.MyProxy.class);
	}
}
