/**
 * 
 */
package hu.hw.cloud.client.fro.gin;

import java.util.logging.Logger;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

import hu.hw.cloud.client.fro.app.FroAppModule;
import hu.hw.cloud.client.fro.config.contact.ContactConfigModule;
import hu.hw.cloud.client.fro.config.hotel.HotelConfigModule;
import hu.hw.cloud.client.fro.config.organization.OrganizationConfigModule;
import hu.hw.cloud.client.fro.config.profile.ProfileConfigModule;
import hu.hw.cloud.client.fro.config.system.SystemConfigModule;
import hu.hw.cloud.client.fro.creator.contact.ContactCreateModule;
import hu.hw.cloud.client.fro.creator.organization.OrganizationCreateModule;
import hu.hw.cloud.client.fro.dashboard.DashboardModule;
import hu.hw.cloud.client.fro.filter.FilterModule;
import hu.hw.cloud.client.fro.reservation.ReservationModule;
import hu.hw.cloud.client.fro.resnew.ResNewModule;
import hu.hw.cloud.client.core.gin.CoreModule;

/**
 * @author CR
 *
 */
public class ClientModule extends AbstractPresenterModule {
	private static Logger logger = Logger.getLogger(ClientModule.class.getName());

	@Override
	protected void configure() {
		install(new CoreModule());

		bind(ResourceLoader.class).asEagerSingleton();

		install(new FroAppModule());

		install(new DashboardModule());
		install(new ResNewModule());
		install(new ReservationModule());

		install(new SystemConfigModule());
		install(new ProfileConfigModule());
		install(new HotelConfigModule());

		install(new OrganizationConfigModule());
		install(new ContactConfigModule());

		install(new OrganizationCreateModule());
		install(new ContactCreateModule());

		install(new FilterModule());

	}
}
