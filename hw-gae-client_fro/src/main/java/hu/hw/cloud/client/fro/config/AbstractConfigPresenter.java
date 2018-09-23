/**
 * 
 */
package hu.hw.cloud.client.fro.config;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.event.ContentPushEvent;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public abstract class AbstractConfigPresenter<V extends AbstractConfigPresenter.MyView, P extends Proxy<?>>
		extends Presenter<V, P> implements AbstractConfigUiHandlers, ContentPushEvent.ContentPushHandler {
	private static Logger logger = Logger.getLogger(AbstractConfigPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<AbstractConfigUiHandlers> {

		void buildMenu(List<String> captions);

		void setMobileView(Boolean show);

		void setDesktopMenu(Integer index);

		void setMobileButtonText(String text);
	}

	private String caption;

	private String description;

	private String placeToken;
	
	protected Boolean firstReveal = true;
	
	private List<String> captions = new ArrayList<String>();
	
	private List<String> placeParams = new ArrayList<String>();

	private List<PresenterWidget<?>> browsers = new ArrayList<PresenterWidget<?>>();

	private final PlaceManager placeManager;
	
	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	private final static String PLACE_PARAM = "placeParam";

	public AbstractConfigPresenter(EventBus eventBus, PlaceManager placeManager, V view, P proxy, GwtEvent.Type<RevealContentHandler<?>> slot) {
		super(eventBus, view, proxy, null, slot);
		logger.info("AbstractConfigPresenter()");
		this.placeManager = placeManager;
		addRegisteredHandler(ContentPushEvent.TYPE, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().buildMenu(captions);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		logger.info("AbstractConfigPresenter().prepareFromRequest()");
		Integer index = placeParams.indexOf(request.getParameter(PLACE_PARAM, null));
		if (index == -1) index = 0;
		getView().setDesktopMenu(index);
		getView().setMobileButtonText(captions.get(index));
		setInSlot(SLOT_CONTENT, beforeShowContent(browsers.get(index)));
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(caption, description, MenuItemType.MENU_ITEM, this);
		if (firstReveal) {
//			showContent(0);
			firstReveal = false;
		}
	}

	@Override
	public void onContentPush(ContentPushEvent event) {
		/*
		 * if ((event.getMenuState().equals(MenuState.OPEN)) && (Window.getClientWidth()
		 * <= 1150)) { getView().setMobileView(true); return; } if
		 * ((event.getMenuState().equals(MenuState.CLOSE)) && (Window.getClientWidth()
		 * <= 995)) { getView().setMobileView(true); return; }
		 * getView().setMobileView(false);
		 */
	}

	protected PresenterWidget<?> beforeShowContent(PresenterWidget<?> widget) {
		return widget;
	}

	@Override
	public void showContent(Integer index) {
		logger.info("AbstractConfigPresenter().showContent(" + index + ")");
		PlaceRequest placeRequest = new PlaceRequest.Builder()
	            .nameToken(placeToken)
	            .with(PLACE_PARAM, placeParams.get(index))
	            .build();

	    placeManager.revealPlace(placeRequest);
	}

	public void addContent(String caption, PresenterWidget<?> widget, String placeParam) {
		captions.add(caption);
		browsers.add(widget);
		placeParams.add(placeParam);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlaceToken() {
		return placeToken;
	}

	public void setPlaceToken(String placeToken) {
		this.placeToken = placeToken;
	}

}
