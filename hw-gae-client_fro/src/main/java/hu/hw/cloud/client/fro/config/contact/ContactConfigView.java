/**
 * 
 */
package hu.hw.cloud.client.fro.config.contact;

import javax.inject.Inject;

import hu.hw.cloud.client.fro.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class ContactConfigView extends AbstractConfigView implements ContactConfigPresenter.MyView {

	@Inject
	ContactConfigView() {
		super();
	}

}
