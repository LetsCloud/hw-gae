/**
 * 
 */
package hu.hw.cloud.client.kip.push;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import gwt.material.design.client.ui.MaterialToast;
import hu.hw.cloud.shared.NotificationService;
import hu.hw.cloud.shared.rpc.NotificationDTO;

/**
 * @author robi
 *
 */
public class PushPresenter extends PresenterWidget<PushPresenter.MyView> implements PushUiHandlers {
	private static Logger logger = Logger.getLogger(PushPresenter.class.getName());

	interface MyView extends View, HasUiHandlers<PushUiHandlers> {
		void initView();

		void openModal();
	}

	private final RestDispatch dispatcher;
	private final NotificationService service;

	@Inject
	PushPresenter(EventBus eventBus, MyView view, RestDispatch dispatcher, NotificationService service) {
		super(eventBus, view);
		logger.log(Level.INFO, "PushPresenter()");
		this.service = service;
		this.dispatcher = dispatcher;
		getView().setUiHandlers(this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().initView();
	}

	public void open() {
		getView().openModal();
	}

	@Override
	public void push(NotificationDTO notification) {
		dispatcher.execute(service.notifyAllUser(notification), new AsyncCallback<Void>() {

			@Override
			public void onSuccess(Void aVoid) {
				MaterialToast.fireToast("Successfully notify all the users.");
			}

			@Override
			public void onFailure(Throwable throwable) {
				MaterialToast.fireToast(throwable.getMessage());
			}
		});
	}
}