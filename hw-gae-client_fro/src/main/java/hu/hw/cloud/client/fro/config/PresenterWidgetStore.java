/**
 * 
 */
package hu.hw.cloud.client.fro.config;

import com.gwtplatform.mvp.client.PresenterWidget;

import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;

/**
 * @author robi
 *
 */
public class PresenterWidgetStore {

	private String caption;

	private AbstractBrowserPresenter<?, ?> table;

	private PresenterWidget<?> widget;

	public PresenterWidgetStore(String caption, PresenterWidget<?> widget) {
		this.caption = caption;
		this.widget = widget;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public AbstractBrowserPresenter<?, ?> getTable() {
		return table;
	}

	public void setTable(AbstractBrowserPresenter<?, ?> table) {
		this.table = table;
	}

	public PresenterWidget<?> getWidget() {
		return widget;
	}

	public void setWidget(PresenterWidget<?> widget) {
		this.widget = widget;
	}

}
