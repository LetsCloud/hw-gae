/**
 * 
 */
package hu.hw.cloud.client.fro.ui;

import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;

import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;

import static gwt.material.design.jquery.client.api.JQuery.$;

/**
 * @author robi
 *
 */
public class MaterialComboBoxAdd<T> extends MaterialComboBox<T> implements HasIcon {
	private static Logger logger = Logger.getLogger(MaterialComboBoxAdd.class.getName());

	private MaterialIcon icon = new MaterialIcon();

	public MaterialComboBoxAdd() {
		super();
	}

	@Override
	public MaterialIcon getIcon() {
		return icon;
	}

	@Override
	public void setIconType(IconType iconType) {
		logger.info("setIconType");
		icon.setIconType(iconType);
		icon.setIconPrefix(true);
		insert(icon, 0);

		Timer t = new Timer() {
			@Override
			public void run() {
				$(getElement()).find(".select2-container").css("padding-left", "44px");
			}
		};
		t.schedule(150);
	}

	@Override
	public void setIconPosition(IconPosition position) {
		icon.setIconPosition(position);
	}

	@Override
	public void setIconSize(IconSize size) {
		icon.setIconSize(size);
	}

	@Override
	public void setIconFontSize(double size, Unit unit) {
		icon.setIconFontSize(size, unit);
	}

	@Override
	public void setIconColor(Color iconColor) {
		icon.setIconColor(iconColor);
	}

	@Override
	public Color getIconColor() {
		return icon.getIconColor();
	}

	@Override
	public void setIconPrefix(boolean prefix) {
		icon.setIconPrefix(prefix);
	}

	@Override
	public boolean isIconPrefix() {
		return icon.isIconPrefix();
	}
}
