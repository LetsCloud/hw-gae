package hu.hw.cloud.client.fro.filter.room;

import java.util.List;
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
public class RoomFilterPresenter extends AbstractFilterPresenter<RoomFilterPresenter.MyView> {
	private static Logger logger = Logger.getLogger(RoomFilterPresenter.class.getName());

	public interface MyView extends AbstractFilterPresenter.MyView, HasUiHandlers<AbstractFilterUiHandlers> {

		void setFloors(List<String> floors);

		String getSelectedFloor();
	}

	@Inject
	RoomFilterPresenter(EventBus eventBus, MyView view, HotelDataSource hotelDataSource, CurrentUser currentUser) {
		super(eventBus, view, hotelDataSource, currentUser);
		logger.info("RoomFilterPresenter()");
		getView().setUiHandlers(this);
	}

	public void setFloors(List<String> floors) {
		getView().setFloors(floors);
	}

	public String getSelectedFloor() {
		return getView().getSelectedFloor();
	}
}
