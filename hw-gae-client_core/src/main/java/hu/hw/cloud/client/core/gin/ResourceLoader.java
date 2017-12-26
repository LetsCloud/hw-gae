/**
 * 
 */
package hu.hw.cloud.client.core.gin;

import javax.inject.Inject;

import com.google.gwt.dom.client.StyleInjector;

import hu.hw.cloud.client.core.resources.CoreResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
    ResourceLoader(CoreResources resources) {

		StyleInjector.injectAtStart(resources.materialize().getText());
		StyleInjector.inject(resources.gwtMaterial().getText());
		StyleInjector.injectAtEnd(resources.coreCss().getText());
		
		resources.wallpaperCss().ensureInjected();
        
    }
}
