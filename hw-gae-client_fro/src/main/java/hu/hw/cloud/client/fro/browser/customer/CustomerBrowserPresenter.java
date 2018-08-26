/**
 * 
 */
package hu.hw.cloud.client.fro.browser.customer;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.shared.api.CustomerResource;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public class CustomerBrowserPresenter extends AbstractBrowserPresenter<OrganizationDto, CustomerBrowserPresenter.MyView>
		implements CustomerBrowserUiHandlers {
	private static Logger logger = Logger.getLogger(CustomerBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<CustomerBrowserUiHandlers> {
		void setData(List<OrganizationDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<CustomerResource> resourceDelegate;

	@Inject
	CustomerBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<CustomerResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view, placeManager);
		logger.info("CustomerBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<OrganizationDto>>() {
			@Override
			public void onSuccess(List<OrganizationDto> result) {
				getView().setData(result);
			}
		}).list();
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.CUSTOMER_CREATOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.CUSTOMER_EDITOR;
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}
}