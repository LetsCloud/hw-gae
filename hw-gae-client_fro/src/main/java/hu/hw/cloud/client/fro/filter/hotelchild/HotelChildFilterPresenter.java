/**
 * 
 */
package hu.hw.cloud.client.fro.filter.hotelchild;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;

/**
 * @author robi
 *
 */
public class HotelChildFilterPresenter extends AbstractHotelChildFilterPresenter<HotelChildFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(HotelChildFilterPresenter.class.getName());

	public interface MyView extends AbstractHotelChildFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
	}

	@Inject
	HotelChildFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser,
			HotelDataSource hotelDataSource) {
		super(eventBus, view, currentUser, hotelDataSource);
		logger.info("HotelChildFilterPresenter()");

		getView().setUiHandlers(this);
	}
}
