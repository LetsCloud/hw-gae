/**
 * 
 */
package hu.hw.cloud.client.fro.meditor.profilegroup;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.config.AbstractConfigPresenter;
import hu.hw.cloud.client.fro.config.profile.ProfileConfigPresenter;
import hu.hw.cloud.client.fro.meditor.AbstractMeditorPresenter;
import hu.hw.cloud.client.fro.meditor.MeditorView;
import hu.hw.cloud.shared.api.ProfileGroupResource;
import hu.hw.cloud.shared.dto.BaseDto;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileGroupEditorPresenter
		extends AbstractMeditorPresenter<ProfileGroupDto, ProfileGroupEditorPresenter.MyView>
		implements ProfileGroupEditorUiHandlers {
	private static Logger logger = Logger.getLogger(ProfileGroupEditorPresenter.class.getName());

	public interface MyView extends MeditorView<ProfileGroupDto>, HasUiHandlers<ProfileGroupEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<ProfileGroupResource> resourceDelegate;
	private final CurrentUser currentUser;

	@Inject
	ProfileGroupEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<ProfileGroupResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("ProfileGroupEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected ProfileGroupDto createDto() {
		logger.info("ProfileGroupEditorPresenter().createDto");
		ProfileGroupDto dto = new ProfileGroupDto();
		dto.setAccount(new BaseDto());
		dto.getAccount().setId(currentUser.getAppUserDto().getAccount().getId());
		return dto;
	}

	@Override
	public void saveDto(ProfileGroupDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<ProfileGroupDto>() {
			@Override
			public void onSuccess(ProfileGroupDto dto) {
				getView().close();
				PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.PROFILE_CONFIG)
						.with(AbstractConfigPresenter.PLACE_PARAM, ProfileConfigPresenter.PROFILE_GROUPS).build();
				placeManager.revealPlace(placeRequest);
			}

			@Override
			public void onFailure(Throwable caught) {
				getView().displayError(EntityPropertyCode.NONE, caught.getMessage());
			}
		}).saveOrCreate(dto);
	}

	@Override
	public void cancel() {
		getView().close();
	}
}