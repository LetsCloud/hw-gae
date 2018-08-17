/**
 * 
 */
package hu.hw.cloud.client.fro.browser.hotel;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.data.component.RowComponent;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.cell.TextColumn;
import gwt.material.design.client.ui.table.cell.WidgetColumn;

import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.client.fro.browser.BrowserView;
import hu.hw.cloud.shared.dto.hotel.HotelDto;

/**
 * @author robi
 *
 */
public class HotelTableView extends ViewWithUiHandlers<HotelTableUiHandlers> implements HotelTablePresenter.MyView {
	private static Logger logger = Logger.getLogger(HotelTableView.class.getName());

	private final BrowserView<HotelDto> table;

	private final CoreMessages i18nCore;

	/**
	* 
	*/
	@Inject
	HotelTableView(BrowserView<HotelDto> table, CoreMessages i18nCore) {
		logger.info("HotelTableView()");

		initWidget(table);

		this.table = table;
		this.i18nCore = i18nCore;

		init();
	}

	private void init() {

		table.setTitle(i18nCore.hotelsTableTitle());

		table.getAddButton().addClickHandler(e -> {
			getUiHandlers().addNew();
		});

		/*
		 * CODE
		 */
		table.getTable().addColumn(new TextColumn<HotelDto>() {
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
		}, i18nCore.hotelsTableCode());

		/*
		 * NAME
		 */
		table.getTable().addColumn(new TextColumn<HotelDto>() {
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
		}, i18nCore.hotelsTableName());

		//
		// EDIT ICON
		//
		table.getTable().addColumn(new WidgetColumn<HotelDto, MaterialIcon>() {
			@Override
			public TextAlign textAlign() {
				return TextAlign.RIGHT;
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

				icon.setWaves(WavesType.DEFAULT);
				icon.setIconType(IconType.EDIT);
				icon.setBackgroundColor(Color.AMBER);
				icon.setCircle(true);
				icon.setTextColor(Color.WHITE);
				return icon;
			}
		});
	}

	@Override
	public void setData(List<HotelDto> data) {
		logger.info("RoomTypeTableView().setData()");
		table.setData(data);
	}
}
