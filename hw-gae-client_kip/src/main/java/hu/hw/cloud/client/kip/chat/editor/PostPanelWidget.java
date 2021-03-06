/**
 * 
 */
package hu.hw.cloud.client.kip.chat.editor;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import hu.hw.cloud.shared.dto.chat.ChatPostDto;

/**
 * @author robi
 *
 */
public class PostPanelWidget extends Composite {

	private static ChatPanelUiBinder uiBinder = GWT.create(ChatPanelUiBinder.class);

	interface ChatPanelUiBinder extends UiBinder<Widget, PostPanelWidget> {
	}

	@UiField
	HTMLPanel panel;

	/**
	 * 
	 */
	public PostPanelWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPosts(List<ChatPostDto> posts, String currenUserKey) {
		panel.clear();
		for (ChatPostDto p : posts) {
			panel.add(new PostWidget(p.getSender().getPicture(), p.getMessage(), p.getCreated().toString(),
					currenUserKey.equals(p.getSender().getWebSafeKey())));
		}
	}
}
