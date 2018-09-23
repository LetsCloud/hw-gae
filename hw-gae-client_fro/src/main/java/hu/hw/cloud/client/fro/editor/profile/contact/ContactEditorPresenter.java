/**
 * 
 */
package hu.hw.cloud.client.fro.editor.profile.contact;

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
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.editor.AbstractEditorPresenterWidget;
import hu.hw.cloud.client.fro.editor.AbstractEditorView;
import hu.hw.cloud.shared.api.ContactResource;
import hu.hw.cloud.shared.cnst.CommMode;
import hu.hw.cloud.shared.cnst.MenuItemType;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.AddressDto;
import hu.hw.cloud.shared.dto.profile.CommunicationDto;
import hu.hw.cloud.shared.dto.profile.ContactDto;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ContactEditorPresenter extends AbstractEditorPresenterWidget<ContactDto, ContactEditorPresenter.MyView>
		implements ContactEditorUiHandlers {
	private static Logger logger = Logger.getLogger(ContactEditorPresenter.class.getName());

	interface MyView extends AbstractEditorView<ContactDto>, HasUiHandlers<ContactEditorUiHandlers> {
		void setProfileGroupData(List<ProfileGroupDto> profileGroupData);

		void displayError(EntityPropertyCode code, String message);

		void setReadOnly(Boolean readOnly);
	}

	private final ResourceDelegate<ContactResource> resourceDelegate;
	private final ProfileGroupDataSource profileGroupDataSource;
	private final CurrentUser currentUser;
	private final CoreMessages i18n;

	@Inject
	ContactEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<ContactResource> resourceDelegate, ProfileGroupDataSource profileGroupDataSource,
			CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, placeManager, view);
		logger.info("ContactEditorPresenter()");

		this.resourceDelegate = resourceDelegate;
		this.profileGroupDataSource = profileGroupDataSource;
		this.currentUser = currentUser;
		this.i18n = i18n;

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
	protected ContactDto createDto() {
		ContactDto dto = new ContactDto();
		dto.getAccount().setId(currentUser.getAppUserDto().getAccount().getId());
		dto.getCommunications().add(new CommunicationDto(true, CommMode.MOBILE));
		dto.getAddresses().add(new AddressDto());
		return dto;
	}

	private void showOrEdit(String webSafeKey) {
		resourceDelegate.withCallback(new AsyncCallback<ContactDto>() {
			@Override
			public void onSuccess(ContactDto dto) {
				SetPageTitleEvent.fire(dto.getName(), i18n.contactDisplayTitle(), MenuItemType.MENU_ITEM,
						ContactEditorPresenter.this);

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
	public void save(ContactDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<ContactDto>() {
			@Override
			public void onSuccess(ContactDto dto) {
				logger.info("OrganizationEditorPresenter().save().onSuccess()->webSafeKey=" + dto.getWebSafeKey());
				loadData();
//		eventBus.fireEvent(new CommunicationActionEvent(CommunicationActionEvent.Action.CLOSE, -1));
//		Builder placeBuilder = new Builder().nameToken(FroNameTokens.ORGANIZATION_DISPLAY);
//		placeBuilder.with(WEBSAFEKEY, String.valueOf(dto.getWebSafeKey()));
//		placeManager.revealPlace(placeBuilder.build());
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

	@Override
	public void edit(String name) {
		SetPageTitleEvent.fire(name, i18n.contactEditorTitle(), MenuItemType.MENU_ITEM,
				ContactEditorPresenter.this);
	}

	@Override
	public void cancel(String name) {
		SetPageTitleEvent.fire(name, i18n.contactDisplayTitle(), MenuItemType.MENU_ITEM,
				ContactEditorPresenter.this);
	}
}