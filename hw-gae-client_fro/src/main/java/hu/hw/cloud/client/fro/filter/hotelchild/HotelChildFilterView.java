/**
 * 
 */
package hu.hw.cloud.client.fro.filter.hotelchild;

import java.util.logging.Logger;

import javax.inject.Inject;

import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.filter.AbstractFilterView;

/**
 * @author robi
 *
 */
public class HotelChildFilterView extends AbstractFilterView implements HotelChildFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(HotelChildFilterView.class.getName());

	@Inject
	HotelChildFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		logger.info("HotelChildFilterView()");
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
