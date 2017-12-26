/**
 * 
 */
package hu.hw.cloud.client.inf.ana.perf1;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.base.constants.StyleName;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.ui.table.MaterialDataTable;
import gwt.material.design.client.ui.table.cell.TextColumn;

import hu.hw.cloud.shared.dto.cube.Perf1Dto;

/**
 * @author CR
 *
 */
public class Perf1Table extends Composite {
	private static Logger logger = Logger.getLogger(Perf1Table.class.getName());

	interface Binder extends UiBinder<Widget, Perf1Table> {
	}
	private static Binder uiBinder = GWT.create(Binder.class);

//	private final CoreMessages i18n;

	@UiField(provided = true)
	MaterialDataTable<Perf1Dto> table;

	/**
	 */
//	@Inject
	public Perf1Table() {
		logger.log(Level.INFO, "Perf1Table()");
//		this.i18n = i18n;

		table = new MaterialDataTable<Perf1Dto>();

		initWidget(uiBinder.createAndBindUi(this));

		init();
	}

	// @Override
	// protected void onLoad() {
	// super.onLoad();
	private void init() {
		logger.log(Level.INFO, "Perf1Table.init()");
		table.setVisibleRange(0, 2001);
		table.setUseStickyHeader(true);
		// table.getTableTitle().setText(i18n.usersTableTitle());
		// Load the categories from the server
		// table.setLoadMask(true);

		// Add the tables columns
		// Date
		table.addColumn(new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return object.getDim();
			}
		}, "Date");

		// OccAct
		TextColumn<Perf1Dto> occActCol = new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0.00").format(object.getOccAct());
			}
		};
		occActCol.setTextAlign(TextAlign.RIGHT);
		occActCol.setStyleProperty(StyleName.BACKGROUND_COLOR, "#eeeeff");
		table.addColumn(occActCol, "Occ%(Actual)");

		// OccVs
		TextColumn<Perf1Dto> occVsCol = new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0.00").format(object.getOccVs());
			}
		};
		occVsCol.setTextAlign(TextAlign.RIGHT);
		occVsCol.setStyleProperty(StyleName.BACKGROUND_COLOR, "#eeeeff");
		table.addColumn(occVsCol, "Occ%(VS)");

		// OccDelta
		TextColumn<Perf1Dto> occDelatCol = new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0.00").format(object.getOccDelta());
			}
		};
		occDelatCol.setTextAlign(TextAlign.RIGHT);
		occDelatCol.setStyleProperty(StyleName.BACKGROUND_COLOR, "#eeeeff");
		table.addColumn(occDelatCol, "Delta%");

		// AdrAct
		table.addColumn(new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0").format(object.getAdrAct());
			}
		}, "ADR(Actual)");

		// AdrVs
		table.addColumn(new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0").format(object.getAdrVs());
			}
		}, "ADR(VS)");

		// AdrDelta
		table.addColumn(new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0.00").format(object.getAdrDelta());
			}
		}, "Delta%");

		// RevParAct
		table.addColumn(new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0").format(object.getRevparAct());
			}
		}, "RevPAR(Actual)");

		// RevParVs
		table.addColumn(new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0").format(object.getRevparVs());
			}
		}, "RevPAR(VS)");

		// RevParDelta
		table.addColumn(new TextColumn<Perf1Dto>() {
			@Override
			public String getValue(Perf1Dto object) {
				return NumberFormat.getFormat("#,##0.00").format(object.getRevparDelta());
			}
		}, "Delta%");

		logger.log(Level.INFO, "UsersTable.onLoad()->end");
	}

	public void refresh() {
		logger.log(Level.INFO, "UsersTable.refresh()");
		table.getView().refresh();
	}

	public void setData(List<Perf1Dto> data) {
		logger.log(Level.INFO, "UsersTable.setData()");
		table.setRowData(0, data);
		// table.setRedraw(true);
		table.getView().refresh();
	}
}
