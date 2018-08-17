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

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.app.AppPresenter;
import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.datasource.UserGroupDataSource;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.ErrorHandlerAsyncCallback;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenter;
import hu.hw.cloud.client.fro.editor.EditorView;
import hu.hw.cloud.shared.api.AppUserResource;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

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

		void setHotelData(List<HotelDto> data);

		void displayError(EntityPropertyCode code, String message);
	}

	@ProxyCodeSplit
	@NameToken(CoreNameTokens.USER_EDITOR)
	interface MyProxy extends ProxyPlace<AppUserEditorPresenter> {
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<AppUserResource> resourceDelegate;
	private final UserGroupDataSource userGroupDataSource;
	private final HotelDataSource hotelDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	AppUserEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view, MyProxy proxy,
			ResourceDelegate<AppUserResource> resourceDelegate, UserGroupDataSource userGroupDataSource,
			HotelDataSource hotelDataSource, CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, placeManager, view, proxy, AppPresenter.SLOT_MAIN);
		logger.info("AppUserEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.userGroupDataSource = userGroupDataSource;
		this.hotelDataSource = hotelDataSource;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		LoadCallback<UserGroupDto> groupLC = new LoadCallback<UserGroupDto>() {
			@Override
			public void onSuccess(LoadResult<UserGroupDto> loadResult) {
				getView().setUserGroupData(loadResult.getData());
				if (areDataSourcesLoaded()) {
					start();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		userGroupDataSource.load(new LoadConfig<UserGroupDto>(0, 0, null, null), groupLC);

		LoadCallback<HotelDto> hotelLoadCallback = new LoadCallback<HotelDto>() {
			@Override
			public void onSuccess(LoadResult<HotelDto> loadResult) {
				getView().setHotelData(loadResult.getData());
				if (areDataSourcesLoaded()) {
					start();
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		hotelDataSource.load(new LoadConfig<HotelDto>(0, 0, null, null), hotelLoadCallback);
	}

	private void start() {
		if (isNew()) {
			SetPageTitleEvent.fire(i18n.userEditorCreateTitle(), "", MenuItemType.MENU_ITEM,
					AppUserEditorPresenter.this);
			create();
		} else {
			SetPageTitleEvent.fire(i18n.userEditorModifyTitle(), "", MenuItemType.MENU_ITEM,
					AppUserEditorPresenter.this);
			edit(filters.get(AbstractBrowserPresenter.PARAM_DTO_KEY));
		}
	}

	private Boolean areDataSourcesLoaded() {
		logger.info("AppUserEditorPresenter().areDataSourcesLoaded()-1");
		if (!userGroupDataSource.getIsLoaded()) {
			logger.info("AppUserEditorPresenter().areDataSourcesLoaded()-2");
			return false;
		}

		if (!hotelDataSource.getIsLoaded()) {
			logger.info("AppUserEditorPresenter().areDataSourcesLoaded()-3");
			return false;
		}

		logger.info("AppUserEditorPresenter().areDataSourcesLoaded()-4");
		return true;
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
	public void save(AppUserDto dto) {
		resourceDelegate.withCallback(new ErrorHandlerAsyncCallback<AppUserDto>(this) {
			@Override
			public void onSuccess(AppUserDto dto) {
				PlaceRequest placeRequest = new Builder().nameToken(FroNameTokens.SYSTEM_CONFIG).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

}