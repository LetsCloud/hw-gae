package hu.hw.cloud.client.core.security;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.annotations.DefaultGatekeeper;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import hu.hw.cloud.client.core.CoreNameTokens;

@DefaultGatekeeper
public class LoggedInGatekeeper implements Gatekeeper {
	private static Logger logger = Logger.getLogger(LoggedInGatekeeper.class.getName());

	private final String PLACE_TO_GO = "placeToGo";

	private final PlaceManager placeManager;
	private final CurrentUser currentUser;

	@Inject
	LoggedInGatekeeper(PlaceManager placeManager, CurrentUser currentUser) {
		logger.log(Level.INFO, "LoggedInGatekeeper");
		this.placeManager = placeManager;
		this.currentUser = currentUser;
	}

	@Override
	public boolean canReveal() {
		logger.log(Level.INFO, "LoggedInGatekeeper.canReveal()");
		if (!currentUser.isLoggedIn())
			goToLogin();
		return currentUser.isLoggedIn();
	}

	private void goToLogin() {
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(CoreNameTokens.LOGIN)
				.with(PLACE_TO_GO, placeManager.getCurrentPlaceRequest().getNameToken()).build();
		placeManager.revealPlace(placeRequest);
	}

}
