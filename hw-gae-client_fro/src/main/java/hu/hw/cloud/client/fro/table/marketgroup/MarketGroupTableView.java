/**
 * 
 */
package hu.hw.cloud.client.fro.table.marketgroup;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreConstants;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.table.BaseTableView;
import hu.hw.cloud.client.fro.table.DataColumn;
import hu.hw.cloud.client.fro.table.EditColumn;
import hu.hw.cloud.client.fro.table.roomtype.RoomTypeTablePresenter;
import hu.hw.cloud.shared.dto.hotel.MarketGroupDto;

/**
 * @author robi
 *
 */
public class MarketGroupTableView extends ViewWithUiHandlers<MarketGroupTableUiHandlers>
		implements MarketGroupTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(MarketGroupTableView.class.getName());

	private final BaseTableView<MarketGroupDto> table;

	private final CoreMessages i18nCore;
	private final CoreConstants cnstCore;

	/**
	* 
	*/
	@Inject
	MarketGroupTableView(BaseTableView<MarketGroupDto> table, CoreMessages i18nCore, CoreConstants cnstCore) {
		logger.info("MarketGroupTableView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;
		this.cnstCore = cnstCore;

		bindSlot(MarketGroupTablePresenter.SLOT_FILTER, table.getFilterPanel());
		bindSlot(MarketGroupTablePresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {

		table.setTitle(i18nCore.roomTypesTableTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

// Code Column
		table.addColumn(
				new DataColumn<MarketGroupDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18nCore.roomTypesTableCode());

// Name Column
		table.addColumn(
				new DataColumn<MarketGroupDto>((object) -> object.getDescription(),
						(o1, o2) -> o1.getData().getDescription().compareToIgnoreCase(o2.getData().getDescription())),
				i18nCore.roomTypesTableName());

// Active Column
		table.addColumn(new DataColumn<MarketGroupDto>((object) -> {
			if (object.getActive()) {
				return i18nCore.roomTypesTableActive();
			} else {
				return i18nCore.roomTypesTableInactive();
			}
		}), i18nCore.roomTypesTableActive());

// Edit Column
		table.addColumn(new EditColumn<MarketGroupDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<MarketGroupDto> data) {
		logger.info("MarketGroupTableView().setData()");
		table.setData(data);
	}
}
