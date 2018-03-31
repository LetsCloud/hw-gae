/**
 * 
 */
package hu.hw.cloud.client.fro.table.appuser;

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
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.client.fro.table.AbstractTablePresenter;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author robi
 *
 */
public class AppUserTablePresenter extends AbstractTablePresenter<AppUserDto, AppUserTablePresenter.MyView>
		implements AppUserTableUiHandlers {
	private static Logger logger = Logger.getLogger(AppUserTablePresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<AppUserTableUiHandlers> {
		void setData(List<AppUserDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_EDITOR = new SingleSlot<>();

	private final ResourceDelegate<AppUserResource> resourceDelegate;

	@Inject
	AppUserTablePresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<AppUserResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view, placeManager);
		logger.info("AppUserTablePresenter()");

		this.resourceDelegate = resourceDelegate;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<AppUserDto>>() {
			@Override
			public void onSuccess(List<AppUserDto> result) {
				getView().setData(result);
			}
		}).list();
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.USER_EDITOR;
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