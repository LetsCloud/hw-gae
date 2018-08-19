/**
 * 
 */
package hu.hw.cloud.client.fro.config.profile;

import javax.inject.Inject;

import hu.hw.cloud.client.fro.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class ProfileConfigView extends AbstractConfigView implements ProfileConfigPresenter.MyView {

	@Inject
	ProfileConfigView() {
		super();
	}
}
