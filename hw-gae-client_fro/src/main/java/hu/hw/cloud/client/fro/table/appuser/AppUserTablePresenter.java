/**
 * 
 */
package hu.hw.cloud.client.fro.table.appuser;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.client.fro.table.AbstractTablePresenter;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public class AppUserTablePresenter extends AbstractTablePresenter<AppUserTablePresenter.MyView>
		implements AppUserTableUiHandlers {
	private static Logger logger = Logger.getLogger(AppUserTablePresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<AppUserTableUiHandlers> {
		void setData(List<AppUserDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final PlaceManager placeManager;
	private final ResourceDelegate<AppUserResource> resourceDelegate;

	@Inject
	AppUserTablePresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<AppUserResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("AppUserTablePresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void loadData() {
		logger.log(Level.INFO, "loadData()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<AppUserDto>>() {
			@Override
			public void onSuccess(List<AppUserDto> result) {
				logger.info("loadData()->onSuccess()");
				getView().setData(result);
			}
		}).list();
	}

	@Override
	public void addItem() {
		logger.log(Level.INFO, "addItem()");
		PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.USER_EDITOR).build();

		placeManager.revealPlace(placeRequest);
	}

	@Override
	public void editItem(AppUserDto dto) {
		logger.log(Level.INFO, "editItem()->dto=" + dto);
		PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.USER_EDITOR)
				.with("id", String.valueOf(dto.getWebSafeKey())).build();

		placeManager.revealPlace(placeRequest);
	}

	@Override
	public void deleteItem(AppUserDto dto) {
		deleteData(dto.getWebSafeKey());
	}

	private void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	public void inviteItem(AppUserDto dto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
			@Override
			public void onSuccess(AppUserDto userDto) {
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).invite(dto);
	}

	@Override
	public void clearFcmTokens(AppUserDto dto) {
		dto.getFcmTokenDtos().clear();
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
			@Override
			public void onSuccess(AppUserDto userDto) {
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		}).update(dto);
	}
}