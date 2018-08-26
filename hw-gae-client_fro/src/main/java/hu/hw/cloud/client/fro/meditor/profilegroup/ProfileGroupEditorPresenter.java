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
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.FroNameTokens;
import hu.hw.cloud.client.fro.meditor.AbstractMeditorPresenter;
import hu.hw.cloud.client.fro.meditor.MeditorView;
import hu.hw.cloud.shared.api.ProfileGroupResource;
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
	private final CoreMessages i18n;

	@Inject
	ProfileGroupEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<ProfileGroupResource> resourceDelegate, CurrentUser currentUser, CoreMessages i18n) {
		super(eventBus, view);
		logger.info("ProfileGroupEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;
		this.i18n = i18n;

		getView().setUiHandlers(this);
	}

	@Override
	protected ProfileGroupDto createDto() {
		logger.info("ProfileGroupEditorPresenter().createDto");
		ProfileGroupDto dto = new ProfileGroupDto();
		dto.setAccount(currentUser.getAppUserDto().getAccount());
		return dto;
	}

	@Override
	public void saveDto(ProfileGroupDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<ProfileGroupDto>() {
			@Override
			public void onSuccess(ProfileGroupDto dto) {
				getView().close();
				PlaceRequest placeRequest = new Builder().nameToken(FroNameTokens.PROFILE_CONFIG).build();
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