/**
 * 
 */
package hu.hw.cloud.client.fro.editor;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class DataPresenterWidget<V extends View> extends PresenterWidget<V> {

	public DataPresenterWidget(EventBus eventBus, V view) {
		super(eventBus, view);
	}

	public abstract void show(String dataId);
}
