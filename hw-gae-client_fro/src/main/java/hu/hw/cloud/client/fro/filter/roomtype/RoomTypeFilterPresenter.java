/**
 * 
 */
package hu.hw.cloud.client.fro.filter.roomtype;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;

import hu.hw.cloud.client.core.datasource.HotelDataSource2;
import hu.hw.cloud.client.core.security.CurrentUser;
import hu.hw.cloud.client.fro.filter.AbstractFilterUiHandlers;
import hu.hw.cloud.client.fro.filter.hotelchild.AbstractHotelChildFilterPresenter;
import hu.hw.cloud.shared.cnst.InventoryType;

/**
 * @author robi
 *
 */
public class RoomTypeFilterPresenter extends AbstractHotelChildFilterPresenter<RoomTypeFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RoomTypeFilterPresenter.class.getName());

	public interface MyView extends AbstractHotelChildFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {
		InventoryType getSelectedInventoryType();
	}

	@Inject
	RoomTypeFilterPresenter(EventBus eventBus, MyView view, CurrentUser currentUser, HotelDataSource2 hotelDataSource) {
		super(eventBus, view, currentUser, hotelDataSource);
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
