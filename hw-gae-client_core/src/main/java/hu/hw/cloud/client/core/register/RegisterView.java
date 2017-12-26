/**
 * 
 */
package hu.hw.cloud.client.core.register;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.core.i18n.CountryConstants;
import hu.hw.cloud.shared.dto.RegisterDto;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.base.validator.BlankValidator;
import gwt.material.design.client.base.validator.SizeValidator;
import gwt.material.design.client.ui.MaterialTextBox;

/**
 * @author CR
 *
 */
public class RegisterView extends ViewWithUiHandlers<RegisterUiHandlers> implements RegisterPresenter.MyView {
	private static final Logger LOGGER = Logger.getLogger(RegisterView.class.getName());

	interface Binder extends UiBinder<Widget, RegisterView> {
	}

	@UiField
	MaterialTextBox accountName, street, city, postcode, username, userEmail, confEmail, userPassword, confPassword;

	@Ignore
	@UiField
	MaterialComboBox<String> country;

	// @Ignore
	// @UiField
	// TextBox captchaTextBox;

	public interface Driver extends SimpleBeanEditorDriver<RegisterDto, RegisterView> {
	}

	private CoreMessages i18n;

	@Inject
	RegisterView(Binder uiBinder, CoreMessages i18n, CountryConstants countries) {
		LOGGER.log(Level.FINE, "RegisterView()");
		this.i18n = i18n;
		initWidget(uiBinder.createAndBindUi(this));
		setValidation();
		// setCaptcha();
		country.clear();
		for (Map.Entry<String, String> countryCode : countries.countryMap().entrySet()) {
			country.addItem( countryCode.getValue(), countryCode.getKey());
		}
	//	country.reinitialize();
	}

	private void setValidation() {
		accountName.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
		street.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
		city.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
		postcode.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
//		country.addValidator(new BlankValidator<String>(i18n.comRequiredField()));

		username.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
		username.addValidator(new SizeValidator<String>(4, 20, "Ne {0} hagyd {1} űressen!"));

		userEmail.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
		confEmail.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
		userPassword.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
		confPassword.addValidator(new BlankValidator<String>(i18n.comRequiredField()));
	}

	private void setCaptcha() {
		// captchaTextBox.getElement().setId("recaptcha_response_field");
		// captchaTextBox.setName("recaptcha_response_field");
		// showCaptcha("6Le6VicTAAAAAExDWTZ90P_SaLye3ry5ym-PdAoD");
	}

	@Override
	public SimpleBeanEditorDriver<RegisterDto, ?> createEditorDriver() {
		Driver driver = GWT.create(Driver.class);
		driver.initialize(this);
		return driver;
	}

	@UiHandler("submitButton")
	void onRegisterClicked(ClickEvent event) {
		if (!validate())
			return;

		getUiHandlers().register();
	}

	private Boolean validate() {
		Boolean valid = true;

		valid = (accountName.validate(true)) ? valid : false;
		valid = (street.validate(true)) ? valid : false;
		valid = (city.validate(true)) ? valid : false;
		valid = (postcode.validate(true)) ? valid : false;
		valid = (country.validate(true)) ? valid : false;
		valid = (username.validate(true)) ? valid : false;
		valid = (userEmail.validate(true)) ? valid : false;
		valid = (confEmail.validate(true)) ? valid : false;
		valid = (userPassword.validate(true)) ? valid : false;
		valid = (confPassword.validate(true)) ? valid : false;

		return valid;

	}

	private native void showCaptcha(String publicKey)
	/*-{
	    $wnd.Recaptcha.create(publicKey, "", {
	        "theme" : "custom"
	    });
	}-*/;

	private native String getCaptchaChallenge()
	/*-{
	    return $wnd.Recaptcha.get_challenge();
	}-*/;

	private native void destroyCaptcha()
	/*-{
	    $wnd.Recaptcha.destroy();
	}-*/;

}
