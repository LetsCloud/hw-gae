/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import java.util.List;
import java.util.logging.Logger;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;

import hu.hw.cloud.client.core.event.RefreshTableEvent;
import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class AbstractTablePresenter<T extends BaseDto, V extends View> extends PresenterWidget<V>
		implements DtoTableUiHandlers<T>, RefreshTableEvent.RefreshTableHandler {
	private static Logger logger = Logger.getLogger(AbstractTablePresenter.class.getName());

	private String caption;
	
	private final PlaceManager placeManager;

	public AbstractTablePresenter(EventBus eventBus, V view, PlaceManager placeManager) {
		super(eventBus, view);
		logger.info("AbstractTablePresenter()");

		this.placeManager = placeManager;

		addRegisteredHandler(RefreshTableEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		loadData();
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	protected abstract String getEditorNameToken();

	@Override
	public void addNew() {
		logger.info("addNew()");
		PlaceRequest placeRequest = new Builder().nameToken(getEditorNameToken()).build();
		placeManager.revealPlace(placeRequest);
	}

	@Override
	public void edit(T dto) {
		logger.info("editItem()->dto=" + dto);
		PlaceRequest placeRequest = new Builder().nameToken(getEditorNameToken())
				.with("id", String.valueOf(dto.getWebSafeKey())).build();

		placeManager.revealPlace(placeRequest);
	}

	protected abstract void loadData();

	@Override
	public void onRefresh(RefreshTableEvent event) {
		loadData();
	}

	@Override
	public void delete(List<T> dtos) {
		for (T dto : dtos)
			deleteData(dto.getWebSafeKey());
		loadData();
	}

	protected abstract void deleteData(String webSafeKey);
}
