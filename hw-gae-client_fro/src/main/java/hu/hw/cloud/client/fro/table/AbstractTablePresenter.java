/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import hu.hw.cloud.client.core.event.RefreshTableEvent;

/**
 * @author robi
 *
 */
public abstract class AbstractTablePresenter<T extends View> extends PresenterWidget<T>
		implements RefreshTableEvent.RefreshTableHandler {

	private String caption;

	public AbstractTablePresenter(EventBus eventBus, T view) {
		super(eventBus, view);

		addRegisteredHandler(RefreshTableEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		loadData();
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public abstract void addItem();

	protected abstract void loadData();

	@Override
	public void onRefresh(RefreshTableEvent event) {
		loadData();
	}
}
