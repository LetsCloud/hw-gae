/**
 * 
 */
package hu.hw.cloud.client.fro.config.system;

import javax.inject.Inject;

import hu.hw.cloud.client.fro.config.AbstractConfigView;

/**
 * @author CR
 *
 */
public class SystemConfigView extends AbstractConfigView implements SystemConfigPresenter.MyView {

	@Inject
	SystemConfigView() {
		super();
	}
}
