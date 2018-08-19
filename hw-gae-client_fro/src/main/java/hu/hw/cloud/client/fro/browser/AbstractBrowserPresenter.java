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
 * Az <b>AbstractBrowserPresenter</b> egy <b>PresenterWidget</b> utód, amely
 * leszármaztatásakor paraméterként meg kell adni a megjelenítendő adat
 * <b>BasBaseDto</b> utodú típusát és a <b>View</b> utódú megjelenítő nézetet.
 * <p>
 * Az osztály a megjelenítő widget megjelenítésekor (onReveal) a
 * {@link #loadData()} metódus segítségével betölti a megjelenítendő adatokat az
 * utódban kifejtett módon.
 * <p>
 * Az osztály meghívja a nézeten keresztül a felhasználó által kezdeményezet
 * alábbi CRUD funkciókat:
 * <ul>
 * <li>Új adat létrehozása.
 * <li>A kiválasztott adat módosítása.
 * <li>A kiválasztott adat törlése.
 * </ul>
 * 
 * @author robi
 *
 */
public abstract class AbstractBrowserPresenter<T extends BaseDto, V extends View> extends PresenterWidget<V>
		implements AbstractBrowserUiHandlers<T>, RefreshTableEvent.RefreshTableHandler {
	private static Logger logger = Logger.getLogger(AbstractBrowserPresenter.class.getName());

	/**
	 * 
	 */
	public static final String PARAM_DTO_KEY = "id";
	public static final String PARAM_HOTEL_KEY = "hotelKey";
	/**
	 * A megjelenítő komponens felirat.
	 */
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
		loadData();
	}

	/**
	 * A widget mejelenítésekor végrehajtandó adatbetöltés, ami az utóban fejtendő
	 * ki.
	 */
	protected abstract void loadData();

	/**
	 * A törzsadatmegjelenítő feliratának kiolvasása.
	 * 
	 * @return A törzsadatmegjelenítő felirata.
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * A törzsadatmegjelenítő feliratának megadása.
	 * 
	 * @param caption A törzsadatmegjelenítő felirata.
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	/**
	 * Az utódban kifejtendő eljárás, amely a törzsadat létrehozó oldal névjelzőjét
	 * adja vissza.
	 * 
	 * @return A törzsadatszerkeztő oldal névjelzője.
	 */
	protected abstract String getCreatorNameToken();

	/**
	 * Az utódban kifejtendő eljárás, amely a törzsadatszerkesztő oldal névjelzőjét
	 * adja vissza.
	 * 
	 * @return A törzsadatszerkeztő oldal névjelzője.
	 */
	protected abstract String getEditorNameToken();

	@Override
	public void addNew() {
		Builder placeBuilder = new Builder().nameToken(getCreatorNameToken());
		placeManager.revealPlace(addFilters(placeBuilder));
	}

	@Override
	public void edit(T dto) {
		Builder placeBuilder = new Builder().nameToken(getEditorNameToken());
		placeBuilder.with(PARAM_DTO_KEY, String.valueOf(dto.getWebSafeKey()));
		placeManager.revealPlace(addFilters(placeBuilder));
	}

	private PlaceRequest addFilters(Builder placeBuilder) {
		if (!filters.isEmpty())
			placeBuilder.with(filters);
		return placeBuilder.build();
	}

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
