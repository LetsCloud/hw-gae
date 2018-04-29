/**
 * 
 */
package hu.hw.cloud.client.fro.table.room;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.table.BaseTableView;
import hu.hw.cloud.client.fro.table.DataColumn;
import hu.hw.cloud.client.fro.table.EditColumn;
import hu.hw.cloud.shared.dto.hotel.RoomDto;

/**
 * @author robi
 *
 */
public class RoomTableView extends ViewWithUiHandlers<RoomTableUiHandlers> implements RoomTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomTableView.class.getName());

	private final BaseTableView<RoomDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	RoomTableView(BaseTableView<RoomDto> table, CoreMessages i18nCore) {
		logger.info("RoomTypeTableView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(RoomTablePresenter.SLOT_FILTER, table.getFilterPanel());

		initTable();
	}

	private void initTable() {

		table.setTitle(i18nCore.roomsTableTitle());

		table.getAddButton().addClickHandler(e -> getUiHandlers().addNew());
		table.getDeleteIcon().addClickHandler(e -> getUiHandlers().delete(table.getSelected()));

		// Code Column
		table.addColumn(
				new DataColumn<RoomDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomsTableCode());

		// Type Column
		table.addColumn(new DataColumn<RoomDto>((object) -> {
			if (object.getRoomTypeDto() != null) {
				return object.getRoomTypeDto().getCode();
			} else {
				return null;
			}
		}), i18nCore.roomsTableType());

		// Edit Column
		table.addColumn(new EditColumn<RoomDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<RoomDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
