/**
 * 
 */
package hu.hw.cloud.client.core.ui.editor.appuser;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import hu.hw.cloud.client.core.event.RefreshTableEvent;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.UserGroupResource;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author CR
 *
 */
public class AppUserEditPresenter extends PresenterWidget<AppUserEditPresenter.MyView>
		implements AppUserEditUiHandlers {

	private static final String FIRST_PASSWORD = "*";

	public interface MyView extends View, HasUiHandlers<AppUserEditUiHandlers> {
		void open(Boolean isNew, AppUserDto dto);

		void setUserGroupData(List<UserGroupDto> data);

		void displayError(EntityPropertyCode code, String message);

		void close();
	}

	private final ResourceDelegate<AppUserResource> resourceDelegate;
	private final ResourceDelegate<UserGroupResource> userGroupresourceDelegate;

	private final CurrentUser currentUser;

	private Boolean isNew;

	@Inject
	AppUserEditPresenter(EventBus eventBus, MyView view, ResourceDelegate<AppUserResource> resourceDelegate,
			ResourceDelegate<UserGroupResource> userGroupresourceDelegate, CurrentUser currentUser) {
		super(eventBus, view);

		this.resourceDelegate = resourceDelegate;
		this.userGroupresourceDelegate = userGroupresourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		loadUserGroupData();
	}

	private void loadUserGroupData() {
		userGroupresourceDelegate.withCallback(new AbstractAsyncCallback<List<UserGroupDto>>() {
			@Override
			public void onSuccess(List<UserGroupDto> result) {
				getView().setUserGroupData(result);
			}
		}).list();
	}

	@Override
	public void create() {
		isNew = true;

		AppUserDto userDto = new AppUserDto();
		// userDto.setAccountWebSafeKey(currentUser.getAppUserDto().getAccountDto().getWebSafeKey());
		userDto.setAccountDto(currentUser.getAppUserDto().getAccountDto());
		userDto.setPassword(FIRST_PASSWORD);

		getView().open(isNew, userDto);
	}

	@Override
	public void edit(AppUserDto dto) {
		isNew = false;
		getView().open(isNew, dto);
	}

	@Override
	public void save(AppUserDto dto) {
		if (isNew) {
			createEntity(dto);
		} else {
			updateEntity(dto);
		}
	}

	private void createEntity(AppUserDto userDto) {
		resourceDelegate.withCallback(new AsyncCallback<AppUserDto>() {
			@Override
			public void onSuccess(AppUserDto userDto) {
				RefreshTableEvent.fire(AppUserEditPresenter.this);
				getView().close();
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).create(userDto);
	}

	private void updateEntity(AppUserDto userDto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
			@Override
			public void onSuccess(AppUserDto userDto) {
				RefreshTableEvent.fire(AppUserEditPresenter.this);
				getView().close();
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).update(userDto);
	}

}