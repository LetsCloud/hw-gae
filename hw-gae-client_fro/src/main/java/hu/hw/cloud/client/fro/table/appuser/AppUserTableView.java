/**
 * 
 */
package hu.hw.cloud.client.fro.table.appuser;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.data.component.RowComponent;
import gwt.material.design.client.data.events.SetupEvent;
import gwt.material.design.client.data.events.SetupHandler;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.client.ui.table.cell.TextColumn;
import gwt.material.design.client.ui.table.cell.WidgetColumn;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

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

	/**
	 * 
	 */
	@Inject
	AppUserTableView(Binder uiBinder, ResourceDelegate<AppUserResource> usersDelegate, CoreMessages i18n) {
		initWidget(uiBinder.createAndBindUi(this));

		this.i18n = i18n;

		initTable();
	}

	private void initTable() {
		table.getTableTitle().setText(i18n.usersTableTitle());

		table.addSetupHandler(new SetupHandler() {
			@Override
			public void onSetup(SetupEvent event) {
				setToolPanel(event.getScaffolding().getToolPanel());
			}
		});

		/*
		 * CODE
		 */
		table.addColumn(new TextColumn<AppUserDto>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public Comparator<? super RowComponent<AppUserDto>> sortComparator() {
				return (o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode());
			}

			@Override
			public String getValue(AppUserDto object) {
				return object.getCode();
			}
		}, i18n.userEditorCode());

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
		 * TITLE
		 */
		table.addColumn(new TextColumn<AppUserDto>() {
			@Override
			public String getValue(AppUserDto object) {
				return object.getTitle();
			}
		}, i18n.userEditorTitle());

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
						getUiHandlers().edit(object);
					}
				});

				icon.setIconType(IconType.EDIT);
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

	private void setToolPanel(Panel toolPanel) {
		// Add two buttons
		MaterialIcon deleteIcon = new MaterialIcon(IconType.DELETE);
		deleteIcon.addClickHandler((e) -> {
			getUiHandlers().delete(table.getSelectedRowModels(false));});
		
		MaterialIcon menuIcon = new MaterialIcon(IconType.MORE_VERT);
		menuIcon.setActivates("dd-menu");
		
		MaterialDropDown menuDropDown = new MaterialDropDown();
		menuDropDown.setActivator("dd-menu");
		
		MaterialLink inviteLink = new MaterialLink();
		inviteLink.setIconType(IconType.DRAFTS);
		inviteLink.setText("Invite");
		inviteLink.addClickHandler((e) -> {
			getUiHandlers().inviteItem(table.getSelectedRowModels(false));});
		menuDropDown.add(inviteLink);
		
		MaterialLink clearTokenLink = new MaterialLink();
		clearTokenLink.setIconType(IconType.REMOVE_FROM_QUEUE);
		clearTokenLink.setText("Clear tokens");
		clearTokenLink.addClickHandler((e) -> {
			getUiHandlers().clearFcmTokens(table.getSelectedRowModels(false));});
		menuDropDown.add(clearTokenLink);
		
		MaterialLink pdfLink = new MaterialLink();
		pdfLink.setIconType(IconType.PICTURE_AS_PDF);
		pdfLink.setText("PDF export");
		menuDropDown.add(pdfLink);
		
		toolPanel.add(deleteIcon);
		toolPanel.add(menuIcon);
		toolPanel.add(menuDropDown);
		
		table.getStretchIcon().setVisible(false);
		table.getColumnMenuIcon().setVisible(false);
	}

	@Override
	public void setData(List<AppUserDto> data) {
		logger.info("setData()");
		table.getView().clearRows(true);
		table.setRowData(0, data);
		table.sort(0);
	}
}
