/**
 * 
 */
package hu.hw.cloud.client.fro.editor.appuser;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenter;
import hu.hw.cloud.client.fro.editor.EditorView;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.UserGroupResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class AppUserEditorPresenter
		extends AbstractEditorPresenter<AppUserDto, AppUserEditorPresenter.MyView, AppUserEditorPresenter.MyProxy>
		implements AppUserEditorUiHandlers {
	private static Logger logger = Logger.getLogger(AppUserEditorPresenter.class.getName());

	private static final String FIRST_PASSWORD = "*";

	public interface MyView extends EditorView<AppUserDto>, HasUiHandlers<AppUserEditorUiHandlers> {

		void setUserGroupData(List<UserGroupDto> data);

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.USER_EDITOR)
	interface MyProxy extends ProxyPlace<AppUserEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<AppUserResource> resourceDelegate;
	private final ResourceDelegate<UserGroupResource> userGroupresourceDelegate;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	AppUserEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<AppUserResource> resourceDelegate,
			ResourceDelegate<UserGroupResource> userGroupresourceDelegate, CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("AppUserEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.userGroupresourceDelegate = userGroupresourceDelegate;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		userGroupresourceDelegate.withCallback(new AbstractAsyncCallback<List<UserGroupDto>>() {
			@Override
			public void onSuccess(List<UserGroupDto> result) {
				getView().setUserGroupData(result);

				if (isNew()) {
					SetPageTitleEvent.fire(i18n.userEditorCreateTitle(), "", MenuItemType.MENU_ITEM,
							AppUserEditorPresenter.this);
					create();
				} else {
					SetPageTitleEvent.fire(i18n.userEditorModifyTitle(), "", MenuItemType.MENU_ITEM,
							AppUserEditorPresenter.this);
					edit(dtoWebSafeKey);
				}
			}
		}).list();
	}

	@Override
	protected AppUserDto createDto() {
		logger.info("AppUserEditorPresenter().createDto()");
		AppUserDto dto = new AppUserDto();
		dto.setAccountDto(currentUser.getAppUserDto().getAccountDto());
		dto.setPassword(FIRST_PASSWORD);
		return dto;
	}

	private void edit(String webSafeKey) {
		logger.info("AppUserEditorPresenter().edit()->webSafeKey=" + webSafeKey);
		resourceDelegate.withCallback(new AsyncCallback<AppUserDto>() {
			@Override
			public void onSuccess(AppUserDto dto) {
				logger.info("AppUserEditorPresenter().edit().onSuccess()->dto=" + dto);
				getView().edit(false, dto);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	protected void createEntity(AppUserDto userDto) {
		resourceDelegate.withCallback(new AsyncCallback<AppUserDto>() {
			@Override
			public void onSuccess(AppUserDto userDto) {
				PlaceRequest placeRequest = new Builder().nameToken(FroNameTokens.SYSTEM_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).create(userDto);
	}

	@Override
	protected void updateEntity(AppUserDto userDto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
			@Override
			public void onSuccess(AppUserDto userDto) {
				PlaceRequest placeRequest = new Builder().nameToken(FroNameTokens.SYSTEM_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).update(userDto);
	}

}