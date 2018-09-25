/**
 * 
 */
package hu.hw.cloud.client.fro.browser.organization;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
import hu.hw.cloud.client.fro.filter.FilterChangeEvent;
import hu.hw.cloud.client.fro.filter.FilterPresenterFactory;
import hu.hw.cloud.client.fro.filter.profile.ProfileFilterPresenter;
import hu.hw.cloud.shared.api.OrganizationResource;
import hu.hw.cloud.shared.dto.profile.OrganizationDtor;

/**
 * @author robi
 *
 */
public class OrganizationBrowserPresenter
		extends AbstractBrowserPresenter<OrganizationDtor, OrganizationBrowserPresenter.MyView>
		implements OrganizationBrowserUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(OrganizationBrowserPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<OrganizationBrowserUiHandlers> {
		void setData(List<OrganizationDtor> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();
	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<OrganizationResource> resourceDelegate;
	private final ProfileFilterPresenter filter;

	@Inject
	OrganizationBrowserPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<OrganizationResource> resourceDelegate, CurrentUser currentUser,
			FilterPresenterFactory filterFactory) {
		super(eventBus, view, placeManager);
		logger.info("OrganizationBrowserPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterFactory.createProfileFilterPresenter();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void loadData() {
	}

	@Override
	protected String getCreatorNameToken() {
		return CoreNameTokens.ORGANIZATION_CREATOR;
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.ORGANIZATION_DISPLAY;
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

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		logger.info("OrganizationBrowserPresenter().onFilterChange()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<OrganizationDtor>>() {
			@Override
			public void onSuccess(List<OrganizationDtor> result) {
				if ((filter.getCode() != null) && (!filter.getCode().isEmpty()))
					result = result.stream().filter(org -> org.getCode().contains(filter.getCode()))
							.collect(Collectors.toList());
				if ((filter.getName() != null) && (!filter.getName().isEmpty()))
					result = result.stream().filter(org -> org.getName().contains(filter.getName()))
							.collect(Collectors.toList());
				if (!filter.getProfileGroupKeys().isEmpty())
					result = result.stream().filter(
							org -> filter.getProfileGroupKeys().contains(org.getProfileGroup().getWebSafeKey()))
							.collect(Collectors.toList());

				getView().setData(result);
			}
		}).list();
	}
}