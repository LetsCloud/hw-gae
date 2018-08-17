/**
 * 
 */
package hu.hw.cloud.client.fro.config;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;

import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.event.ContentPushEvent.MenuState;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public abstract class AbstractConfigPresenter<V extends AbstractConfigPresenter.MyView, P extends Proxy<?>>
		extends Presenter<V, P> implements ConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(AbstractConfigPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<ConfigUiHandlers> {

		void buildMenu();
		void setMobileView(Boolean show);
		void setDesktopMenu(Integer index);
	}

	private Integer activeTable;

	private String caption;
	private Map<Integer, PresenterWidgetStore> tableMap = new HashMap<Integer, PresenterWidgetStore>();

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	public AbstractConfigPresenter(EventBus eventBus, V view, P proxy, GwtEvent.Type<RevealContentHandler<?>> slot) {
		super(eventBus, view, proxy, null, slot);
		logger.info("AbstractConfigPresenter()");

		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().buildMenu();
		showTable(1);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(caption, "", MenuItemType.MENU_ITEM, this);
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		/*
		if ((event.getMenuState().equals(MenuState.OPEN)) && (Window.getClientWidth() <= 1150)) {
			getView().setMobileView(true);
			return;
		}
		if ((event.getMenuState().equals(MenuState.CLOSE)) && (Window.getClientWidth() <= 995)) {
			getView().setMobileView(true);
			return;
		}
		getView().setMobileView(false);
		*/
	}

	@Override
	public Map<Integer, PresenterWidgetStore> getTableMap() {
		return tableMap;
	}

	@Override
	public void showTable(Integer index) {
		activeTable = index;
		getView().setDesktopMenu(index);
		setInSlot(SLOT_CONTENT, tableMap.get(index).getTable());
	}

	public void addTable(Integer index, PresenterWidgetStore store) {
		tableMap.put(index, store);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

}
