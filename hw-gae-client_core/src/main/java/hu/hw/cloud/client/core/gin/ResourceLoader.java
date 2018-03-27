/**
 * 
 */
package hu.hw.cloud.client.core.gin;

import javax.inject.Inject;

import com.google.gwt.dom.client.StyleInjector;

import hu.hw.cloud.client.core.resources.CoreResources;
import hu.hw.cloud.client.core.resources.GssResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
    ResourceLoader(CoreResources resources, GssResources gssResources) {

		StyleInjector.injectAtStart(resources.materialize().getText());
		StyleInjector.inject(resources.gwtMaterial().getText());
		StyleInjector.injectAtEnd(resources.coreCss().getText());
		
		resources.wallpaperCss().ensureInjected();
		gssResources.common().ensureInjected();
       
    }
}
