/**
 * 
 */
package hu.hw.cloud.client.kip.chat.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import gwt.material.design.client.base.MaterialWidget;
import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.shared.ChatResource;
import hu.hw.cloud.shared.api.AppUserResource;
import hu.hw.cloud.shared.dto.chat.ChatDto;
import hu.hw.cloud.shared.dto.chat.ChatPostDto;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class ChatCreatorPresenter extends PresenterWidget<ChatCreatorPresenter.MyView>
		implements ChatCreatorUiHandlers {
	private static Logger logger = Logger.getLogger(ChatCreatorPresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<ChatCreatorUiHandlers> {
		void open(MaterialWidget widget);

		void clearReceiverList();

		void addUserGroupList(List<UserGroupDto> userGroups);

		void addAppUserList(List<UserGroupDto> appUsers);

		void close();
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<ChatResource> chatResource;
	private final ResourceDelegate<AppUserResource> appUserResource;
	private final CurrentUser currentUser;

	@Inject
	ChatCreatorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<ChatResource> chatResource, ResourceDelegate<AppUserResource> appUserResource,
			CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("ChatCreatorPresenter()");

		this.placeManager = placeManager;
		this.chatResource = chatResource;
		this.appUserResource = appUserResource;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
	}

	public void open(MaterialWidget widget) {
		loadReceiversData();
		getView().open(widget);
	}

	private void loadReceiversData() {
		appUserResource.withCallback(new AbstractAsyncCallback<List<AppUserDto>>() {
			@Override
			public void onSuccess(List<AppUserDto> users) {
				List<UserGroupDto> userGroups = new ArrayList<UserGroupDto>();
				List<UserGroupDto> appUsers = new ArrayList<UserGroupDto>();
				
				for (AppUserDto appUser : users) {
					for (UserGroupDto userGroup : appUser.getUserGroups())
						if (!userGroups.contains(userGroup))
							userGroups.add(userGroup);

					for (UserGroupDto receiver : userGroups) {
						for (UserGroupDto userGroup2 : appUser.getUserGroups())
							if (receiver.equals(userGroup2)) {
								if (!receiver.getMembers().contains(appUser))
									receiver.getMembers().add(appUser);
							}
					}

					appUsers.add(new UserGroupDto(appUser));
				}
				getView().clearReceiverList();
				getView().addUserGroupList(userGroups);
				getView().addAppUserList(appUsers);
			}
		}).list();
	}

	@Override
	public void saveChat(List<AppUserDto> receivers, String message) {

		ChatDto dto = new ChatDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		dto.setSender(currentUser.getAppUserDto());
		dto.setReceivers(receivers);
		dto.setMessage(message);
		
		PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.getHome()).with("id", "").build();
		dto.setUrl(GWT.getHostPageBaseURL() + "kip.html#" + placeManager.buildHistoryToken(placeRequest));

		ChatPostDto post = new ChatPostDto(dto.getSender(), message);
		dto.getPosts().add(post);

		createEntity(dto);
	}

	private void createEntity(ChatDto dto) {
		chatResource.withCallback(new AsyncCallback<ChatDto>() {
			@Override
			public void onSuccess(ChatDto dto) {
				getView().close();
				PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.getHome())
						.with("id", String.valueOf(dto.getWebSafeKey())).build();

				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				/*
				 * if (caught instanceof CustomActionException) {
				 * customMessage((CustomActionException) caught); return; }
				 * getView().displayError(EntityPropertyCode.USER_GROUP_NAME,
				 * caught.getMessage());
				 */
			}
		}).create(dto);
	}

}
