/**
 * 
 */
package hu.hw.cloud.client.fro.config.customer;

import javax.inject.Inject;

import hu.hw.cloud.client.fro.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class CustomerConfigView extends AbstractConfigView implements CustomerConfigPresenter.MyView {

	@Inject
	CustomerConfigView() {
		super();
	}
}
