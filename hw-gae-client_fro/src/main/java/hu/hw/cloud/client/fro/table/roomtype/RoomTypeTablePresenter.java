/**
 * 
 */
package hu.hw.cloud.client.fro.table.roomtype;

import java.util.List;
import java.util.logging.Logger;

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
import hu.hw.cloud.client.fro.filter.roomtype.RoomTypeFilterPresenter;
import hu.hw.cloud.client.fro.table.AbstractTablePresenter;
import hu.hw.cloud.shared.api.RoomTypeResource;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomTypeTablePresenter extends AbstractTablePresenter<RoomTypeDto, RoomTypeTablePresenter.MyView>
		implements RoomTypeTableUiHandlers, FilterChangeEvent.FilterChangeHandler {
	private static Logger logger = Logger.getLogger(RoomTypeTablePresenter.class.getName());

	public interface MyView extends View, HasUiHandlers<RoomTypeTableUiHandlers> {
		void setData(List<RoomTypeDto> data);
	}

	public static final SingleSlot<PresenterWidget<?>> SLOT_FILTER = new SingleSlot<>();

	private final ResourceDelegate<RoomTypeResource> resourceDelegate;
	private final RoomTypeFilterPresenter filter;

	@Inject
	RoomTypeTablePresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RoomTypeResource> resourceDelegate, FilterPresenterFactory filterPresenterFactory) {
		super(eventBus, view, placeManager);
		logger.info("RoomTypeTablePresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = filterPresenterFactory.createRoomTypeFilterPresenter();

		addVisibleHandler(FilterChangeEvent.TYPE, this);

		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_FILTER, filter);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("RoomTypeTablePresenter().onReveal()");
//		filter.onReveal();
	}

	@Override
	protected void loadData() {
	}

	@Override
	protected String getEditorNameToken() {
		return CoreNameTokens.ROOMTYPE_EDITOR;
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
		logger.info("RoomTypeTablePresenter().onFilterChange()");
		logger.info("filter.isOnlyActive()" + filter.isOnlyActive());
		logger.info("filter.getSelectedInventoryType()" + filter.getSelectedInventoryType());
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomTypeDto>>() {
			@Override
			public void onSuccess(List<RoomTypeDto> result) {
				getView().setData(result);
			}
		}).getAll(filter.getSelectedHotel().getWebSafeKey(), filter.isOnlyActive(), filter.getSelectedInventoryType());
	}
}