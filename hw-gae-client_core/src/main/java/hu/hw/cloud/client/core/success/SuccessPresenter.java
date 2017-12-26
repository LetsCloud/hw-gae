/**
 * 
 */
package hu.hw.cloud.client.core.success;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;


/**
 * @author CR
 *
 */
public class SuccessPresenter extends Presenter<SuccessPresenter.MyView, SuccessPresenter.MyProxy>
		implements SuccessUiHandlers {
	private static Logger LOGGER = Logger.getLogger(SuccessPresenter.class.getName());
	
	public static final String ACCOUNT_ID = "accountId";

	interface MyView extends View, HasUiHandlers<SuccessUiHandlers> {
		void setAccountId(String accountId);
	}

	private String accountId;

	@NameToken(CoreNameTokens.SUCCESS)
	@ProxyCodeSplit
	@NoGatekeeper
	interface MyProxy extends ProxyPlace<SuccessPresenter> {
	}

	@Inject
	SuccessPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);
		LOGGER.log(Level.FINE, "SuccessPresenter");
		getView().setUiHandlers(this);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		accountId = request.getParameter(ACCOUNT_ID, null);
	}

	@Override
	protected void onBind() {
		super.onBind();
		LOGGER.log(Level.FINE, "onBind()");
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		LOGGER.log(Level.FINE, "onReveal()");
		getView().setAccountId(accountId);
	}

}
