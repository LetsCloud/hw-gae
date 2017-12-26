/**
 * 
 */
package hu.hw.cloud.client.core.success;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreMessages;

/**
 * @author CR
 *
 */
public class SuccessView extends ViewWithUiHandlers<SuccessUiHandlers> implements SuccessPresenter.MyView {
	interface Binder extends UiBinder<Widget, SuccessView> {
	}

	@UiField
	Label accountIdLabel;

	private final CoreMessages i18n;

	@Inject
	SuccessView(Binder uiBinder, CoreMessages i18n) {
		initWidget(uiBinder.createAndBindUi(this));
		this.i18n = i18n;
	}

	@Override
	public void setAccountId(String accountId) {
		accountIdLabel.setText(i18n.sucLine2(accountId));
	}
}
