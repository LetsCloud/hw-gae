/**
 * 
 */
package hu.hw.cloud.client.kip.chat;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author CR
 *
 */
public class ChatView extends ViewWithUiHandlers<ChatUiHandlers> implements ChatPresenter.MyView {

	interface Binder extends UiBinder<Widget, ChatView> {
	}

	/**
	 */
	@Inject
	ChatView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
