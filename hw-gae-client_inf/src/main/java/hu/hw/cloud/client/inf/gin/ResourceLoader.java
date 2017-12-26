package hu.hw.cloud.client.inf.gin;

import javax.inject.Inject;

import com.google.gwt.dom.client.StyleInjector;

import hu.hw.cloud.client.core.resources.HwOrangeResources;
import hu.hw.cloud.client.inf.resources.InfResources;

public class ResourceLoader {
	@Inject
	ResourceLoader(HwOrangeResources resources, InfResources infRes) {
        resources.override().ensureInjected();
		StyleInjector.injectAtEnd(infRes.infCss().getText());
    }
}
