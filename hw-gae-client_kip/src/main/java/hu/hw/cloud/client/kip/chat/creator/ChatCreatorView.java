/**
 * 
 */
package hu.hw.cloud.client.kip.chat.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.overlay.MaterialOverlay;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CheckBoxType;
import gwt.material.design.client.constants.CollectionType;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialCollectionSecondary;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.html.OptGroup;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.kip.chat.editor.SendMessageWidget;
import hu.hw.cloud.shared.dto.common.AppUserDto;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class ChatCreatorView extends ViewWithUiHandlers<ChatCreatorUiHandlers> implements ChatCreatorPresenter.MyView {
	private static Logger logger = Logger.getLogger(ChatCreatorView.class.getName());

	interface Binder extends UiBinder<Widget, ChatCreatorView> {
	}

	@UiField
	MaterialOverlay overlay;

//	@UiField
//	MaterialComboBox<UserGroupDto> receiversComboBox;

	@UiField
	SendMessageWidget sendMessageWidget;

	@UiField
	MaterialButton btnCloseOverlay;
	
	@UiField
	MaterialCollection receiverCollection;

	private final CoreMessages i18n;

	/**
	 */
	@Inject
	ChatCreatorView(Binder uiBinder, CoreMessages i18n) {
		logger.info("ChatCreatorView()");
		this.i18n = i18n;
		initWidget(uiBinder.createAndBindUi(this));
		sendMessageWidget.addSendIconClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				saveMessage();
			}
		});
	}

	@Override
	public void open(MaterialWidget widget) {
		overlay.open(widget);
	}

	@Override
	public void clearReceiverList() {
//		receiversComboBox.clear();
		receiverCollection.clear();
	}

	@Override
	public void addUserGroupList(List<UserGroupDto> userGroups) {
//		receiversComboBox.add(new OptGroup(i18n.createChatGroupGroup()));
		for (UserGroupDto dto : userGroups) {
//			receiversComboBox.addItem(dto.getName(), dto);
			receiverCollection.add(new ReceiverItem(dto));
		}
	}

	@Override
	public void addAppUserList(List<UserGroupDto> appUsers) {
//		receiversComboBox.add(new OptGroup(i18n.createChatUserGroup()));
		for (UserGroupDto dto : appUsers) {
//			receiversComboBox.addItem(dto.getName(), dto);
			receiverCollection.add(new ReceiverItem(dto));
		}
	}

	private void saveMessage() {
		logger.info("saveMessage()");
		List<AppUserDto> receivers = new ArrayList<AppUserDto>();
		
		for (Widget w : receiverCollection) {
			ReceiverItem ri = (ReceiverItem)w;
			if (ri.isSelected()) {
				logger.info("saveMessage()->userGroup.getName()=" + ri.getValue().getName());
				for (AppUserDto receiver : ri.getValue().getMemberDtos()) {
					logger.info("saveMessage()->receiver.getName()=" + receiver.getName());
					receiver.getUserGroupDtos().clear();
					receivers.add(receiver);
				}
			}
		}
/*		
		List<UserGroupDto> selected = receiversComboBox.getSelectedValues();
		for (UserGroupDto userGroup : selected) {
			logger.info("saveMessage()->userGroup.getName()=" + userGroup.getName());
			for (AppUserDto receiver : userGroup.getMemberDtos()) {
				logger.info("saveMessage()->receiver.getName()=" + receiver.getName());
				receiver.getUserGroupDtos().clear();
				receivers.add(receiver);
			}
		}
*/		
		logger.info("saveMessage()-2");
		getUiHandlers().saveChat(receivers, sendMessageWidget.getText());
		sendMessageWidget.clearText();
	}

	@Override
	public void close() {
		overlay.close();
	}

	@UiHandler("btnCloseOverlay")
	public void onCloseOverlay(ClickEvent event) {
		close();
	}
	
	private MaterialCollectionItem createReceiverItem(UserGroupDto userGroup) {
		MaterialCollectionItem item = new MaterialCollectionItem();
		item.setType(CollectionType.CHECKBOX);
		item.setWaves(WavesType.DEFAULT);
		
		item.add(new MaterialLabel(userGroup.getName()));
		
		MaterialCheckBox checkBox = new MaterialCheckBox();
		checkBox.setType(CheckBoxType.FILLED);
		
		MaterialCollectionSecondary sec = new MaterialCollectionSecondary();
		sec.add(checkBox);
		
		item.add(sec);
		
		return item;
	}
}
