/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile.organization;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.common.base.Strings;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import hu.hw.cloud.client.core.datasource.ProfileGroupDataSource;
import hu.hw.cloud.client.core.event.SetPageTitleEvent;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenterWidget;
import hu.hw.cloud.client.fro.editor.AbstractEditorView;
import hu.hw.cloud.shared.api.OrganizationResource;
import hu.hw.cloud.shared.cnst.CommMode;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.OrganizationDto;
import hu.hw.cloud.shared.dto.profile.AddressDto;
import hu.hw.cloud.shared.dto.profile.CommunicationDto;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public final class OrganizationEditorPresenter
		extends AbstractEditorPresenterWidget<OrganizationDto, OrganizationEditorPresenter.MyView>
		implements OrganizationEditorUiHandlers {
	private static Logger logger = Logger.getLogger(OrganizationEditorPresenter.class.getName());

	interface MyView extends AbstractEditorView<OrganizationDto>, HasUiHandlers<OrganizationEditorUiHandlers> {
		void setProfileGroupData(List<ProfileGroupDto> profileGroupData);

		void displayError(EntityPropertyCode code, String message);

		void setReadOnly(Boolean readOnly);
	}

	private final ResourceDelegate<OrganizationResource> resourceDelegate;
	private final ProfileGroupDataSource profileGroupDataSource;
	private final CurrentUser currentUser;

	@Inject
	OrganizationEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<OrganizationResource> resourceDelegate, ProfileGroupDataSource profileGroupDataSource,
			CurrentUser currentUser) {
		super(eventBus, placeManager, view);
		logger.info("OrganizationEditorPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.profileGroupDataSource = profileGroupDataSource;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected void loadData() {
		profileGroupDataSource.setOnlyActive(true);

		LoadCallback<ProfileGroupDto> profileGroupLoadCallback = new LoadCallback<ProfileGroupDto>() {
			@Override
			public void onSuccess(LoadResult<ProfileGroupDto> loadResult) {
				getView().setProfileGroupData(loadResult.getData());
				if (Strings.isNullOrEmpty(getWebSafeKey())) {
					create();
				} else {
					showOrEdit(getWebSafeKey());
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
		};
		profileGroupDataSource.load(new LoadConfig<ProfileGroupDto>(0, 0, null, null), profileGroupLoadCallback);
	}

	@Override
	protected OrganizationDto createDto() {
		OrganizationDto dto = new OrganizationDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		dto.getCommunications().add(new CommunicationDto(true, CommMode.MOBILE));
		dto.getAddresses().add(new AddressDto());
		return dto;
	}

	private void showOrEdit(String webSafeKey) {
		logger.info("OrganizationEditorPresenter().showOrEdit(" + webSafeKey + ")");
		resourceDelegate.withCallback(new AsyncCallback<OrganizationDto>() {
			@Override
			public void onSuccess(OrganizationDto dto) {
				logger.info("OrganizationEditorPresenter().edit().onSuccess(" + dto + ")");
				SetPageTitleEvent.fire(dto.getCode(), dto.getName(), MenuItemType.MENU_ITEM,
						OrganizationEditorPresenter.this);

				if (getReadOnly()) {
					getView().show(dto);
				} else {
					getView().edit(dto);
				}
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).read(webSafeKey);
	}

	@Override
	public void save(OrganizationDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<OrganizationDto>() {
			@Override
			public void onSuccess(OrganizationDto dto) {
				logger.info("OrganizationEditorPresenter().save().onSuccess()->webSafeKey=" + dto.getWebSafeKey());
				loadData();
//				eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.CLOSE, -1));
//				Builder placeBuilder = new Builder().nameToken(FroNameTokens.ORGANIZATION_DISPLAY);
//				placeBuilder.with(WEBSAFEKEY, String.valueOf(dto.getWebSafeKey()));
//				placeManager.revealPlace(placeBuilder.build());
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	public void setReadOnly(Boolean readOnly) {
		getView().setReadOnly(readOnly);
	}
}