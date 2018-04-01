/**
 * 
 */
package hu.hw.cloud.client.fro.datasource;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author robi
 *
 */
public class DataSourceModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bind(HotelDataSource.class);
		bind(UserGroupDataSource.class);
		bind(AppUserDataSource.class);
	}
}
