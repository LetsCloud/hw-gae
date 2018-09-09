/**
 * 
 */
package hu.hw.cloud.client.fro.config.organization;

import javax.inject.Inject;

import hu.hw.cloud.client.fro.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class OrganizationConfigView extends AbstractConfigView implements OrganizationConfigPresenter.MyView {

	@Inject
	OrganizationConfigView() {
		super();
	}

}
