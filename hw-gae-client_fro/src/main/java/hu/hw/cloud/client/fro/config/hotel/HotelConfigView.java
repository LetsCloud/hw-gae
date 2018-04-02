/**
 * 
 */
package hu.hw.cloud.client.fro.config.hotel;

import javax.inject.Inject;

import hu.hw.cloud.client.fro.config.AbstractConfigView;

/**
 * @author robi
 *
 */
public class HotelConfigView extends AbstractConfigView implements HotelConfigPresenter.MyView {

	@Inject
	HotelConfigView() {
		super();
	}
}
