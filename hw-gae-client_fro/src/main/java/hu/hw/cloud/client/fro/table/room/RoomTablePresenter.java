/**
 * 
 */
package hu.hw.cloud.client.fro.table.room;

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
import hu.hw.cloud.client.fro.table.AbstractTablePresenter;
import hu.hw.cloud.client.fro.table.DtoTablePresenterFactory;
import hu.hw.cloud.client.fro.table.filter.FilterChangeEvent;
import hu.hw.cloud.client.fro.table.filter.FilterWidgetPresenter;
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
	private final FilterWidgetPresenter filter;

	@Inject
	RoomTablePresenter(EventBus eventBus, PlaceManager placeManager, MyView view,
			ResourceDelegate<RoomResource> resourceDelegate, DtoTablePresenterFactory dtoTablePresenterFactory) {
		super(eventBus, view, placeManager);
		logger.info("RoomTablePresenter()");

		this.resourceDelegate = resourceDelegate;
		this.filter = dtoTablePresenterFactory.createFilterWidgetPresenter();

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
		logger.info("RoomTypeTablePresenter().onFilterChange()");
		resourceDelegate.withCallback(new AbstractAsyncCallback<List<RoomDto>>() {
			@Override
			public void onSuccess(List<RoomDto> result) {
				logger.info("RoomTypeTablePresenter().loadData().onSuccess()");
				getView().setData(result);
			}
		}).getByHotel(filter.getSelectedHotel().getWebSafeKey());
		
		addFilter(PARAM_HOTEL_KEY, filter.getSelectedHotel().getWebSafeKey());
	}
}