/**
 * 
 */
package hu.hw.cloud.client.core.users.editor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
public class UserEditPresenter extends PresenterWidget<UserEditPresenter.MyView> implements UserEditUiHandlers {
	private static final Logger LOGGER = Logger.getLogger(UserEditPresenter.class.getName());

	private static final String FIRST_PASSWORD = "*";

	public interface MyView extends View, HasUiHandlers<UserEditUiHandlers> {
		void startCreate(AppUserDto userDto);
		void startModify(AppUserDto userDto);
	}

	private final ResourceDelegate<AppUserResource> userResourceDelegate;
	// private final EditManufacturerMessages messages;

	private final CurrentUser currentUser;
//	private AppUserDto userDto;

	private Boolean isEntityNew;

	@Inject
	UserEditPresenter(EventBus eventBus, MyView view, ResourceDelegate<AppUserResource> userResourceDelegate,
			CurrentUser currentUser) {
		super(eventBus, view);

		this.userResourceDelegate = userResourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	public void create() {
		isEntityNew = true;
		LOGGER.log(Level.INFO,
				"UserEditPresenter.createNew()->currentUser.getAppUserDto()=" + currentUser.getAppUserDto().toString());

		AppUserDto userDto = new AppUserDto();
//		userDto.setAccountWebSafeKey(currentUser.getAppUserDto().getAccountDto().getWebSafeKey());
		userDto.setAccountDto(currentUser.getAppUserDto().getAccountDto());
		userDto.setPassword(FIRST_PASSWORD);

		getView().startCreate(userDto);
	}

	public void modify(AppUserDto userDto) {
		isEntityNew = false;
		getView().startModify(userDto);
	}

	@Override
	public void onSave(AppUserDto userDto) {
		LOGGER.log(Level.INFO, "onSave()->userDto=" + userDto);
		if (isEntityNew) {
			createEntity(userDto);
		} else {
			updateEntity(userDto);
		}
	}

	@Override
	public void onCancel() {
		// getView().hide();
	}

	private void createEntity(AppUserDto userDto) {
		userResourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
			@Override
			public void onSuccess(AppUserDto userDto) {
				LOGGER.log(Level.INFO, "onySave()->onSuccess()");
				/*
				 * DisplayMessageEvent.fire(EditManufacturerPresenter.this, new
				 * Message(messages.manufacturerSaved(), MessageStyle.SUCCESS));
				 * ManufacturerAddedEvent.fire(EditManufacturerPresenter.this,
				 * savedManufacturer);
				 */
			}
		}).create(userDto);
	}

	private void updateEntity(AppUserDto userDto) {
		LOGGER.log(Level.INFO,
				"updateEntity()->webSafeKey=" + userDto.getWebSafeKey() + ", username=" + userDto.getUsername());
		userResourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
			@Override
			public void onSuccess(AppUserDto userDto) {
				LOGGER.log(Level.INFO, "onySave()->onSuccess()->webSafeKey=" + userDto.getWebSafeKey() + ", username="
						+ userDto.getUsername());
				/*
				 * DisplayMessageEvent.fire(EditManufacturerPresenter.this, new
				 * Message(messages.manufacturerSaved(), MessageStyle.SUCCESS));
				 * ManufacturerAddedEvent.fire(EditManufacturerPresenter.this,
				 * savedManufacturer);
				 */
			}
		}).update(userDto);
	}
}