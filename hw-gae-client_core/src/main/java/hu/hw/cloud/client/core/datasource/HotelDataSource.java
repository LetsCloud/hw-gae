/**
 * 
 */
package hu.hw.cloud.client.core.datasource;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;

import gwt.material.design.client.data.DataSource;
import gwt.material.design.client.data.loader.LoadCallback;
import gwt.material.design.client.data.loader.LoadConfig;
import gwt.material.design.client.data.loader.LoadResult;

import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.shared.HotelResource;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelDataSource implements DataSource<HotelDto> {
	private static Logger logger = Logger.getLogger(HotelDataSource.class.getName());

	private Boolean isLoaded = false;

	private final ResourceDelegate<HotelResource> resourceDelegate;

	@Inject
	HotelDataSource(ResourceDelegate<HotelResource> resourceDelegate) {
		logger.info("HotelDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<HotelDto> loadConfig, LoadCallback<HotelDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<HotelDto>>() {
			@Override
			public void onSuccess(List<HotelDto> result) {
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
				isLoaded = true;
			}
		}).list();
	}

	@Override
	public boolean useRemoteSort() {
		return false;
	}

	public Boolean getIsLoaded() {
		return isLoaded;
	}

	public void setIsLoaded(Boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

}