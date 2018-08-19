/**
 * 
 */
package hu.hw.cloud.client.fro.creator.customer;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.fro.editor.profile.customer.CustomerEditorFactory;
import hu.hw.cloud.client.fro.editor.profile.customer.CustomerEditorPresenter;
import hu.hw.cloud.client.fro.i18n.FroMessages;
import hu.hw.cloud.shared.cnst.MenuItemType;

/**
 * @author robi
 *
 */
public class CustomerCreatePresenter extends Presenter<CustomerCreatePresenter.MyView, CustomerCreatePresenter.MyProxy>
		implements CustomerCreateUiHandlers {
	private static Logger logger = Logger.getLogger(CustomerCreatePresenter.class.getName());

	interface MyView extends View, HasUiHandlers<CustomerCreateUiHandlers> {
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.CUSTOMER_CREATOR)
	interface MyProxy extends ProxyPlace<CustomerCreatePresenter> {
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_CONTENT = new SingleSlot<>();

	private final CustomerEditorPresenter editor;
	
	private final FroMessages i18n;

	@Inject
	CustomerCreatePresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			CustomerEditorFactory editorFactory, FroMessages i18n) {
		super(eventBus, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("CustomerCreatePresenter()");

		this.editor = editorFactory.createCustomerEditor();
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_CONTENT, editor);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		SetPageTitleEvent.fire(i18n.customerCreateCaption(), "General Data", MenuItemType.MENU_ITEM, this);
	}
}