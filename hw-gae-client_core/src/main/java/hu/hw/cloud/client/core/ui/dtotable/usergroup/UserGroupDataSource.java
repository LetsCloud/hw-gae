/**
 * 
 */
package hu.hw.cloud.client.core.ui.dtotable.usergroup;

import java.util.List;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import gwt.material.design.client.data.DataSource;
import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.shared.UserGroupResource;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupDataSource implements DataSource<UserGroupDto> {

	private final ResourceDelegate<UserGroupResource> resourceDelegate;

	public UserGroupDataSource(ResourceDelegate<UserGroupResource> resourceDelegate) {
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<UserGroupDto> loadConfig, LoadCallback<UserGroupDto> callback) {

		resourceDelegate.withCallback(new AbstractAsyncCallback<List<UserGroupDto>>() {
			@Override
			public void onSuccess(List<UserGroupDto> result) {
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).list();
	}

	@Override
	public boolean useRemoteSort() {
		return false;
	}
}