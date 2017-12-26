/**
 * 
 */
package hu.hw.cloud.client.core.register;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.client.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;
import hu.hw.cloud.client.core.success.SuccessPresenter;
import hu.hw.cloud.shared.AccountResource;
import hu.hw.cloud.shared.dto.RegisterDto;

/**
 * @author CR
 *
 */
public class RegisterPresenter extends Presenter<RegisterPresenter.MyView, RegisterPresenter.MyProxy> implements RegisterUiHandlers {
	private static Logger LOGGER = Logger.getLogger(RegisterPresenter.class.getName());

	interface MyView extends HasUiHandlers<RegisterUiHandlers>, EditorView<RegisterDto> {
	}

	@NameToken(CoreNameTokens.REGISTER)
	@ProxyCodeSplit
	@NoGatekeeper
	interface MyProxy extends ProxyPlace<RegisterPresenter> {
	}

	private final PlaceManager placeManager;
	private final RestDispatch dispatcher;
	private final AccountResource accountService;

	private SimpleBeanEditorDriver<RegisterDto, ?> driver;

	@Inject
	RegisterPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, RestDispatch dispatcher,
			AccountResource accountService) {
		super(eventBus, view, proxy, RevealType.Root);
		LOGGER.info("RegisterPresenter");

		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.accountService = accountService;

		getView().setUiHandlers(this);
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
		driver = getView().createEditorDriver();
		driver.edit(new RegisterDto());
	}

	@Override
	protected void onReset() {
		driver.edit(new RegisterDto());
	}

	@Override
	public void register() {
		LOGGER.info("register");

		RegisterDto registerDto = driver.flush();

		LOGGER.info("register->" + registerDto.getAccountName());
		dispatcher.execute(accountService.register(registerDto), new AsyncCallback<RegisterDto>() {

			@Override
			public void onSuccess(RegisterDto result) {
				LOGGER.info("register->onSuccess");
				onRegisterSuccess(result.getAccountId());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void onRegisterSuccess(Long accountId) {
		LOGGER.info("onRegisterSuccess()");
		// Window.alert("Success registration(" + accountId + ")!");
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.SUCCESS)
				.with(SuccessPresenter.ACCOUNT_ID, accountId.toString()).build();
		placeManager.revealPlace(placeRequest);
	}

}
