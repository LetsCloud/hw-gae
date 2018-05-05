/**
 * 
 */
package hu.hw.cloud.client.fro.table.room;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.util.AbstractAsyncCallback;
import hu.hw.cloud.client.fro.filter.FilterChangeEvent;
import hu.hw.cloud.client.fro.filter.FilterPresenterFactory;
import hu.hw.cloud.client.fro.filter.room.RoomFilterPresenter;
import hu.hw.cloud.client.fro.table.AbstractTablePresenter;
import hu.hw.cloud.client.fro.table.roomtype.RoomTypeTablePresenter;
import hu.hw.cloud.shared.api.RoomResource;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author robi
 *
 */
public class RoomTablePresenter extends AbstractTablePresenter<RoomDto, RoomTablePresenter.MyView>
		implements RoomTableUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(RoomTypeTablePresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RoomTableUiHandlers> {
		void setData(List<RoomDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<RoomResource> resourceDelegate;
	private final RoomFilterPresenter filter;

	@Inject
	RoomTablePresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RoomResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory) {
		super(eventBus, view, placeManager);
		logger.info("RoomTablePresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterPresenterFactory.createRoomFilterPresenter();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void loadData() {
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.ROOM_EDITOR;
	}

	@Override
	protected void deleteData(String webSafeKey) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				loadData();
			}
		}).delete(webSafeKey);
	}

	@Override
	public void onFilterChange(FilterChangeEvent event) {
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomDto>>() {
			@Override
			public void onSuccess(List<RoomDto> result) {
				filter.setFloors(getFloors(result));
				if ((filter.getSelectedFloor() != null) && (!filter.getSelectedFloor().isEmpty()))
					result = result.stream().filter(room -> room.getFloor().equals(filter.getSelectedFloor()))
							.collect(Collectors.toList());
				if (!filter.getSelectedRoomTypeKeys().isEmpty())
					result = result.stream().filter(
							room -> filter.getSelectedRoomTypeKeys().contains(room.getRoomTypeDto().getWebSafeKey()))
							.collect(Collectors.toList());
				getView().setData(result);
			}
		}).getByHotel(filter.getSelectedHotel().getWebSafeKey(), filter.isOnlyActive());

		addFilter(PARAM_HOTEL_KEY, filter.getSelectedHotel().getWebSafeKey());
	}

	private List<String> getFloors(List<RoomDto> rooms) {
		List<String> floors = new ArrayList<String>();
		for (RoomDto room : rooms) {
			if (!floors.contains(room.getFloor()))
				floors.add(room.getFloor());
		}
		floors.sort((p1, p2) -> p1.compareTo(p2));
		floors.add(0, "");
		return floors;
	}
}