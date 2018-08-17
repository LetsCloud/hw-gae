/**
 * 
 */
package hu.hw.cloud.client.fro.browser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public abstract class AbstractBrowserPresenter<T extends BaseDto, V extends View> extends PresenterWidget<V>
		implements BrowserUiHandlers<T>, RefreshTableEvent.RefreshTableHandler {
	private static Logger logger = Logger.getLogger(AbstractBrowserPresenter.class.getName());

	public static final String PARAM_DTO_KEY = "id";
	public static final String PARAM_HOTEL_KEY = "hotelKey";

	private String caption;
	private Map<String, String> filters = new HashMap<String, String>();
	private final PlaceManager placeManager;

	public AbstractBrowserPresenter(EventBus eventBus, V view, PlaceManager placeManager) {
		super(eventBus, view);
		logger.info("AbstractTablePresenter()");

		this.placeManager = placeManager;

		addRegisteredHandler(RefreshTableEvent.TYPE, this);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		logger.info("AbstractTablePresenter().onReveal()");
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
		Builder placeBuilder = new Builder().nameToken(getEditorNameToken());
		placeManager.revealPlace(addFilters(placeBuilder));
	}

	@Override
	public void edit(T dto) {
		Builder placeBuilder = new Builder().nameToken(getEditorNameToken());
		placeBuilder.with(PARAM_DTO_KEY, String.valueOf(dto.getWebSafeKey()));
		placeManager.revealPlace(addFilters(placeBuilder));
	}

	private PlaceRequest addFilters(Builder placeBuilder) {
		if(!filters.isEmpty())
			placeBuilder.with(filters);
		return placeBuilder.build();
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
	
	protected void addFilter(String key, String value) {
		filters.put(key, value);
	}
}
