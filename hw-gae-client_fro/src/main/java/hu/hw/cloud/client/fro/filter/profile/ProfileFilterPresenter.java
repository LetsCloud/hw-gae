/**
 * 
 */
package hu.hw.cloud.client.fro.filter.profile;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import hu.hw.cloud.client.core.datasource.ProfileGroupDataSource;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.AbstractFilterPresenter;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;
import hu.hw.cloud.shared.dto.profile.ProfileGroupDto;

/**
 * @author robi
 *
 */
public class ProfileFilterPresenter extends AbstractFilterPresenter<ProfileFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(ProfileFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		String getCode();

		String getName();

		void setProfileGroupData(List<ProfileGroupDto> profileGroupData);

		List<String> getSelectedProfileGroupKeys();
	}

	private final CurrentUser currentUser;
	private final ProfileGroupDataSource profileGroupDataSource;
	
	@Inject
	ProfileFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser, ProfileGroupDataSource profileGroupDataSource) {
		super(eventBus, view, currentUser);
		logger.info("ProfileFilterPresenter()");

		this.currentUser = currentUser;
		this.profileGroupDataSource = profileGroupDataSource;

		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("ProfileFilterPresenter().onReveal()");
		LoadCallback<ProfileGroupDto> profileGroupLoadCallback = new LoadCallback<ProfileGroupDto>() {

			@Override
			public void onSuccess(LoadResult<ProfileGroupDto> loadResult) {
				getView().setProfileGroupData(loadResult.getData());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		};
		profileGroupDataSource.load(new LoadConfig<ProfileGroupDto>(0, 0, null, null), profileGroupLoadCallback);
	}

	public String getCode() {
		return getView().getCode();
	}

	public String getName() {
		return getView().getName();
	}

	public List<String> getProfileGroupKeys() {
		return getView().getSelectedProfileGroupKeys();
	}
}
