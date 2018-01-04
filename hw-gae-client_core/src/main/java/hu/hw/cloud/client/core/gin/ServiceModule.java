/**
 * 
 */
package hu.hw.cloud.client.core.gin;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.inject.Provides;
import com.gwtplatform.dispatch.rest.client.RestApplicationPath;
import com.gwtplatform.dispatch.rest.client.gin.RestDispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

/**
 * @author CR
 *
 */
public class ServiceModule extends AbstractPresenterModule {
	private static final Logger logger = Logger.getLogger(ServiceModule.class.getName());

	@Override
	protected void configure() {
		// logger.log(Level.INFO, "configure()");
		install(new RestDispatchAsyncModule.Builder().xsrfTokenHeaderName("Protection-Token").build());
		bindConstant().annotatedWith(SecurityCookie.class).to("JSESSIONID");
	}

	@Provides
	@RestApplicationPath
	String getApplicationPath() {
		String baseUrl = GWT.getHostPageBaseURL();
		logger.log(Level.INFO, "getApplicationPath()->baseUrl=" + baseUrl);

		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 4);
			logger.log(Level.INFO, "if (baseUrl.endsWith(/))->baseUrl=" + baseUrl);
		} else {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 3);
			logger.log(Level.INFO, "if (baseUrl.endsWith(/))->baseUrl=" + baseUrl);
		}

		return baseUrl;
	}
}
