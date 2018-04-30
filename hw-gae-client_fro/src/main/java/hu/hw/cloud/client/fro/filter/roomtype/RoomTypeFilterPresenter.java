/**
 * 
 */
package hu.hw.cloud.client.fro.filter.roomtype;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import hu.hw.cloud.client.core.datasource.HotelDataSource;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.AbstractFilterPresenter;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;
import hu.hw.cloud.shared.cnst.InventoryType;

/**
 * @author robi
 *
 */
public class RoomTypeFilterPresenter extends AbstractFilterPresenter<RoomTypeFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RoomTypeFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		Boolean isOnlyActive();

		InventoryType getSelectedInventoryType();
	}

	@Inject
	RoomTypeFilterPresenter(EventBus eventBus, MyView view, HotelDataSource hotelDataSource, CurrentUser currentUser) {
		super(eventBus, view, hotelDataSource, currentUser);
		logger.info("RoomTypeFilterPresenter()");
		getView().setUiHandlers(this);
	}

	@Override
	public void onReveal() {
		super.onReveal();
		logger.info("RoomTypeFilterPresenter().onReveal()");
	}

	public Boolean isOnlyActive() {
		return getView().isOnlyActive();
	}

	public InventoryType getSelectedInventoryType() {
		return getView().getSelectedInventoryType();
	}
}
