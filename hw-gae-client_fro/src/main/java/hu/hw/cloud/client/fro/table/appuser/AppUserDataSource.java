/**
 * 
 */
package hu.hw.cloud.client.fro.table.appuser;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import gwt.material.design.client.data.DataSource;
import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
public class AppUserDataSource implements DataSource<AppUserDto> {
	private static Logger logger = Logger.getLogger(AppUserDataSource.class.getName());

	private final ResourceDelegate<AppUserResource> usersDelegate;

	public AppUserDataSource(ResourceDelegate<AppUserResource> usersDelegate) {
		logger.log(Level.INFO, "AppUserDataSource()");
		this.usersDelegate = usersDelegate;
	}

	
	@Override
	public void load(LoadConfig<AppUserDto> loadConfig, LoadCallback<AppUserDto> callback) {
		logger.log(Level.INFO, "AppUserDataSource.load()");

		usersDelegate.withCallback(new AbstractAsyncCallback<List<AppUserDto>>() {
			@Override
			public void onSuccess(List<AppUserDto> result) {
				logger.log(Level.INFO, "AppUserDataSource.load()->onSuccess");
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).list();
	}

	@Override
	public boolean useRemoteSort() {
		return false;
	}
}