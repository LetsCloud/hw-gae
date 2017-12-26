/**
 * 
 */
package hu.hw.cloud.client.fro.gin;

import javax.inject.Inject;

import hu.hw.cloud.client.core.resources.HwRedResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
    ResourceLoader(HwRedResources resources) {
        resources.override().ensureInjected();
    }
}
