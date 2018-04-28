/**
 * 
 */
package hu.hw.cloud.client.fro.table.usergroup;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.table.BaseTableView;
import hu.hw.cloud.client.fro.table.DataColumn;
import hu.hw.cloud.client.fro.table.EditColumn;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupTableView extends ViewWithUiHandlers<UserGroupTableUiHandlers>
		implements UserGroupTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(UserGroupTableView.class.getName());

	private final BaseTableView<UserGroupDto> table;

	private final CoreMessages i18nCore;

	/**
	 * 
	 */
	@Inject
	UserGroupTableView(BaseTableView<UserGroupDto> table, CoreMessages i18nCore) {
		logger.info("UserGroupTableView()");
		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		bindSlot(UserGroupTablePresenter.SLOT_EDITOR, table.getEditorPanel());

		initTable();
	}

	private void initTable() {

		table.setTitle(i18nCore.userGroupTableTitle());

		// Name Column
		table.addColumn(
				new DataColumn<UserGroupDto>((object) -> object.getName(),
						(o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName())),
				i18nCore.userGroupTableName());

		// Edit Column
		table.addColumn(new EditColumn<UserGroupDto>((object) -> getUiHandlers().edit(object)));
	}

	@Override
	public void setData(List<UserGroupDto> data) {
		table.setData(data);
	}
}
