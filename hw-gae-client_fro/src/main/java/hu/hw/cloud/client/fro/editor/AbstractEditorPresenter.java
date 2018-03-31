/**
 * 
 */
package hu.hw.cloud.client.fro.editor;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

/**
 * @author robi
 *
 */
public abstract class AbstractEditorPresenter<V extends View, P extends Proxy<?>> extends Presenter<V, P> {

	private String caption;

	public AbstractEditorPresenter(EventBus eventBus, V view, P proxy) {
		super(eventBus, view, proxy);
	}

	public AbstractEditorPresenter(EventBus eventBus, V view, P proxy, GwtEvent.Type<RevealContentHandler<?>> slot) {
		super(eventBus, view, proxy, null, slot);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
