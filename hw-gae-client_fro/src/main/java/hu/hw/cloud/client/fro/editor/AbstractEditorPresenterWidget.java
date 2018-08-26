/**
 * 
 */
package hu.hw.cloud.client.fro.editor;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import hu.hw.cloud.client.fro.browser.AbstractBrowserPresenter;
import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public abstract class AbstractEditorPresenterWidget<T extends BaseDto, V extends AbstractEditorView<T>>
		extends DataPresenterWidget<V> implements AbstractEditorUiHandlers<T> {
	private static Logger logger = Logger.getLogger(AbstractEditorPresenterWidget.class.getName());

	protected Map<String, String> filters = new HashMap<String, String>();

	private Boolean createOnly = false;

	private final PlaceManager placeManager;

	public AbstractEditorPresenterWidget(EventBus eventBus, PlaceManager placeManager, V view) {
		super(eventBus, view);
		this.placeManager = placeManager;
	}

	@Override
	protected void onReveal() {
		super.onReveal();

		loadData();
	}

	protected abstract void loadData();

	protected Boolean isNew() {
		return Strings.isNullOrEmpty(filters.get(AbstractBrowserPresenter.PARAM_DTO_KEY));
	}

	protected void create() {
		logger.info("AbstractEditorPresenter().create()");
		T dto = createDto();
		getView().edit(true, dto);
	}

	protected abstract T createDto();

	@Override
	public void cancel() {
		getView().close();
		placeManager.navigateBack();
	}

	public Boolean getCreateOnly() {
		return createOnly;
	}

	public void setCreateOnly(Boolean createOnly) {
		this.createOnly = createOnly;
	}
}
