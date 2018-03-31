/**
 * 
 */
package hu.hw.cloud.client.fro.table.usergroup;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.data.SelectionType;
import gwt.material.design.client.data.component.RowComponent;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.client.ui.table.cell.TextColumn;
import gwt.material.design.client.ui.table.cell.WidgetColumn;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.shared.UserGroupResource;
import hu.hw.cloud.shared.dto.common.UserGroupDto;

/**
 * @author robi
 *
 */
public class UserGroupTableView extends ViewWithUiHandlers<UserGroupTableUiHandlers>
		implements UserGroupTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(UserGroupTableView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, UserGroupTableView> {
	}

	private final CoreMessages i18n;

	@UiField
	MaterialDataTable<UserGroupDto> table;

	@UiField
	SimplePanel editorPanel;

	/**
	 * 
	 */
	@Inject
	UserGroupTableView(Binder uiBinder, ResourceDelegate<UserGroupResource> resourceDelegate, CoreMessages i18n) {
		initWidget(uiBinder.createAndBindUi(this));

		this.i18n = i18n;

		bindSlot(UserGroupTablePresenter.SLOT_EDITOR, editorPanel);

		initTable();
	}

	private void initTable() {
		table.setSelectionType(SelectionType.NONE);

		table.setUseCategories(false);

		if (table.getColumns().size() > 0)
			return;

		table.getTableTitle().setText(i18n.userGroupTableTitle());
		// Load the categories from the server
		// table.setLoadMask(true);

		// Add the tables columns
		table.addColumn(new TextColumn<UserGroupDto>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public Comparator<? super RowComponent<UserGroupDto>> sortComparator() {
				return (o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName());
			}

			@Override
			public String getValue(UserGroupDto object) {
				return object.getName();
			}
		}, i18n.userGroupTableName());

		/**
		 * Edit Icon
		 */
		table.addColumn(new WidgetColumn<UserGroupDto, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.CENTER;
			}

			@Override
			public MaterialIcon getValue(UserGroupDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						getUiHandlers().edit(object);
					}
				});

				icon.setIconType(IconType.EDIT);
				return icon;
			}
		});

		/**
		 * Edit Icon
		 */
		table.addColumn(new WidgetColumn<UserGroupDto, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.CENTER;
			}

			@Override
			public MaterialIcon getValue(UserGroupDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						getUiHandlers().delete(object);
					}
				});

				icon.setIconType(IconType.DELETE);
				return icon;
			}
		});
		// table.addRowSelectHandler((e, model, elem, selected) -> {
		// updateSelectedRows(table.getSelectedRowModels(false).size());
		// return true;
		// });

		table.addColumnSortHandler(event -> {
			table.getView().refresh();
		});

		// table.addSelectAllHandler((e, models, elems, selected) -> {
		// updateSelectedRows(table.getSelectedRowModels(false).size());
		// return true;
		// });
	}

	@Override
	public void setData(List<UserGroupDto> data) {
		logger.info("setData()_1");
		table.getView().clearRows(true);
		logger.info("setData()_2");
		table.setRowData(0, data);
		logger.info("setData()_3");
		table.sort(0);
		logger.info("setData()_4");
	}
}
