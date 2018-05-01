/**
 * 
 */
package hu.hw.cloud.client.fro.table;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author robi
 *
 */
public class AddNewEvent extends GwtEvent<AddNewEvent.AddNewHandler> {

	public enum TableType {
		APP_USER, USER_GROUP;
	}

	public interface AddNewHandler extends EventHandler {
		void onAddNew(AddNewEvent event);
	}

	public static final Type<AddNewHandler> TYPE = new Type<>();

	private TableType tableType;

	AddNewEvent(TableType tableType) {
		this.tableType = tableType;
	}

	public static Type<AddNewHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, TableType tableType) {
		source.fireEvent(new AddNewEvent(tableType));
	}

	@Override
	public Type<AddNewHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddNewHandler handler) {
		handler.onAddNew(this);
	}

	public TableType getTableType() {
		return tableType;
	}
}