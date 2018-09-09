/**
 * 
 */
package hu.hw.cloud.client.fro.filter.accountchild;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.filter.AbstractFilterView;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class AccountChildFilterView extends AbstractFilterView implements AccountChildFilterPresenter.MyView {
	private static Logger logger = Logger.getLogger(AccountChildFilterView.class.getName());

	@Inject
	AccountChildFilterView(CoreMessages i18nCore, CoreConstants cnstCore) {
		super(i18nCore);
		logger.info("AccountChildFilterView()");
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
}
