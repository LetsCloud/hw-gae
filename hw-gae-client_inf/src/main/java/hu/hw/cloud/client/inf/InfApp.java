/**
 * 
 */
package hu.hw.cloud.client.inf;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.PreBootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import hu.hw.cloud.client.core.security.AppData;
import hu.hw.cloud.shared.cnst.SubSystem;

/**
 * @author CR
 *
 */
public class InfApp  implements Bootstrapper {

	private final PlaceManager placeManager;
	
	private final AppData appData;

	@Inject
	InfApp(PlaceManager placeManager, AppData appData) {
		this.placeManager = placeManager;
		this.appData = appData;
	}

	public static class PreApplicationImpl implements PreBootstrapper {
		private static Logger logger = Logger.getLogger(PreApplicationImpl.class.getName());

		@Override
		public void onPreBootstrap() {
			GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
				public void onUncaughtException(Throwable e) {
					logger.log(Level.SEVERE, e.getMessage(), e);
				}
			});
		}
	}
	
	@Override
	public void onBootstrap() {
		appData.setAppCode(SubSystem.INF);
		placeManager.revealCurrentPlace();
	}
} 