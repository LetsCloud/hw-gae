/**
 * 
 */
package hu.hw.cloud.client.fro.meditor.relationship;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.meditor.AbstractMeditorPresenter;
import hu.hw.cloud.client.fro.meditor.MeditorView;
import hu.hw.cloud.shared.api.RelationshipResource;
import hu.hw.cloud.shared.dto.EntityPropertyCode;
import hu.hw.cloud.shared.dto.profile.RelationshipDto;

/**
 * @author robi
 *
 */
public class RelationshipEditorPresenter
		extends AbstractMeditorPresenter<RelationshipDto, RelationshipEditorPresenter.MyView>
		implements RelationshipEditorUiHandlers {
	private static Logger logger = Logger.getLogger(RelationshipEditorPresenter.class.getName());

	public interface MyView extends MeditorView<RelationshipDto>, HasUiHandlers<RelationshipEditorUiHandlers> {

		void displayError(EntityPropertyCode code, String message);
	}

	private final PlaceManager placeManager;
	private final ResourceDelegate<RelationshipResource> resourceDelegate;
	private final CurrentUser currentUser;

	@Inject
	RelationshipEditorPresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RelationshipResource> resourceDelegate, CurrentUser currentUser) {
		super(eventBus, view);
		logger.info("RelationshipEditorPresenter()");

		this.placeManager = placeManager;
		this.resourceDelegate = resourceDelegate;
		this.currentUser = currentUser;

		getView().setUiHandlers(this);
	}

	@Override
	protected RelationshipDto createDto() {
		RelationshipDto dto = new RelationshipDto();
		dto.getAccount().setId(currentUser.getAppUserDto().getAccount().getId());
		return dto;
	}

	@Override
	public void saveDto(RelationshipDto dto) {
		resourceDelegate.withCallback(new AsyncCallback<RelationshipDto>() {
			@Override
			public void onSuccess(RelationshipDto dto) {
				getView().close();
				PlaceRequest placeRequest = new Builder().nameToken(CoreNameTokens.PROFILE_CONFIG).build();
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