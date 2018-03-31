/**
 * 
 */
package hu.hw.cloud.client.fro.editor;

import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class AbstractEditorPresenter<T extends BaseDto, V extends EditorView<T>, P extends Proxy<?>>
		extends Presenter<V, P> implements EditorUiHandlers<T> {
	private static Logger logger = Logger.getLogger(AbstractEditorPresenter.class.getName());

	protected String dtoWebSafeKey;

	private final PlaceManager placeManager;

	public AbstractEditorPresenter(EventBus eventBus, PlaceManager placeManager, V view, P proxy) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}

	public AbstractEditorPresenter(EventBus eventBus, PlaceManager placeManager, V view, P proxy, GwtEvent.Type<RevealContentHandler<?>> slot) {
		super(eventBus, view, proxy, null, slot);
		this.placeManager = placeManager;
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		logger.info("AppUserEditorPresenter().prepareFromRequest()");
		dtoWebSafeKey = request.getParameter("id", null);
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		loadData();
	}

	protected abstract void loadData();

	protected Boolean isNew() {
		return Strings.isNullOrEmpty(dtoWebSafeKey);
	}

	protected void create() {
		logger.info("AbstractEditorPresenter().create()");
		T dto = createDto();
		getView().edit(true, dto);
	}

	protected abstract T createDto();

	@Override
	public void save(T dto) {
		if (isNew()) {
			createEntity(dto);
		} else {
			updateEntity(dto);
		}
	}

	protected abstract void createEntity(T dto);

	protected abstract void updateEntity(T dto);

	@Override
	public void cancel() {
		placeManager.navigateBack();
	}
}
