/**
 * 
 */
package hu.hw.cloud.client.core.activate;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author CR
 *
 */
public class ActivateView extends ViewWithUiHandlers<ActivateUiHandlers> implements ActivatePresenter.MyView {
	interface Binder extends UiBinder<Widget, ActivateView> {
	}

	@Inject
	ActivateView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
