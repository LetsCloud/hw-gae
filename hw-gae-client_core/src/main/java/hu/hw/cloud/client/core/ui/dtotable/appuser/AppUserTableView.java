/**
 * 
 */
package hu.hw.cloud.client.core.ui.dtotable.appuser;

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
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * @author CR
 *
 */
public class AppUserTableView extends ViewWithUiHandlers<AppUserTableUiHandlers>
		implements AppUserTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(AppUserTableView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, AppUserTableView> {
	}

	private final CoreMessages i18n;

	@UiField
	MaterialDataTable<AppUserDto> table;

	@UiField
	SimplePanel editorPanel;

	/**
	 * 
	 */
	@Inject
	AppUserTableView(Binder uiBinder, ResourceDelegate<AppUserResource> usersDelegate, CoreMessages i18n) {
		initWidget(uiBinder.createAndBindUi(this));

		this.i18n = i18n;

		bindSlot(AppUserTablePresenter.SLOT_EDITOR, editorPanel);

		initTable();
	}

	private void initTable() {
		table.setSelectionType(SelectionType.NONE);

		table.setUseCategories(false);

		if (table.getColumns().size() > 0)
			return;

		table.getTableTitle().setText(i18n.usersTableTitle());
		// Load the categories from the server
		// table.setLoadMask(true);

		// Add the tables columns

		/*
		 * NAME
		 */
		table.addColumn(new TextColumn<AppUserDto>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public Comparator<? super RowComponent<AppUserDto>> sortComparator() {
				return (o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName());
			}

			@Override
			public String getValue(AppUserDto object) {
				return object.getName();
			}
		}, i18n.userEditorUsername());

		/*
		 * EMAIL ADDRESS
		 */
		table.addColumn(new TextColumn<AppUserDto>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public String getValue(AppUserDto object) {
				return object.getEmailAddress();
			}
		}, i18n.usersTableEmail());
		
		//
		// EDIT ICON
		// 
		table.addColumn(new WidgetColumn<AppUserDto, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.CENTER;
			}

			@Override
			public MaterialIcon getValue(AppUserDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						getUiHandlers().editItem(object);
					}
				});

				icon.setIconType(IconType.EDIT);
				return icon;
			}
		});
		
		//
		// INVITE ICON
		// 
		table.addColumn(new WidgetColumn<AppUserDto, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.CENTER;
			}

			@Override
			public MaterialIcon getValue(AppUserDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						getUiHandlers().inviteItem(object);
					}
				});

				icon.setIconType(IconType.DRAFTS);
				return icon;
			}
		});
		
		//
		// CLEAR FCM TOKENS ICON
		// 
		table.addColumn(new WidgetColumn<AppUserDto, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.CENTER;
			}

			@Override
			public MaterialIcon getValue(AppUserDto object) {
				MaterialIcon icon = new MaterialIcon();
				icon.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						getUiHandlers().clearFcmTokens(object);
					}
				});

				icon.setIconType(IconType.REMOVE_FROM_QUEUE);
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
	public void setData(List<AppUserDto> data) {
		logger.log(Level.INFO, "setData()");
		table.getView().clearRows(true);
		table.setRowData(0, data);
		table.sort(0);
	}
}