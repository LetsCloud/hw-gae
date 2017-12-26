/**
 * 
 */
package hu.hw.cloud.client.kip.gin;

import javax.inject.Inject;

import hu.hw.cloud.client.core.resources.HwBlueResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(HwBlueResources resources) {
        resources.override().ensureInjected();
    }
}
