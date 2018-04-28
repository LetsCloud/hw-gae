/**
 * 
 */
package hu.hw.cloud.client.fro.table.roomtype;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.table.BaseTableView;
import hu.hw.cloud.client.fro.table.DataColumn;
import hu.hw.cloud.client.fro.table.EditColumn;
import hu.hw.cloud.shared.dto.hotel.RoomTypeDto;

/**
 * @author robi
 *
 */
public class RoomTypeTableView extends ViewWithUiHandlers<RoomTypeTableUiHandlers>
		implements RoomTypeTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(RoomTypeTableView.class.getName());

	private final BaseTableView<RoomTypeDto> table;

	private final CoreMessages i18n;

	/**
	* 
	*/
	@Inject
	RoomTypeTableView(BaseTableView<RoomTypeDto> table, CoreMessages i18n) {
		logger.info("RoomTypeTableView()");
		initWidget(table);

		this.table = table;
		this.i18n = i18n;

		bindSlot(RoomTypeTablePresenter.SLOT_FILTER, table.getFilterPanel());

		initTable();
	}

	private void initTable() {

		table.setTitle(i18n.roomTypesTableTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Code Column
		table.addColumn(
				new DataColumn<RoomTypeDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18n.roomTypesTableCode());

		// Name Column
		table.addColumn(
				new DataColumn<RoomTypeDto>((object) -> object.getName(),
						(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())),
				i18n.roomTypesTableName());

		// Edit Column
		table.addColumn(new EditColumn<RoomTypeDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<RoomTypeDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
