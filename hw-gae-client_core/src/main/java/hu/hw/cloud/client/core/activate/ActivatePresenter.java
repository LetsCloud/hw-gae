/**
 * 
 */
package hu.hw.cloud.client.core.activate;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.shared.AppUserResource;

/**
 * @author CR
 *
 */
public class ActivatePresenter extends Presenter<ActivatePresenter.MyView, ActivatePresenter.MyProxy> implements ActivateUiHandlers {
	private static Logger logger = Logger.getLogger(ActivatePresenter.class.getName());

	interface MyView extends View, HasUiHandlers<ActivateUiHandlers> {
	}

	private final RestDispatch dispatcher;
	private final AppUserResource appUserResource;

	private String token;

	@NameToken(CoreNameTokens.ACTIVATE)
	@ProxyCodeSplit
	@NoGatekeeper
	interface MyProxy extends ProxyPlace<ActivatePresenter> {
	}

	@Inject
	ActivatePresenter(EventBus eventBus, MyView view, MyProxy proxy, RestDispatch dispatcher,
			AppUserResource appUserResource) {
		super(eventBus, view, proxy, RevealType.Root);
		logger.log(Level.FINE, "ActivatePresenter");
		this.dispatcher = dispatcher;
		this.appUserResource = appUserResource;

		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		token = request.getParameter("token", null);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		activateAccount();
	}

	private void activateAccount() {

		logger.info("activateAccount()");
		dispatcher.execute(appUserResource.activate(token), new AsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean result) {
				logger.info("activateAccount->onSuccess()");
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
	    });
	}
}
