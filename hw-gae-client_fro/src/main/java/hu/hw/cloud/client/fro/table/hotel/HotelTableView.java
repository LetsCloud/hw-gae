/**
 * 
 */
package hu.hw.cloud.client.fro.table.hotel;

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
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.data.SortDir;
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
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelTableView extends ViewWithUiHandlers<HotelTableUiHandlers> implements HotelTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(HotelTableView.class.getName());

	interface Binder extends UiBinder<HTMLPanel, HotelTableView> {
	}

	private final CoreMessages i18n;

	@UiField
	MaterialDataTable<HotelDto> table;

	/**
	* 
	*/
	@Inject
	HotelTableView(Binder uiBinder, CoreMessages i18n) {
		logger.info("HotelTableView()");

		initWidget(uiBinder.createAndBindUi(this));

		this.i18n = i18n;

		initTable();

	}

	private void initTable() {

		table.getTableTitle().setText(i18n.hotelsTableTitle());

		table.addSetupHandler(new SetupHandler() {

			@Override
			public void onSetup(SetupEvent event) {
				logger.info("initTable().onSetup()");
				Panel tp = event.getScaffolding().getToolPanel();
				// Add two buttons
				MaterialIcon dmi = new MaterialIcon(IconType.DELETE);
				dmi.addClickHandler((e) -> {
					getUiHandlers().delete(table.getSelectedRowModels(false));});
				
				MaterialIcon mmi = new MaterialIcon(IconType.MORE_VERT);
				mmi.setActivates("dd-menu");
				
				MaterialDropDown mdd = new MaterialDropDown();
				mdd.setActivator("dd-menu");
				MaterialLink pdfLink = new MaterialLink();
				pdfLink.setIconType(IconType.PICTURE_AS_PDF);
				pdfLink.setText("PDF export");
				mdd.add(pdfLink);
				MaterialLink xlsLink = new MaterialLink();
				xlsLink.setIconType(IconType.DOCK);
				pdfLink.setText("XLS export");
				mdd.add(xlsLink);
				
				tp.add(dmi);
				tp.add(mmi);
				tp.add(mdd);
				
				table.getStretchIcon().setVisible(false);
				table.getColumnMenuIcon().setVisible(false);
			}

		});

		// Load the categories from the server
		// table.setLoadMask(true);

		// Add the tables columns

		/*
		 * CODE
		 */
		table.addColumn(new TextColumn<HotelDto>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public Comparator<? super RowComponent<HotelDto>> sortComparator() {
				return (o1, o2) -> o1.getData().getCode().compareToIgnoreCase(o2.getData().getCode());
			}

			@Override
			public String getValue(HotelDto object) {
				return object.getCode();
			}
		}, i18n.hotelsTableCode());

		/*
		 * NAME
		 */
		table.addColumn(new TextColumn<HotelDto>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public Comparator<? super RowComponent<HotelDto>> sortComparator() {
				return (o1, o2) -> o1.getData().getName().compareToIgnoreCase(o2.getData().getName());
			}

			@Override
			public String getValue(HotelDto object) {
				return object.getName();
			}
		}, i18n.hotelsTableName());

		//
		// EDIT ICON
		//
		table.addColumn(new WidgetColumn<HotelDto, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.CENTER;
			}

			@Override
			public MaterialIcon getValue(HotelDto object) {
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
		// Adding / removing table toolpanel action buttons / icons
//		Panel panel = table.getScaffolding().getToolPanel();

		table.getView().refresh();

	}

	@Override
	public void setData(List<HotelDto> data) {

		table.setVisibleRange(0, data.size());
		table.setRowData(0, data);
		table.sort(0, SortDir.ASC);
	}
}
