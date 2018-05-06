/**
 * 
 */
package hu.hw.cloud.client.fro.table.appuser;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.table.BaseTableView;
import hu.hw.cloud.client.fro.table.DataColumn;
import hu.hw.cloud.client.fro.table.EditColumn;
import hu.hw.cloud.shared.api.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

/**
 * @author CR
 *
 */
public class AppUserTableView extends ViewWithUiHandlers<AppUserTableUiHandlers>
		implements AppUserTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(AppUserTableView.class.getName());

	private final BaseTableView<AppUserDto> table;

	private final CoreMessages i18n;

	/**
	 * 
	 */
	@Inject
	AppUserTableView(BaseTableView<AppUserDto> table, ResourceDelegate<AppUserResource> usersDelegate,
			CoreMessages i18n) {
		logger.info("AppUserTableView()");
		initWidget(table);

		this.table = table;
		this.i18n = i18n;

		initTable();
	}

	private void initTable() {
		table.setTitle(i18n.usersTableTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		table.getDeleteIcon().addClickHandler(e -> {
			getUiHandlers().delete(table.getSelected());
		});

		// Code Column
		table.addColumn(
				new DataColumn<AppUserDto>((object) -> object.getCode(),
						(o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode())),
				i18n.userEditorCode());

		// Name Column
		table.addColumn(
				new DataColumn<AppUserDto>((object) -> object.getName(),
						(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())),
				i18n.userEditorName());

		// Title Column
		table.addColumn(new DataColumn<AppUserDto>((object) -> object.getTitle()), i18n.userEditorTitle());

		// Email Column
		table.addColumn(new DataColumn<AppUserDto>((object) -> object.getEmailAddress()), i18n.usersTableEmail());

		// Edit Column
		table.addColumn(new EditColumn<AppUserDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<AppUserDto> data) {
		table.setData(data);
	}
}
