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
import hu.hw.cloud.client.fro.filter.AbstractFilterPresenter;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;

/**
 * @author robi
 *
 */
public class HotelChildFilterPresenter extends AbstractFilterPresenter<HotelChildFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(HotelChildFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		Boolean isOnlyActive();
	}

	@Inject
	HotelChildFilterPresenter(EventBus eventBus, MyView view, HotelDataSource hotelDataSource, CurrentUser currentUser) {
		super(eventBus, view, hotelDataSource, currentUser);
		logger.info("HotelChildFilterPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
	}

	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}
}
