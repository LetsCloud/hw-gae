package hu.hw.cloud.client.core.login;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.MaterialToast;
import hu.hw.cloud.client.core.resources.CoreResources;
import hu.hw.cloud.shared.cnst.SubSystem;
import hu.hw.cloud.shared.dto.EntityPropertyCode;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {
	private static Logger logger = Logger.getLogger(LoginView.class.getName());

	interface Binder extends UiBinder<Widget, LoginView> {
	}

	@UiField
	MaterialPanel headerPanel;

	@UiField
	HTMLPanel brandPanel;

	@UiField
	MaterialTextBox accountId;

	@UiField
	MaterialTextBox username;

	@UiField
	MaterialTextBox password;

	@UiField
	MaterialCheckBox rememberMe;

	private final CoreResources res;

	@Inject
	LoginView(Binder uiBinder, CoreResources res) {
		logger.info("LoginView()");
		this.res = res;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("submit")
	void onSubmitClicked(ClickEvent event) {
		getUiHandlers().login(accountId.getText(), username.getText(), password.getText(), rememberMe.getValue());
	}

	@Override
	public void setAccountId(String accounId) {

		Timer t = new Timer() {
			@Override
			public void run() {
				accountId.setFocus(true);
				accountId.setText(accounId);
			}
		};
		t.schedule(2000);
	}

	@Override
	public void setAppCode(String appCode) {
		brandPanel.add(new HTML("HostWare <span>Cloud</span> " + appCode + " <span>v1.0</span>"));

		String wallpaperStyle;
		switch (appCode) {
		case SubSystem.INF:
			logger.info("setAppCode(INF)");
			wallpaperStyle = res.wallpaperCss().orangeWallpaper();
			break;
		case SubSystem.FRO:
			logger.info("setAppCode(FRO)");
			wallpaperStyle = res.wallpaperCss().redWallpaper();
			break;
		case SubSystem.KIP:
			logger.info("setAppCode(KIP)");
			wallpaperStyle = res.wallpaperCss().blueWallpaper();
			break;
		default:
			logger.info("setAppCode(DEF)");
			wallpaperStyle = res.wallpaperCss().orangeWallpaper();
		}
		headerPanel.addStyleName(wallpaperStyle);
	}

	@Override
	public void displayError(EntityPropertyCode code, String message) {
		MaterialToast.fireToast(message);
	}
}
