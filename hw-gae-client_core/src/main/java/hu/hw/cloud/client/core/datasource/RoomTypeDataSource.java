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
import hu.hw.cloud.shared.api.RoomTypeResource;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomTypeDataSource implements DataSource<RoomTypeDto> {
	private static Logger logger = Logger.getLogger(RoomTypeDataSource.class.getName());

	private Boolean isLoaded = false;
	
	private String hotelKey;
	
	private Boolean onlyActive = true;

	private final ResourceDelegate<RoomTypeResource> resourceDelegate;

	@Inject
	RoomTypeDataSource(ResourceDelegate<RoomTypeResource> resourceDelegate) {
		logger.info("RoomTypeDataSource()");
		this.resourceDelegate = resourceDelegate;
	}

	@Override
	public void load(LoadConfig<RoomTypeDto> loadConfig, LoadCallback<RoomTypeDto> callback) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomTypeDto>>() {
			@Override
			public void onSuccess(List<RoomTypeDto> result) {
				callback.onSuccess(new LoadResult<>(result, loadConfig.getOffset(), result.size()));
				isLoaded = true;
			}
		}).getAll(hotelKey, onlyActive);
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

	public String getHotelKey() {
		return hotelKey;
	}

	public void setHotelKey(String hotelKey) {
		this.hotelKey = hotelKey;
	}

	public Boolean getOnlyActive() {
		return onlyActive;
	}

	public void setOnlyActive(Boolean onlyActive) {
		this.onlyActive = onlyActive;
	}

}