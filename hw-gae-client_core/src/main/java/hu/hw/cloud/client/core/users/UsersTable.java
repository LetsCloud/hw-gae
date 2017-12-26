/**
 * 
 */
package hu.hw.cloud.client.core.users;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.Presenter;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.TextAlign;
import gwt.material.design.client.data.infinite.InfiniteDataView;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.table.MaterialInfiniteDataTable;
import gwt.material.design.client.ui.table.cell.TextColumn;
import gwt.material.design.client.ui.table.cell.WidgetColumn;
import hu.hw.cloud.client.core.i18n.CoreMessages;
import hu.hw.cloud.shared.AppUserResource;
import hu.hw.cloud.shared.dto.common.AppUserDto;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * @author CR
 *
 */
public class UsersTable extends Composite {
	private static Logger logger = Logger.getLogger(UsersTable.class.getName());

	interface UsersTableUiBinder extends UiBinder<HTMLPanel, UsersTable> {
	}

	private static UsersTableUiBinder binder = GWT.create(UsersTableUiBinder.class);

	private Presenter<?, ?> presenter;

	private final CoreMessages i18n;

	@UiField(provided = true)
	MaterialInfiniteDataTable<AppUserDto> table;

	/**
	 */
	@Inject
	UsersTable(ResourceDelegate<AppUserResource> usersDelegate, CoreMessages i18n) {
		logger.log(Level.INFO, "UsersTable()");
		table = new MaterialInfiniteDataTable<>(20, InfiniteDataView.DYNAMIC_VIEW,
				new AppUserDataSource(usersDelegate));

		this.i18n = i18n;

		initWidget(binder.createAndBindUi(this));

		table.setUseCategories(false);
	}

	public void setPresenter(Presenter<?, ?> presenter) {
		this.presenter = presenter;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		logger.log(Level.INFO, "UsersTable.onLoad()");
		if (table.getColumns().size() > 0)
			return;

		table.getTableTitle().setText(i18n.usersTableTitle());
		// Load the categories from the server
		// table.setLoadMask(true);

		// Add the tables columns
		table.addColumn(new TextColumn<AppUserDto>() {
			@Override
			public boolean isSortable() {
				return true;
			}

			@Override
			public String getValue(AppUserDto object) {
				return object.getUsername();
			}
		}, i18n.userEditorUsername());

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
		// Example of a widget column!
		// You can add any handler to the column cells widget.
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
						UserEditEvent.fire(presenter);
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

		logger.log(Level.INFO, "UsersTable.onLoad()->end");
	}

	public void refresh() {
		logger.log(Level.INFO, "UsersTable.refresh()");
		table.getView().refresh();
	}
}
