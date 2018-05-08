/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.data.SortDir;
import gwt.material.design.client.data.events.SetupEvent;
import gwt.material.design.client.data.events.SetupHandler;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialDropDown;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.client.ui.table.cell.Column;

import hu.hw.cloud.shared.dto.BaseDto;

/**
 * @author robi
 *
 */
public class BaseTableView<T extends BaseDto> extends Composite {
	private static Logger logger = Logger.getLogger(BaseTableView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, BaseTableView<?>> {
	}

	public interface EditRow<O extends BaseDto> {
		void edit(O object);
	}
	
	@UiField
	SimplePanel filterPanel;

	@UiField
	MaterialDataTable<T> table;

	@UiField
	MaterialButton addButton;

	MaterialIcon deleteIcon;

	@UiField
	SimplePanel editorPanel;

	/**
	* 
	*/
	@Inject
	BaseTableView(Binder uiBinder) {
		logger.info("AbstractTableView()");

		initWidget(uiBinder.createAndBindUi(this));

		initTable();
	}

	public SimplePanel getEditorPanel() {
		return editorPanel;
	}

	public void setTitle(String title) {
		table.getTableTitle().setText(title);
	}

	private void initTable() {
		deleteIcon = new MaterialIcon(IconType.DELETE);

		table.addSetupHandler(new SetupHandler() {
			@Override
			public void onSetup(SetupEvent event) {
				setToolPanel(event.getScaffolding().getToolPanel());
			}
		});

		table.addColumnSortHandler(event -> {
			table.getView().refresh();
		});

		table.getView().refresh();
	}

	private void setToolPanel(Panel toolPanel) {

		MaterialIcon menuIcon = new MaterialIcon(IconType.MORE_VERT);
		menuIcon.setActivates("dd-menu");

		MaterialDropDown menuDropDown = new MaterialDropDown();
		menuDropDown.setActivator("dd-menu");
		menuDropDown.setConstrainWidth(false);
		menuDropDown.setWidth("180px");

		MaterialLink pdfLink = new MaterialLink();
		pdfLink.setIconType(IconType.PICTURE_AS_PDF);
		pdfLink.setText("PDF export");
		menuDropDown.add(pdfLink);

		MaterialLink xlsLink = new MaterialLink();
		xlsLink.setIconType(IconType.DOCK);
		pdfLink.setText("XLS export");
		menuDropDown.add(xlsLink);

		toolPanel.add(deleteIcon);
		toolPanel.add(menuIcon);
		toolPanel.add(menuDropDown);

		table.getStretchIcon().setVisible(false);
		table.getColumnMenuIcon().setVisible(false);
	}

	public MaterialDataTable<T> getTable() {
		return table;
	}

	public void setData(List<T> data) {
		logger.info("AbstractTableView().setData()");
		table.setVisibleRange(0, data.size());
		table.setRowData(0, data);
		table.sort(0, SortDir.ASC);
	}

	public MaterialButton getAddButton() {
		return addButton;
	}

	public MaterialIcon getDeleteIcon() {
		return deleteIcon;
	}

	public List<T> getSelected() {
		return table.getSelectedRowModels(false);
	}

	public SimplePanel getFilterPanel() {
		return filterPanel;
	}

	public void editRecord(T object) {

	}

	public void addColumn(Column<T,?> column) {
		table.addColumn(column);
	}

	public void addColumn(Column<T,?> column, String title) {
		table.addColumn(column, title);
	}
}
