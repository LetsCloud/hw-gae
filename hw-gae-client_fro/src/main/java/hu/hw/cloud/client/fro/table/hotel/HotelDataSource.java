/**
 * 
 */
package hu.hw.cloud.client.fro.table.hotel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	private final ResourceDelegate<HotelResource> resourceDelegate;

	public HotelDataSource(ResourceDelegate<HotelResource> resourceDelegate) {
		logger.info("HotelDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	
	@Override
	public void load(LoadConfig<HotelDto> loadConfig, LoadCallback<HotelDto> callback) {
		logger.log(Level.INFO, "AppUserDataSource.load()");

		resourceDelegate.withCallback(new AbstractAsyncCallback<List<HotelDto>>() {
			
			@Override
			public void onSuccess(List<HotelDto> result) {
				logger.info("HotelDataSource.load().onSuccess()");
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
			}
		}).list();
	}

	@Override
	public boolean useRemoteSort() {
		return false;
	}
}