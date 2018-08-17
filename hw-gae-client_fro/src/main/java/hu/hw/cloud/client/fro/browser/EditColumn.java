/**
 * 
 */
package hu.hw.cloud.client.fro.browser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.cell.WidgetColumn;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public class EditColumn<T extends BaseDto> extends WidgetColumn<T, MaterialIcon> {
	
	public interface EditRow<T extends BaseDto> {
		void edit(T object);
	}

	private EditRow<T> editRow;

	public EditColumn(EditRow<T> editRow) {
		this.editRow = editRow;
	}
	
	@Override
	public MaterialIcon getValue(T object) {
		MaterialIcon icon = new MaterialIcon();
		icon.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				editRow.edit(object);
			}
		});

		icon.setWaves(WavesType.DEFAULT);
		icon.setIconType(IconType.EDIT);
		icon.setBackgroundColor(Color.AMBER);
		icon.setCircle(true);
		icon.setTextColor(Color.WHITE);
		// icon.setWidth("50px");
		// icon.setIconPosition(IconPosition.NONE);
		return icon;
	}

	@Override
	public TextAlign textAlign() {
		return TextAlign.RIGHT;
	}
}
