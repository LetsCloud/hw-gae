/**
 * 
 */
package hu.hw.cloud.client.kip.gin;

import javax.inject.Inject;

import hu.hw.cloud.client.core.resources.HwBlueResources;
import hu.hw.cloud.client.kip.resources.KipGssResources;

/**
 * @author CR
 *
 */
public class ResourceLoader {
	@Inject
	ResourceLoader(HwBlueResources resources, KipGssResources gssResources) {
        resources.override().ensureInjected();
		gssResources.common().ensureInjected();
    }
}
