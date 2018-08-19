/**
 * 
 */
package hu.hw.cloud.client.fro.config;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialCollection;
import gwt.material.design.client.ui.MaterialCollectionItem;
import gwt.material.design.client.ui.MaterialCollectionSecondary;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

/**
 * @author robi
 *
 */
public abstract class AbstractConfigView extends ViewWithUiHandlers<ConfigUiHandlers>
		implements AbstractConfigPresenter.MyView {
	private static Logger logger = Logger.getLogger(AbstractConfigView.class.getName());

	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, AbstractConfigView> {
	}

	@UiField
	MaterialPanel mobilePanel, desktopPanel;

	@UiField
	MaterialColumn tablePanel;

	@UiField
	MaterialDropDown mobileDropDown;

	@UiField
	MaterialCollection desktopMenu;

	public AbstractConfigView() {
		logger.log(Level.INFO, "AbstractConfigView()");
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(AbstractConfigPresenter.SLOT_CONTENT, tablePanel);
	}

	@Override
	public void buildMenu(List<String> captions) {
		mobileDropDown.clear();
		desktopMenu.clear();

		for (int i = 0; i < captions.size(); i++) {
			logger.log(Level.INFO, "AbstractConfigView().buildMenu()->"+i);
			addMobileMenuItem(i, captions.get(i));
			addDesktopMenuItem(i, captions.get(i));
		}
	}

	private void addMobileMenuItem(Integer index, String caption) {
		MaterialLink link = new MaterialLink(caption);
		link.addClickHandler(handler -> {
			getUiHandlers().showContent(index);
		});
		mobileDropDown.add(link);
	}

	private void addDesktopMenuItem(Integer index, String caption) {

		MaterialCollectionItem item = createDesktopItem(caption);
		item.addClickHandler(handler -> {
			getUiHandlers().showContent(index);
		});
		desktopMenu.add(item);
	}

	private MaterialCollectionItem createDesktopItem(String caption) {
		MaterialLabel label = new MaterialLabel(caption);
		label.setPaddingTop(5);

//		MaterialIcon icon = new MaterialIcon(IconType.KEYBOARD_ARROW_RIGHT);
//		icon.setIconPosition(IconPosition.RIGHT);

		MaterialCollectionSecondary sec = new MaterialCollectionSecondary();
		sec.setPaddingBottom(10);
//		sec.add(icon);

		MaterialCollectionItem item = new MaterialCollectionItem();
		item.setWaves(WavesType.DEFAULT);
		item.setPaddingRight(10);
		item.add(label);
		item.add(sec);

		return item;
	}

	@Override
	public void setDesktopMenu(Integer index) {
		desktopMenu.setActive(index+1, true);
	}

	@Override
	public void setMobileView(Boolean show) {
		mobilePanel.setVisible(show);
		desktopPanel.setVisible(!show);
	}

}
