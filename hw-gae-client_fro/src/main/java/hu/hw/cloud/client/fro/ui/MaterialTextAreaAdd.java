/**
 * 
 */
package hu.hw.cloud.client.fro.ui;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialTextArea;

/**
 * @author robi
 *
 */
public class MaterialTextAreaAdd extends MaterialTextArea {

	private MaterialIcon rightIcon = new MaterialIcon();

	public MaterialTextAreaAdd() {
		super();
	}
	
	public MaterialIcon getRightIcon() {
		return rightIcon;
	}

	public void setRightIconType(IconType iconType) {
		rightIcon.setIconType(iconType);
		rightIcon.setLayoutPosition(Position.ABSOLUTE);
		rightIcon.setTop(0);
		rightIcon.setRight(0);
		add(rightIcon);
//		this.getChildrenList().get(0).getElement().getStyle().setPaddingRight(44, Unit.PX);
	}

	public void setRightIconPosition(IconPosition position) {
		rightIcon.setIconPosition(position);
	}

	public void setRightIconSize(IconSize size) {
		rightIcon.setIconSize(size);
	}

	public void setRightIconFontSize(double size, Unit unit) {
		rightIcon.setIconFontSize(size, unit);
	}

	public void setRightIconColor(Color iconColor) {
		rightIcon.setIconColor(iconColor);
	}

	public Color getRightIconColor() {
		return rightIcon.getIconColor();
	}

	public void setRightIconPrefix(boolean prefix) {
		rightIcon.setIconPrefix(prefix);
	}

	public boolean isRightIconPrefix() {
		return rightIcon.isIconPrefix();
	}

}
