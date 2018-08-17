/**
 * 
 */
package hu.hw.cloud.client.fro.ui;

import java.util.logging.Logger;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionHandler;

import gwt.material.design.addins.client.autocomplete.MaterialAutoComplete;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import hu.hw.cloud.client.fro.reservation.header.UserOracle;

/**
 * @author robi
 *
 */
public class MaterialAutoCompleteAdd extends MaterialAutoComplete implements HasIcon {
	private static Logger logger = Logger.getLogger(MaterialAutoCompleteAdd.class.getName());

	private MaterialIcon icon = new MaterialIcon();

	public MaterialAutoCompleteAdd() {
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
		this.getChildrenList().get(1).getElement().getStyle().setPaddingLeft(44, Unit.PX);
		this.getLabel().getElement().getStyle().setPaddingLeft(44, Unit.PX);
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
