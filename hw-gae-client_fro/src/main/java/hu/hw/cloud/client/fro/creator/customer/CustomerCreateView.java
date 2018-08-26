/**
 * 
 */
package hu.hw.cloud.client.fro.creator.customer;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.shared.dto.profile.OrganizationDto;

/**
 * @author robi
 *
 */
public class CustomerCreateView extends ViewWithUiHandlers<CustomerCreateUiHandlers>
		implements CustomerCreatePresenter.MyView, Editor<OrganizationDto> {
	private static Logger logger = Logger.getLogger(CustomerCreateView.class.getName());

	interface Binder extends UiBinder<Widget, CustomerCreateView> {
	}

	@UiField
	HTMLPanel contentPanel;
	
	/**
	* 
	*/
	@Inject
	CustomerCreateView(Binder uiBinder) {
		logger.info("CustomerCreateView()");

		initWidget(uiBinder.createAndBindUi(this));
		
		bindSlot(CustomerCreatePresenter.SLOT_CONTENT, contentPanel);
		
	}
}
